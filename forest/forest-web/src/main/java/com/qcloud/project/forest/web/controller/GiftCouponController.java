package com.qcloud.project.forest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.project.forest.service.GiftCouponService;
import com.qcloud.project.forest.web.handler.GiftCouponHandler;

@Controller
@RequestMapping(value = GiftCouponController.DIR)
public class GiftCouponController {

    public static final String DIR = "/giftCoupon";

    @Autowired
    private GiftCouponService  giftCouponService;

    @Autowired
    private GiftCouponHandler  giftCouponHandler;
}
