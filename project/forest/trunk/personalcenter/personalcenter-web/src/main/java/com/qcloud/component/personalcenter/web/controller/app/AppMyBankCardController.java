//package com.qcloud.component.personalcenter.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.personalcenter.web.controller.MyBankCardController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//
//@Controller
//@RequestMapping(value = AppMyBankCardController.DIR)
//public class AppMyBankCardController {
//
//    public static final String   DIR = "/app/myBankCard";
//
//    @Autowired
//    private MyBankCardController myBankCardController;
//
//    @RequestMapping
//    public FrontAjaxView list(HttpServletRequest request) {
//
//        return myBankCardController.list(request);
//    }
//
//    @RequestMapping
//    public FrontAjaxView add(HttpServletRequest request, String bank, String card,String cardholder,String mobile) {
//
//        return myBankCardController.add(request, bank, card,cardholder,mobile);
//    }
//
//    @RequestMapping
//    public FrontAjaxView delete(HttpServletRequest request, Long id) {
//
//        return myBankCardController.delete(request, id);
//    }
//}
