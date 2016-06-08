package com.qcloud.project.forest.web.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.personalcenter.PersonalcenterClient;
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

    public static final String             USER_SAFETY_SMS_TEMPLATE_KEY = "personalcenter-user-safety-sms-template";

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
            view.setMessage("登录失败");
            view.addObject("loginState", 2);
            return view;
        }
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
}
