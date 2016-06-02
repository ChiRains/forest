package com.qcloud.component.warehouse.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.warehouse.service.MerchandiseStockService;
import com.qcloud.component.warehouse.web.handler.MerchandiseStockHandler;
		
@Controller
@RequestMapping(value = MerchandiseStockController.DIR)
public class MerchandiseStockController {
	
	public static final String DIR = "/merchandiseStock";
	
	@Autowired
	private MerchandiseStockService merchandiseStockService;
	@Autowired
	private MerchandiseStockHandler merchandiseStockHandler;

}
