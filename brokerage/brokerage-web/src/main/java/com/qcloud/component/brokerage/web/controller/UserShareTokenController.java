package com.qcloud.component.brokerage.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.brokerage.model.UserShareToken;
import com.qcloud.component.brokerage.service.UserShareTokenService;
import com.qcloud.component.brokerage.web.handler.AllocationUserHandler;
import com.qcloud.component.brokerage.web.vo.AllocationUser;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = UserShareTokenController.DIR)
public class UserShareTokenController {

    public static final String    DIR        = "/userShareToken";

    @Autowired
    private UserShareTokenService userShareTokenService;

    @Autowired
    private AllocationUserHandler allocationUserHandler;

    @Autowired
    private PersonalcenterClient  personalcenterClient;

    @Autowired
    private ParameterClient       parameterClient;

    private final String          DOMAIN_KEY = "domain-key";

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView getUserShareToken(HttpServletRequest request) {

        UserShareToken userShareToken = getAndInitUserShareToken(request);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取成功");
        view.addObject("result", Boolean.TRUE.toString());
        view.addObject("token", userShareToken.getToken());
        return view;
    }

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView getLoginAllocationUser(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        AllocationUser allocationUser = allocationUserHandler.toVO(user);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取成功");
        view.addObject("result", Boolean.TRUE.toString());
        view.addObject("shareUser", allocationUser);
        return view;
    }

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView getMobileByToken(String token) {

        AssertUtil.assertNotEmpty(token, "Token不能为空.");
        UserShareToken userShareToken = userShareTokenService.getByToken(token);
        AssertUtil.assertNotNull(userShareToken, "Token不存在." + token);
        QUser user = personalcenterClient.getUser(userShareToken.getUserId());
        AssertUtil.assertNotNull(user, "用户不存在." + token);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取分享用户手机号成功");
        view.addObject("mobile", user.getMobile());
        return view;
    }

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView getQRCodeUrl(HttpServletRequest request) {

        UserShareToken userShareToken = getAndInitUserShareToken(request);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取成功");
        view.addObject("qrCodeUrl", "http://" + parameterClient.get(DOMAIN_KEY) + "/personalQRCode.html?token=" + userShareToken.getToken());
        return view;
    }

    private UserShareToken getAndInitUserShareToken(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        synchronized (user.getMobile()) {
            UserShareToken userShareToken = userShareTokenService.getByUser(user.getId());
            return userShareToken;
        }
    }
}
