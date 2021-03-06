package com.qcloud.component.my.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.my.model.MyOrderForm;
import com.qcloud.component.my.model.query.MyOrderFormQuery;
import com.qcloud.component.my.service.MyAfterSaleService;
import com.qcloud.component.my.service.MyOrderFormService;
import com.qcloud.component.my.web.handler.MyOrderFormHandler;
import com.qcloud.component.my.web.vo.MyOrderFormListVO;
import com.qcloud.component.my.web.vo.MyOrderFormMerchandiseVO;
import com.qcloud.component.my.web.vo.MyOrderFormMerchantVO;
import com.qcloud.component.my.web.vo.MyOrderFormSimpleVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = MyOrderFormController.DIR)
public class MyOrderFormController {

    public static final String DIR = "/myOrderForm";

    @Autowired
    private MyOrderFormService myOrderFormService;

    @Autowired
    private MyOrderFormHandler myOrderFormHandler;

    @Autowired
    private MyAfterSaleService myAfterSaleService;

    @PiratesApp
    @RequestMapping
    public FrontPagingView query(HttpServletRequest request, MyOrderFormQuery query, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyOrderForm> list = myOrderFormService.list(query, user.getId(), pPage.getPageStart(), pPage.getPageSize());
        int size = myOrderFormService.count(query, user.getId());
        List<MyOrderFormSimpleVO> voList = myOrderFormHandler.toVOList4Simple(list);
        FrontPagingView view = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), size);
        view.setMessage("查询我的订单成功.");
        view.setList(voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView query4Merchandise(HttpServletRequest request, MyOrderFormQuery query, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyOrderForm> list = myOrderFormService.list(query, user.getId(), pPage.getPageStart(), pPage.getPageSize());
        List<MyOrderFormMerchandiseVO> voList = myOrderFormHandler.toVOList4Merchandise(list);
        int size = myOrderFormService.count(query, user.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的订单成功.");
        view.addObject("data", voList);
        view.addObject("size", size);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView query4Merchant(HttpServletRequest request, MyOrderFormQuery query, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyOrderForm> list = myOrderFormService.list(query, user.getId(), pPage.getPageStart(), pPage.getPageSize());
        List<MyOrderFormMerchantVO> voList = myOrderFormHandler.toVOList4Merchant(list);
        int size = myOrderFormService.count(query, user.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的订单成功.");
        view.addObject("data", voList);
        view.addObject("size", size);
        return view;
    }

    @RequestMapping
    public FrontAjaxView count(HttpServletRequest request, MyOrderFormQuery query) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        int number = myOrderFormService.count(query, user.getId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取我的优惠劵成功.");
        view.addObject("count", number);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView statMyOrder(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        FrontAjaxView view = new FrontAjaxView();
        for (Entry<Integer, String> entry : statOrderStateMap.entrySet()) {
            int number = myOrderFormService.statMyOrder(user.getId(), entry.getKey());
            view.addObject(entry.getValue(), number);
        }
        // for (int state : statOrderStates) {
        // int number = myOrderFormService.statMyOrder(user.getId(), state);
        // view.addObject(String.valueOf(state), number);
        // }
        // for (int state : statMerchantOrderStates) {
        // int number = myOrderFormService.statMyMerchantOrder(user.getId(), state);
        // view.addObject(String.valueOf(state), number);
        // }
        int number = myAfterSaleService.stat(user.getId());
        view.addObject("after", number);
        view.setMessage("查询我的订单数量成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontPagingView list(HttpServletRequest request, MyOrderFormQuery query, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyOrderForm> list = myOrderFormService.list(query, user.getId(), pPage.getPageStart(), pPage.getPageSize());
        int size = myOrderFormService.count(query, user.getId());
        List<MyOrderFormListVO> voList = myOrderFormHandler.toVOList4List(list);
        FrontPagingView view = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), size);
        view.setMessage("查询我的订单成功.");
        view.setList(voList);
        return view;
    }

    private static Map<Integer, String> statOrderStateMap       = new HashMap<Integer, String>();
    static {
        statOrderStateMap.put(1, "toPay");
        statOrderStateMap.put(2, "toPacking");
        statOrderStateMap.put(3, "toShip");
        statOrderStateMap.put(4, "toSign");
        statOrderStateMap.put(5, "finished");
    }

    private int[]                       statOrderStates         = new int[] { 1, 2, 3, 4, 5};

    private int[]                       statMerchantOrderStates = new int[] {};
}
