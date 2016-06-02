package com.qcloud.component.personalcenter.web.controller.app;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.qcloud.component.personalcenter.exception.PersonalCenterException;
import com.qcloud.component.personalcenter.model.CardNumberConfig;
import com.qcloud.component.personalcenter.model.MembershipCardWarehouse;
import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.model.key.TypeEnum.MembershipCardWarehouseStateType;
import com.qcloud.component.personalcenter.model.key.TypeEnum.UserStateType;
import com.qcloud.component.personalcenter.service.MembershipCardWarehouseService;
import com.qcloud.component.personalcenter.service.UserService;
import com.qcloud.component.personalcenter.web.controller.UserController;
import com.qcloud.component.publicservice.SmsClient;
import com.qcloud.component.publicservice.VerificationCodeClient;
import com.qcloud.component.token.TokenClient;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.filter.user.UserFilterService;

@Controller
@RequestMapping(value = AppUserController.DIR)
public class AppUserController {

    public static final String             DIR    = "/app/user";

    @Autowired
    private UserController                 userController;

    //
    private Log                            logger = LogFactory.getLog(getClass());

    @Autowired
    private UserService                    userService;

    @Autowired
    private UserFilterService              userFilterService;

    @Autowired
    private TokenClient                    tokenClient;

    @Autowired
    private SmsClient                      smsClient;

    @Autowired
    private VerificationCodeClient         verificationCodeClient;

    @Autowired
    private MembershipCardWarehouseService membershipCardWarehouseService;

    // @RequestMapping(method = RequestMethod.POST)
    // public FrontAjaxView sendMsgForActivateMembershipCard(String mobile) {
    //
    // return userController.sendMsgForActivateMembershipCard(mobile);
    // }
    //
    // @RequestMapping(method = RequestMethod.POST)
    // public FrontAjaxView sendMsgForRegister(String mobile) {
    //
    // return userController.sendMsgForRegister(mobile);
    // }
    //
    // @RequestMapping
    // public FrontAjaxView registerByMobile(HttpServletRequest request, String membershipCard, String mobile, String code, String pwd) {
    //
    // return userController.registerByMobile(request, membershipCard, mobile, code, pwd);
    // }
    //
    // @RequestMapping
    // public FrontAjaxView activateUser(HttpServletRequest request, String membershipCard, String mobile, String code, String pwd) {
    //
    // return userController.activateUser(request, membershipCard, mobile, code, pwd);
    // }
    @RequestMapping(method = RequestMethod.POST)
    public FrontAjaxView login(HttpServletRequest request, String username, String pwd) {

        AssertUtil.assertNotEmpty(username, "账号不能为空.");
        AssertUtil.assertNotEmpty(pwd, "密码不能为空.");
        // 会员卡
        MembershipCardWarehouse membershipCardWarehouse = membershipCardWarehouseService.getByCardNumber(username);
        CardNumberConfig cardNumberConfig = membershipCardWarehouseService.getConfig();
        if (membershipCardWarehouse != null && membershipCardWarehouse.getState() == MembershipCardWarehouseStateType.NEW.getKey()) {
            AssertUtil.assertTrue(pwd.equals(cardNumberConfig.getDefaultPwd()), "密码不正确.");
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("登录失败");
            view.addObject("loginState", 2);
            return view;
        }
        // 会员卡
        logger.info(username);
        String identificationKey = null;
        if (userService.isUser(username, pwd)) {
            User user = userService.getByAccount(username);
            if (user.getState() == UserStateType.NEW.getKey()) {
                FrontAjaxView view = new FrontAjaxView();
                view.setMessage("登录失败");
                view.addObject("loginState", 3);
                return view;
            }
            identificationKey = String.valueOf(user.getId());
        } else {
            throw new PersonalCenterException("账号或密码有误.");
        }
        String tokenId = userFilterService.doLogin(request);
        boolean ok = tokenClient.reg(tokenId, identificationKey);
        if (!ok) {
            throw new PersonalCenterException("系统服务出现异常,token添加失败.");
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("登录成功");
        view.addObject("token", tokenId);
        view.addObject("loginState", 1);
        return view;
    }

    @RequestMapping
    public FrontAjaxView logout(HttpServletRequest request, String qc_app_token) {

        tokenClient.remove(qc_app_token);
        userController.logout(request);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("已经退出系统.");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST)
    public FrontAjaxView sendMsgForForgetPwd(String mobile) {

        User u = userService.getByAccount(mobile);
        if (u == null) {
            throw new PersonalCenterException("手机号尚未注册" + mobile);
        }
        if (u.getState() == UserStateType.FROZEN.getKey()) {
            throw new PersonalCenterException("用户已经被冻结." + mobile);
        }
        if (u.getState() == UserStateType.FORBIDDEN.getKey()) {
            throw new PersonalCenterException("用户已经被禁用." + mobile);
        }
        String code = verificationCodeClient.create(mobile, 30);
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        map.put("minute", "30");
        boolean result = smsClient.send(UserController.USER_REGIST_SMS_FPWDTEMPLATE_KEY, mobile, map);
        if (result) {
            int[] exCodeArray = new int[code.length()];
            for (int index = 0; index < exCodeArray.length; index++) {
                exCodeArray[index] = '9' - code.charAt(index);
            }
            StringBuffer sb = new StringBuffer("");
            for (int index = 0; index < exCodeArray.length; index++) {
                sb.append(exCodeArray[index]);
            }
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("发送短信成功");
            view.addObject("result", sb.toString());
            return view;
        } else {
            throw new PersonalCenterException("发送短信失败." + mobile);
        }
    }

    // @RequestMapping(method = RequestMethod.POST)
    // public FrontAjaxView setPwdByCode(String code, String username, String newPwd) {
    //
    // return userController.setPwdByCode(code, username, newPwd);
    // }
    // @RequestMapping(method = RequestMethod.POST)
    // public FrontAjaxView changePwd(HttpServletRequest request, String oldPwd, String newPwd) {
    //
    // return userController.changePwd(request, oldPwd, newPwd);
    // }
    //
    // @RequestMapping
    // public FrontAjaxView getUser(HttpServletRequest request) {
    //
    // return userController.getUser(request);
    // }
    //
    // @RequestMapping
    // public FrontAjaxView edit(HttpServletRequest request, UserForm userForm) {
    //
    // return userController.edit(request, userForm);
    // }
    @RequestMapping
    public FrontAjaxView checkLoginToken() {

        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("OK");
        return view;
    }
    // @RequestMapping
    // public FrontAjaxView editHeadImage(HttpServletRequest request, String headImage) {
    //
    // return userController.editUserHeadImage(request, headImage);
    // }
    //
    // @RequestMapping
    // public FrontAjaxView editNickname(HttpServletRequest request, String nickname) {
    //
    // return userController.editNickname(request, nickname);
    // }
    //
    // @RequestMapping
    // public FrontAjaxView editSex(HttpServletRequest request, int sex) {
    //
    // return userController.editSex(request, sex);
    // }
    //
    // @RequestMapping
    // public FrontAjaxView editMobile(HttpServletRequest request, String mobile) {
    //
    // return userController.editMobile(request, mobile);
    // }
    //
    // @RequestMapping
    // public FrontAjaxView editEmail(HttpServletRequest request, String email) {
    //
    // return userController.editEmail(request, email);
    // }
    //
    // @RequestMapping
    // public FrontAjaxView editBirthDay(HttpServletRequest request, Integer birthYear, Integer birthMonth, Integer birthDay) {
    //
    // return userController.editBirthDay(request, birthYear, birthMonth, birthDay);
    // }
    //
    // @RequestMapping
    // public FrontAjaxView editName(HttpServletRequest request, String name) {
    //
    // return userController.editName(request, name);
    // }
}
