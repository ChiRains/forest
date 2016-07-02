package com.qcloud.component.marketing.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.marketing.service.CouponItemsService;
import com.qcloud.component.marketing.web.handler.CouponItemsHandler;
		
@Controller
@RequestMapping(value = CouponItemsController.DIR)
public class CouponItemsController {
	
	public static final String DIR = "/couponItems";
	
	@Autowired
	private CouponItemsService couponItemsService;
	@Autowired
	private CouponItemsHandler couponItemsHandler;

}
