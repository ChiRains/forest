//package com.qcloud.component.marketing.web.controller.app;
//
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.marketing.web.controller.CouponController;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//
//@Controller
//@RequestMapping(value = AppCouponController.DIR)
//public class AppCouponController {
//
//    public static final String DIR = "/app/coupon";
//
//    @Autowired
//    CouponController           couponController;
//
//    // 领取商城优惠劵
//    @RequestMapping
//    public FrontAjaxView extractCoupon(HttpServletRequest request, Long couponId) {
//
//        return couponController.extractCoupon(request, couponId);
//    }
//
//    // 能否领取优惠劵
//    @RequestMapping
//    public FrontAjaxView canExtract(HttpServletRequest request, Long couponId) {
//
//        return couponController.canExtract(request, couponId);
//    }
//
//    // 领取商家的优惠劵
//    @RequestMapping
//    public FrontAjaxView extractCouponItem(HttpServletRequest request, Long couponItemId) {
//
//        return couponController.extractCouponItem(request, couponItemId);
//    }
//}
