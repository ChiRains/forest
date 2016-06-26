package com.qcloud.component.goods.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.goods.service.MerchandiseSpecificationsService;
import com.qcloud.component.goods.web.handler.MerchandiseSpecificationsHandler;
		
@Controller
@RequestMapping(value = MerchandiseSpecificationsController.DIR)
public class MerchandiseSpecificationsController {
	
	public static final String DIR = "/merchandiseSpecifications";
	
	@Autowired
	private MerchandiseSpecificationsService merchandiseSpecificationsService;
	@Autowired
	private MerchandiseSpecificationsHandler merchandiseSpecificationsHandler;

}
