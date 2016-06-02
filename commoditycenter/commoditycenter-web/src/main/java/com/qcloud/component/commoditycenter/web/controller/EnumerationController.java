package com.qcloud.component.commoditycenter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.commoditycenter.service.EnumerationService;
import com.qcloud.component.commoditycenter.web.handler.EnumerationHandler;
		
@Controller
@RequestMapping(value = EnumerationController.DIR)
public class EnumerationController {
	
	public static final String DIR = "/enumeration";
	
	@Autowired
	private EnumerationService enumerationService;
	@Autowired
	private EnumerationHandler enumerationHandler;

}
