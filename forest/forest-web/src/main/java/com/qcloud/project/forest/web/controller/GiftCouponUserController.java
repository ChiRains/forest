package com.qcloud.project.forest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.project.forest.service.GiftCouponUserService;
import com.qcloud.project.forest.web.handler.GiftCouponUserHandler;
		
@Controller
@RequestMapping(value = GiftCouponUserController.DIR)
public class GiftCouponUserController {
	
	public static final String DIR = "/giftCouponUser";
	
	@Autowired
	private GiftCouponUserService giftCouponUserService;
	@Autowired
	private GiftCouponUserHandler giftCouponUserHandler;

}
