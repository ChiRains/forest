package com.qcloud.component.marketing.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.marketing.service.FullReducesService;
import com.qcloud.component.marketing.web.handler.FullReducesHandler;
		
@Controller
@RequestMapping(value = FullReducesController.DIR)
public class FullReducesController {
	
	public static final String DIR = "/fullReduces";
	
	@Autowired
	private FullReducesService fullReducesService;
	@Autowired
	private FullReducesHandler fullReducesHandler;

}
