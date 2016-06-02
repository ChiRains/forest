package com.qcloud.component.warehouse.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.warehouse.service.MerchandiseStockStateService;
import com.qcloud.component.warehouse.web.handler.MerchandiseStockStateHandler;
		
@Controller
@RequestMapping(value = MerchandiseStockStateController.DIR)
public class MerchandiseStockStateController {
	
	public static final String DIR = "/merchandiseStockState";
	
	@Autowired
	private MerchandiseStockStateService merchandiseStockStateService;
	@Autowired
	private MerchandiseStockStateHandler merchandiseStockStateHandler;

}
