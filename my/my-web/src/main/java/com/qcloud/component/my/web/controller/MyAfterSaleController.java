package com.qcloud.component.my.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.my.model.MyAfterSale;
import com.qcloud.component.my.service.MyAfterSaleService;
import com.qcloud.component.my.web.handler.MyAfterSaleHandler;
import com.qcloud.component.my.web.vo.MyAfterSaleVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = MyAfterSaleController.DIR)
public class MyAfterSaleController {

    public static final String DIR = "/myAfterSale";

    @Autowired
    private MyAfterSaleService myAfterSaleService;

    @Autowired
    private MyAfterSaleHandler myAfterSaleHandler;

    @PiratesApp
    @RequestMapping
    public FrontPagingView list(HttpServletRequest request, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyAfterSale> list = myAfterSaleService.listByUser(user.getId(), pPage.getPageStart(), pPage.getPageSize());
        int total = myAfterSaleService.countByUser(user.getId());
        List<MyAfterSaleVO> voList = myAfterSaleHandler.toVOList(list);
        FrontPagingView view = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), total);
        view.setMessage("获取我的售后列表成功.");
        view.setList(voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listByOrder(HttpServletRequest request, Long orderId) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyAfterSale> list = myAfterSaleService.listByUserAndOrder(user.getId(), orderId);
        List<MyAfterSaleVO> voList = myAfterSaleHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取我的售后列表成功.");
        view.addObject("data", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listBySubOrder(HttpServletRequest request, Long subOrderId) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyAfterSale> list = myAfterSaleService.listByUserAndSubOrder(user.getId(), subOrderId);
        List<MyAfterSaleVO> voList = myAfterSaleHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取我的售后列表成功.");
        view.addObject("data", voList);
        return view;
    }
}
