package com.qcloud.project.forest.web.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
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
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = ForestUserController.DIR)
public class ForestUserController {

    public static final String             DIR                          = "/forestUser";

    @Autowired
    private MembershipCardWarehouseService membershipCardWarehouseService;

    @Autowired
    private VerificationCodeClient         verificationCodeClient;

    @Autowired
    private UserFilterService              userFilterService;

    @Autowired
    private UserController                 userController;

    @Autowired
    private UserService                    userService;

    @Autowired
    private TokenClient                    tokenClient;

    @Autowired
    private SmsClient                      smsClient;

    @Autowired
    private ParameterClient                parameterClient;

    public static final String             USER_SAFETY_SMS_TEMPLATE_KEY = "personalcenter-user-safety-sms-template";

    public static final String             TIP_MESSAGE                  = "tip-message";

    public static final String             CONTACTS_MOBILE              = "contacts-mobile";

    @PiratesApp
    @RequestMapping
    public FrontAjaxView sendSafetyCode(String mobile) {

        User u = userService.getByAccount(mobile);
        if (u == null) {
            throw new PersonalCenterException("会员不存在." + mobile);
        }
        String code = verificationCodeClient.create(mobile, 30);
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        map.put("minute", "30");
        boolean result = smsClient.send(USER_SAFETY_SMS_TEMPLATE_KEY, mobile, map);
        if (result) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("发送短信成功");
            return view;
        } else {
            throw new PersonalCenterException("发送短信失败." + mobile);
        }
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView safetyLogin(HttpServletRequest request, String username, String pwd, String code) {

        AssertUtil.assertNotEmpty(username, "账号不能为空.");
        AssertUtil.assertNotEmpty(pwd, "密码不能为空.");
        boolean verification = verificationCodeClient.verification(username, code);
        AssertUtil.assertTrue(verification, "验证码不正确,请重新获取.");
        MembershipCardWarehouse membershipCardWarehouse = membershipCardWarehouseService.getByCardNumber(username);
        CardNumberConfig cardNumberConfig = membershipCardWarehouseService.getConfig();
        if (membershipCardWarehouse != null && membershipCardWarehouse.getState() == MembershipCardWarehouseStateType.NEW.getKey() && pwd.equals(cardNumberConfig.getDefaultPwd())) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("登录失败,会员卡尚未激活");
            view.addObject("loginState", 2);
            return view;
        }
        String identificationKey = null;
        if (userService.isUser(username, pwd)) {
            User user = userService.getByAccount(username);
            if (user.getState() == UserStateType.NEW.getKey()) {
                FrontAjaxView view = new FrontAjaxView();
                view.setMessage("登录失败,账号尚未激活");
                view.addObject("loginState", 3);
                return view;
            }
            if (user.getState() == UserStateType.FROZEN.getKey()) {
                FrontAjaxView view = new FrontAjaxView();
                String tip_message = parameterClient.get(TIP_MESSAGE);
                String contacts_mobile = parameterClient.get(CONTACTS_MOBILE);
                view.addObject("loginState", 4);
                view.setMessage("登录失败,账号已被冻结");
                view.addObject("contactMobile", "13800138000");
                if (StringUtils.isNotEmpty(tip_message)) {
                    view.addObject("tipMessage", tip_message);
                }
                if (StringUtils.isNotEmpty(contacts_mobile)) {
                    view.addObject("contactMobile", contacts_mobile);
                }
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
        return view;
    }

    @RequestMapping
    public FrontAjaxView editUser(HttpServletRequest request, String headImage, String nickname, String email) {

        PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        userController.editUserHeadImage(request, headImage);
        userController.editNickname(request, nickname);
        userController.editEmail(request, email);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改个人资料成功");
        return view;
    }

    /**
     * 修改绑定手机号 获取验证码
     * @param mobile 原手机号
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView getCodeForChangeBingdingMobileByOldNumber(HttpServletRequest request, String mobile) {

        User u = userService.getByAccount(mobile);
        if (u == null) {
            throw new PersonalCenterException("会员不存在." + mobile);
        }
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        AssertUtil.assertTrue(user.getMobile().equals(mobile), "您所填的电话号码不属于当前 用户");
        String code = verificationCodeClient.create(mobile, 30);
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        map.put("minute", "30");
        boolean result = smsClient.send(USER_SAFETY_SMS_TEMPLATE_KEY, mobile, map);
        if (result) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("发送短信成功");
            return view;
        } else {
            throw new PersonalCenterException("发送短信失败." + mobile);
        }
    }

    /**
     * 判断验证码是否正确
     * @param request
     * @param mobile
     * @param code
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView verifyCodeForChangeBingdingMobileByOldNumber(HttpServletRequest request, String mobile, String code) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        AssertUtil.assertTrue(user.getMobile().equals(mobile), "您所填的电话号码不属于当前 用户");
        boolean verification = verificationCodeClient.verification(mobile, code);
        // 上线时此处代码应该删掉
        // ---------------------------------
        if (code.equals("666666")) {
            verification = true;
        }
        // ---------------------------------
        AssertUtil.assertTrue(verification, "验证码不正确,请重新获取.");
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("验证成功");
        frontAjaxView.addObject("isVerify", 1);
        return frontAjaxView;
    }

    /**
     * 修改绑定手机号 获取验证码
     * @param mobile 原手机号
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView getCodeForChangeBingdingMobileByNewNumber(String mobile) {

        String code = verificationCodeClient.create(mobile, 30);
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        map.put("minute", "30");
        boolean result = smsClient.send(USER_SAFETY_SMS_TEMPLATE_KEY, mobile, map);
        if (result) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("发送短信成功");
            return view;
        } else {
            throw new PersonalCenterException("发送短信失败." + mobile);
        }
    }

    /**
     * 提交修改的新手机号和验证码
     * @param request
     * @param mobile
     * @param code
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView editBingdingMobile(HttpServletRequest request, String mobile, String code) {

        boolean verification = verificationCodeClient.verification(mobile, code);
        // 上线时此处代码应该删掉
        // ---------------------------------
        if (code.equals("666666")) {
            verification = true;
        }
        // ---------------------------------
        AssertUtil.assertTrue(verification, "验证码不正确,请重新获取.");
        return userController.editMobile(request, mobile);
    }
}
