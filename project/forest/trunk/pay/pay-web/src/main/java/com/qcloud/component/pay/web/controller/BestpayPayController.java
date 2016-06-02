package com.qcloud.component.pay.web.controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.pay.service.UnifiedPayService;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.security.annotation.NoReferer;

// 电信翼支付
@Controller
@RequestMapping(value = BestpayPayController.DIR)
public class BestpayPayController {

    public static final String DIR = "/bestpay";

    @Autowired
    private UnifiedPayService  unifiedPayService;

    @RequestMapping
    @NoReferer
    public FrontAjaxView preparePay4WeiXin(HttpServletRequest request, Long orderId, Date orderDate) {

        unifiedPayService.requestBestPay4WeiXin();
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取支付数据准备支付成功.");
        return view;
    }

    @RequestMapping
    @NoReferer
    public FrontAjaxView paied4WeiXin(HttpServletRequest request, String return_code, String return_msg) {

        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("支付成功.");
        return view;
    }

    @RequestMapping
    @NoReferer
    public FrontAjaxView preparePay4Web(HttpServletRequest request, Long orderId, Date orderDate) {

        unifiedPayService.requestBestPay4Web();
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取支付数据准备支付成功.");
        return view;
    }

    @RequestMapping
    @NoReferer
    public FrontAjaxView paied4Web(HttpServletRequest request, String return_code, String return_msg) {

        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("支付成功.");
        return view;
    }

    @RequestMapping
    @NoReferer
    public FrontAjaxView preparePay4App(HttpServletRequest request, Long orderId, Date orderDate) {

        unifiedPayService.requestBestPay4App();
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取支付数据准备支付成功.");
        return view;
    }

    @RequestMapping
    @NoReferer
    public FrontAjaxView paied4App(HttpServletRequest request, String return_code, String return_msg) {

        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("支付成功.");
        return view;
    }
}
