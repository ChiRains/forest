//package com.qcloud.component.sellercenter.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.sellercenter.web.controller.DistributeMembershipCardController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//
//@Controller
//@RequestMapping(value = AppDistributeMembershipCardController.DIR)
//public class AppDistributeMembershipCardController {
//
//    public static final String         DIR = "/app/distributeMembershipCard";
//
//    @Autowired
//    DistributeMembershipCardController distributeMembershipCardController;
//
//    @RequestMapping
//    public FrontAjaxView getToDistributeCard(HttpServletRequest request, String cardNumber) {
//
//        return distributeMembershipCardController.getToSendCard(request, cardNumber);
//    }
//
//    @RequestMapping
//    public FrontAjaxView sendSmsMsg4DistributeCard(HttpServletRequest request, String mobile) {
//
//        return distributeMembershipCardController.sendSmsMsg4DistributeCard(request, mobile);
//    }
//
//    @RequestMapping
//    public FrontAjaxView distributeCard(HttpServletRequest request, String cardNumber, String mobile, String name, Integer sex, String code) {
//
//        return distributeMembershipCardController.distributeCard(request, cardNumber, mobile, name, sex, code);
//    }
//}
