package com.qcloud.component.goods.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.goods.service.AttributeDefinitionService;
import com.qcloud.component.goods.web.handler.AttributeDefinitionHandler;
		
@Controller
@RequestMapping(value = AttributeDefinitionController.DIR)
public class AttributeDefinitionController {
	
	public static final String DIR = "/attributeDefinition";
	
	@Autowired
	private AttributeDefinitionService attributeDefinitionService;
	@Autowired
	private AttributeDefinitionHandler attributeDefinitionHandler;

}
