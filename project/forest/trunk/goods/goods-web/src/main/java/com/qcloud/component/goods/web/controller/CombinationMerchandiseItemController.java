package com.qcloud.component.goods.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.goods.service.CombinationMerchandiseItemService;
import com.qcloud.component.goods.web.handler.CombinationMerchandiseItemHandler;
		
@Controller
@RequestMapping(value = CombinationMerchandiseItemController.DIR)
public class CombinationMerchandiseItemController {
	
	public static final String DIR = "/combinationMerchandiseItem";
	
	@Autowired
	private CombinationMerchandiseItemService combinationMerchandiseItemService;
	@Autowired
	private CombinationMerchandiseItemHandler combinationMerchandiseItemHandler;

}
