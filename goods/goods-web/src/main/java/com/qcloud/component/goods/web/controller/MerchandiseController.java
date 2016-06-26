package com.qcloud.component.goods.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.goods.service.MerchandiseService;
import com.qcloud.component.goods.web.handler.MerchandiseHandler;
		
@Controller
@RequestMapping(value = MerchandiseController.DIR)
public class MerchandiseController {
	
	public static final String DIR = "/merchandise";
	
	@Autowired
	private MerchandiseService merchandiseService;
	@Autowired
	private MerchandiseHandler merchandiseHandler;

}
