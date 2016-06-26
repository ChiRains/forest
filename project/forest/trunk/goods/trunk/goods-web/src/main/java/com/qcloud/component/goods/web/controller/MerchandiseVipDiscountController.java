package com.qcloud.component.goods.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.goods.service.MerchandiseVipDiscountService;
import com.qcloud.component.goods.web.handler.MerchandiseVipDiscountHandler;
		
@Controller
@RequestMapping(value = MerchandiseVipDiscountController.DIR)
public class MerchandiseVipDiscountController {
	
	public static final String DIR = "/merchandiseVipDiscount";
	
	@Autowired
	private MerchandiseVipDiscountService merchandiseVipDiscountService;
	@Autowired
	private MerchandiseVipDiscountHandler merchandiseVipDiscountHandler;

}
