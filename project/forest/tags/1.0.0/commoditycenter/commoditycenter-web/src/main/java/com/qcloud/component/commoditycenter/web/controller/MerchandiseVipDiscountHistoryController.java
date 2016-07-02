package com.qcloud.component.commoditycenter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.commoditycenter.service.MerchandiseVipDiscountHistoryService;
import com.qcloud.component.commoditycenter.web.handler.MerchandiseVipDiscountHistoryHandler;
		
@Controller
@RequestMapping(value = MerchandiseVipDiscountHistoryController.DIR)
public class MerchandiseVipDiscountHistoryController {
	
	public static final String DIR = "/merchandiseVipDiscountHistory";
	
	@Autowired
	private MerchandiseVipDiscountHistoryService merchandiseVipDiscountHistoryService;
	@Autowired
	private MerchandiseVipDiscountHistoryHandler merchandiseVipDiscountHistoryHandler;

}
