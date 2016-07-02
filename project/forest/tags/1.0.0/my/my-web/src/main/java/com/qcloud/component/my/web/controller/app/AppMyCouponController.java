//package com.qcloud.component.my.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.my.web.controller.MyCouponController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.web.page.PPage;
//
//@Controller
//@RequestMapping(value = AppMyCouponController.DIR)
//public class AppMyCouponController {
//
//    public static final String DIR = "/app/myCoupon";
//
//    @Autowired
//    MyCouponController         myCouponController;
//
//    @RequestMapping
//    public FrontAjaxView list(HttpServletRequest request, Integer type, PPage pPage) {
//
//        return myCouponController.list(request, type, pPage);
//    }
//
//    @RequestMapping
//    public FrontAjaxView listCanUse(HttpServletRequest request, Long merchantId, PPage pPage, Double sum) {
//
//        return myCouponController.listCanUse(request, merchantId, pPage, sum);
//    }
//
//    // 扫卡积分,那么只能是线下的商城券
//    @RequestMapping
//    public FrontAjaxView getByCode(String cardNumber, String code) {
//
//        return myCouponController.getByCode(cardNumber, code);
//    }
//
//    @RequestMapping
//    public FrontAjaxView delete(HttpServletRequest request, Long id) {
//
//        return myCouponController.delete(request, id);
//    }
//}
