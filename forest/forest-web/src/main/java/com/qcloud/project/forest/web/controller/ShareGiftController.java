package com.qcloud.project.forest.web.controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.autoid.UniqueCodeGenerator;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.project.forest.model.ShareGift;
import com.qcloud.project.forest.model.ShareGiftUser;
import com.qcloud.project.forest.service.ShareGiftService;
import com.qcloud.project.forest.service.ShareGiftUserService;
import com.qcloud.project.forest.web.handler.ShareGiftUserHandler;
import com.qcloud.project.forest.web.vo.ShareGiftUserVO;

@Controller
@RequestMapping(value = ShareGiftController.DIR)
public class ShareGiftController {

    public static final String   DIR = "/shareGift";

    @Autowired
    private ShareGiftService     shareGiftService;

    @Autowired
    private UniqueCodeGenerator  uniqueCodeGenerator;

    @Autowired
    private ShareGiftUserService shareGiftUserService;

    @Autowired
    private ShareGiftUserHandler shareGiftUserHandler;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView showCode(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        ShareGift shareGift = shareGiftService.getByUserId(user.getId());
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        if (shareGift == null) {
            String code = uniqueCodeGenerator.generate("forest-shareforgift-code", new HashMap<String, String>());
            ShareGift shareGift1 = new ShareGift();
            shareGift1.setUserId(user.getId());
            shareGift1.setCode(code);
            shareGiftService.add(shareGift1);
            frontAjaxView.addObject("code", code);
            frontAjaxView.addObject("isShare", 0);
            frontAjaxView.addObject("inviteNum", 0);
            frontAjaxView.addObject("couponNum", 0);
        } else {
            frontAjaxView.addObject("code", shareGift.getCode());
            frontAjaxView.addObject("isShare", 1);
            frontAjaxView.addObject("inviteNum", shareGiftUserService.countByCode(shareGift.getCode()));
            frontAjaxView.addObject("couponNum", shareGiftUserService.countCouponByCode(shareGift.getCode()));
        }
        return frontAjaxView;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView myInvitation(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        ShareGift shareGift = shareGiftService.getByUserId(user.getId());
        List<ShareGiftUser> shareGiftUsers = shareGiftUserService.listByCode(shareGift.getCode());
        List<ShareGiftUserVO> shareGiftUserVOs = shareGiftUserHandler.toVOList(shareGiftUsers);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("list", shareGiftUserVOs);
        return frontAjaxView;
    }
}
