//package com.qcloud.component.my.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.my.web.controller.MyAfterSaleController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.web.page.PPage;
//
//@Controller
//@RequestMapping(value = AppMyAfterSaleController.DIR)
//public class AppMyAfterSaleController {
//
//    public static final String    DIR = "/app/myAfterSale";
//
//    @Autowired
//    private MyAfterSaleController myAfterSaleController;
//
//    @RequestMapping
//    public FrontAjaxView list(HttpServletRequest request, PPage pPage) {
//
//        return myAfterSaleController.list(request, pPage);
//    }
//}
