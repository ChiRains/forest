package com.qcloud.component.goods.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.goods.service.ClassifySpecificationsService;
import com.qcloud.component.goods.web.handler.ClassifySpecificationsHandler;
		
@Controller
@RequestMapping(value = ClassifySpecificationsController.DIR)
public class ClassifySpecificationsController {
	
	public static final String DIR = "/classifySpecifications";
	
	@Autowired
	private ClassifySpecificationsService classifySpecificationsService;
	@Autowired
	private ClassifySpecificationsHandler classifySpecificationsHandler;

}
