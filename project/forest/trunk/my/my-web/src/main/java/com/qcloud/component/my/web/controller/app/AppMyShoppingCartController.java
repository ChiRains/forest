//package com.qcloud.component.my.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.my.web.controller.MyShoppingCartController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.web.page.PPage;
//
//@Controller
//@RequestMapping(value = AppMyShoppingCartController.DIR)
//public class AppMyShoppingCartController {
//
//    public static final String DIR = "/app/myShoppingCart";
//
//    @Autowired
//    MyShoppingCartController   myShoppingCartController;
//
//    @RequestMapping
//    public FrontAjaxView clear(HttpServletRequest request) {
//
//        return myShoppingCartController.clear(request);
//    }
//
//    @RequestMapping
//    public FrontAjaxView reduce(HttpServletRequest request, Long unifiedMerchandiseId, Integer number) {
//
//        return myShoppingCartController.reduce(request, unifiedMerchandiseId, number);
//    }
//
//    @RequestMapping
//    public FrontAjaxView add(HttpServletRequest request, Long unifiedMerchandiseId, Integer number) {
//
//        return myShoppingCartController.add(request, unifiedMerchandiseId, number);
//    }
//
//    @RequestMapping
//    public FrontAjaxView remove(HttpServletRequest request, Long unifiedMerchandiseId) {
//
//        return myShoppingCartController.remove(request, unifiedMerchandiseId);
//    }
//
//    @RequestMapping
//    public FrontAjaxView listAll(HttpServletRequest request) {
//
//        return myShoppingCartController.listAll(request);
//    }
//
//    @RequestMapping
//    public FrontAjaxView list(HttpServletRequest request, PPage pPage) {
//
//        return myShoppingCartController.list(request, pPage);
//    }
//
//    @RequestMapping
//    public FrontAjaxView listAll4Merchant(HttpServletRequest request) {
//
//        return myShoppingCartController.listAll4Merchant(request);
//    }
//
//    @RequestMapping
//    public FrontAjaxView list4Merchant(HttpServletRequest request, PPage pPage) {
//
//        return myShoppingCartController.list4Merchant(request, pPage);
//    }
//
//    @RequestMapping
//    public FrontAjaxView listAll4Classify(HttpServletRequest request) {
//
//        return myShoppingCartController.listAll4Classify(request);
//    }
//
//    @RequestMapping
//    public FrontAjaxView list4Classify(HttpServletRequest request, PPage pPage) {
//
//        return myShoppingCartController.list4Classify(request, pPage);
//    }
//}
