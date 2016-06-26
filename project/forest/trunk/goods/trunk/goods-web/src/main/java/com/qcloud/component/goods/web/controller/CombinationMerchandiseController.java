package com.qcloud.component.goods.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.goods.service.CombinationMerchandiseService;
import com.qcloud.component.goods.web.handler.CombinationMerchandiseHandler;
		
@Controller
@RequestMapping(value = CombinationMerchandiseController.DIR)
public class CombinationMerchandiseController {
	
	public static final String DIR = "/combinationMerchandise";
	
	@Autowired
	private CombinationMerchandiseService combinationMerchandiseService;
	@Autowired
	private CombinationMerchandiseHandler combinationMerchandiseHandler;

}
