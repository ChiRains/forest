//package com.qcloud.component.my.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.my.model.query.MyOrderFormQuery;
//import com.qcloud.component.my.web.controller.MyOrderFormController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.web.page.PPage;
//
//@Controller
//@RequestMapping(value = AppMyOrderFormController.DIR)
//public class AppMyOrderFormController {
//
//    public static final String DIR = "/app/myOrderForm";
//
//    @Autowired
//    MyOrderFormController      myOrderFormController;
//
//    @RequestMapping
//    public FrontAjaxView query(HttpServletRequest request, MyOrderFormQuery query, PPage pPage) {
//
//        return myOrderFormController.query(request, query, pPage);
//    }
//
//    @RequestMapping
//    public FrontAjaxView query4Merchandise(HttpServletRequest request, MyOrderFormQuery query, PPage pPage) {
//
//        return myOrderFormController.query4Merchandise(request, query, pPage);
//    }
//
//    @RequestMapping
//    public FrontAjaxView query4Merchant(HttpServletRequest request, MyOrderFormQuery query, PPage pPage) {
//
//        return myOrderFormController.query4Merchant(request, query, pPage);
//    }
//
//    @RequestMapping
//    public FrontAjaxView statMyOrder(HttpServletRequest request) {
//
//        return myOrderFormController.statMyOrder(request);
//    }
//}
