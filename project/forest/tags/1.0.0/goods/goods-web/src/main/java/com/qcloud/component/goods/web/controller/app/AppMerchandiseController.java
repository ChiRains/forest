//package com.qcloud.component.goods.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.goods.web.controller.MerchandiseController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//
//@Controller
//@RequestMapping(value = AppMerchandiseController.DIR)
//public class AppMerchandiseController {
//
//    public static final String    DIR = "/app/merchandise";
//
//    @Autowired
//    private MerchandiseController merchandiseController;
//
//    @RequestMapping
//    public FrontAjaxView listMySearch(HttpServletRequest request, Integer number) {
//
//        return merchandiseController.listMySearch(request, number);
//    }
//
//    @RequestMapping
//    public FrontAjaxView clearMySearch(HttpServletRequest request) {
//
//        return merchandiseController.clearMySearch(request);
//    }
//
//    @RequestMapping
//    public FrontAjaxView listShopRecommend(Long unifiedMerchandiseId, Integer number) {
//
//        return merchandiseController.listShopRecommend(unifiedMerchandiseId, number);
//    }
//}
