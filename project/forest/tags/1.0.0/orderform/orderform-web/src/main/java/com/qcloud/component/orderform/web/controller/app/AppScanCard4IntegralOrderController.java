//package com.qcloud.component.orderform.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.orderform.web.controller.ScanCard4IntegralOrderController;
//import com.qcloud.component.piratesship.web.form.ListForm;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.web.security.annotation.NoReferer;
//
//@Controller
//@RequestMapping(value = AppScanCard4IntegralOrderController.DIR)
//public class AppScanCard4IntegralOrderController {
//
//    public static final String               DIR = "/app/scanCard";
//
//    @Autowired
//    private ScanCard4IntegralOrderController scanCard4IntegralOrderController;
//
//    @RequestMapping
//    public FrontAjaxView getMembershipCard(HttpServletRequest request, String cardNumber) {
//
//        return scanCard4IntegralOrderController.getMembershipCard(request, cardNumber);
//    }
//
//    @RequestMapping
//    @NoReferer
//    public FrontAjaxView order4Integral(HttpServletRequest request, String cardNumber, Double sum, ListForm couponList) {
//
//        return scanCard4IntegralOrderController.order4Integral(request, cardNumber, sum, couponList);
//    }
//}
