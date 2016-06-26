//package com.qcloud.component.goods.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.goods.web.controller.MerchandiseBrowsingHistoryController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.web.page.PPage;
//
//@Controller
//@RequestMapping(value = AppMerchandiseBrowsingHistoryController.DIR)
//public class AppMerchandiseBrowsingHistoryController {
//
//    public static final String           DIR = "/app/merchandiseBrowsingHistory";
//
//    @Autowired
//    MerchandiseBrowsingHistoryController merchandiseBrowsingHistoryController;
//
//    @RequestMapping
//    public FrontAjaxView list(HttpServletRequest request, PPage pPage) {
//
//        return merchandiseBrowsingHistoryController.list(request, pPage);
//    }
//}
