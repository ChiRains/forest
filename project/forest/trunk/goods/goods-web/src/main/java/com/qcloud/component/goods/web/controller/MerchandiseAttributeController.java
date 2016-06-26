package com.qcloud.component.goods.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.goods.service.MerchandiseAttributeService;
import com.qcloud.component.goods.web.handler.MerchandiseAttributeHandler;
		
@Controller
@RequestMapping(value = MerchandiseAttributeController.DIR)
public class MerchandiseAttributeController {
	
	public static final String DIR = "/merchandiseAttribute";
	
	@Autowired
	private MerchandiseAttributeService merchandiseAttributeService;
	@Autowired
	private MerchandiseAttributeHandler merchandiseAttributeHandler;

}
