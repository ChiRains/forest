package com.qcloud.component.goods.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.goods.web.handler.UnifiedMerchandiseHandler;
		
@Controller
@RequestMapping(value = UnifiedMerchandiseController.DIR)
public class UnifiedMerchandiseController {
	
	public static final String DIR = "/unifiedMerchandise";
	
	@Autowired
	private UnifiedMerchandiseService unifiedMerchandiseService;
	@Autowired
	private UnifiedMerchandiseHandler unifiedMerchandiseHandler;

}
