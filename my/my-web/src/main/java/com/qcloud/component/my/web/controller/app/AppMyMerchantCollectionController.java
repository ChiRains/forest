//package com.qcloud.component.my.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.my.web.controller.MyMerchantCollectionController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.web.page.PPage;
//
//@Controller
//@RequestMapping(value = AppMyMerchantCollectionController.DIR)
//public class AppMyMerchantCollectionController {
//
//    public static final String     DIR = "/app/myMerchantCollection";
//
//    @Autowired
//    MyMerchantCollectionController myMerchantCollectionController;
//
//    @RequestMapping
//    public FrontAjaxView collect(HttpServletRequest request, Long merchantId) {
//
//        return myMerchantCollectionController.collect(request, merchantId);
//    }
//
//    @RequestMapping
//    public FrontAjaxView isCollect(HttpServletRequest request, Long merchantId) {
//
//        return myMerchantCollectionController.isCollect(request, merchantId);
//    }
//
//    @RequestMapping
//    public FrontAjaxView remove(HttpServletRequest request, Long id) {
//
//        return myMerchantCollectionController.remove(request, id);
//    }
//
//    @RequestMapping
//    public FrontAjaxView removeByMerchant(HttpServletRequest request, Long merchantId) {
//
//        return myMerchantCollectionController.removeByMerchant(request, merchantId);
//    }
//
//    @RequestMapping
//    public FrontAjaxView list(HttpServletRequest request, PPage pPage) {
//
//        return myMerchantCollectionController.list(request, pPage);
//    }
//}
