package com.qcloud.component.goods.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.goods.service.MerchandiseVipDiscountDateService;
import com.qcloud.component.goods.web.handler.MerchandiseVipDiscountDateHandler;
		
@Controller
@RequestMapping(value = MerchandiseVipDiscountDateController.DIR)
public class MerchandiseVipDiscountDateController {
	
	public static final String DIR = "/merchandiseVipDiscountDate";
	
	@Autowired
	private MerchandiseVipDiscountDateService merchandiseVipDiscountDateService;
	@Autowired
	private MerchandiseVipDiscountDateHandler merchandiseVipDiscountDateHandler;

}
