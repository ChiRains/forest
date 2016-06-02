//package com.qcloud.component.orderform.web.controller.app;
//
//import java.util.Date;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.orderform.web.controller.PayController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.web.security.annotation.NoReferer;
//
//@Controller
//@RequestMapping(value = AppPayController.DIR)
//public class AppPayController {
//
//    public static final String DIR = "/app/pay";
//
//    @Autowired
//    PayController              payController;
//
//    @RequestMapping
//    @NoReferer
//    public FrontAjaxView prepareAlipayPay(HttpServletRequest request, Long orderId, Date orderDate) {
//
//        return payController.prepareAlipayPay(request, orderId, orderDate);
//    }
//}
