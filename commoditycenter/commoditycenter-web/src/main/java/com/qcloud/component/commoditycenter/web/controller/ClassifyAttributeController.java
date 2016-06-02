package com.qcloud.component.commoditycenter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.commoditycenter.service.ClassifyAttributeService;
import com.qcloud.component.commoditycenter.web.handler.ClassifyAttributeHandler;
		
@Controller
@RequestMapping(value = ClassifyAttributeController.DIR)
public class ClassifyAttributeController {
	
	public static final String DIR = "/classifyAttribute";
	
	@Autowired
	private ClassifyAttributeService classifyAttributeService;
	@Autowired
	private ClassifyAttributeHandler classifyAttributeHandler;

}
