package com.qcloud.component.goods.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.goods.service.MerchandiseMarketingService;
import com.qcloud.component.goods.web.handler.MerchandiseMarketingHandler;
		
@Controller
@RequestMapping(value = MerchandiseMarketingController.DIR)
public class MerchandiseMarketingController {
	
	public static final String DIR = "/merchandiseMarketing";
	
	@Autowired
	private MerchandiseMarketingService merchandiseMarketingService;
	@Autowired
	private MerchandiseMarketingHandler merchandiseMarketingHandler;

}
