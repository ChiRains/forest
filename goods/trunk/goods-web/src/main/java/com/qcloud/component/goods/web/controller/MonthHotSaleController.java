package com.qcloud.component.goods.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.goods.service.MonthHotSaleService;
import com.qcloud.component.goods.web.handler.MonthHotSaleHandler;
		
@Controller
@RequestMapping(value = MonthHotSaleController.DIR)
public class MonthHotSaleController {
	
	public static final String DIR = "/monthHotSale";
	
	@Autowired
	private MonthHotSaleService monthHotSaleService;
	@Autowired
	private MonthHotSaleHandler monthHotSaleHandler;

}
