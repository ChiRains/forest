package com.qcloud.project.forest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.project.forest.service.GradeMerchandiseService;
import com.qcloud.project.forest.web.handler.GradeMerchandiseHandler;
		
@Controller
@RequestMapping(value = GradeMerchandiseController.DIR)
public class GradeMerchandiseController {
	
	public static final String DIR = "/gradeMerchandise";
	
	@Autowired
	private GradeMerchandiseService gradeMerchandiseService;
	@Autowired
	private GradeMerchandiseHandler gradeMerchandiseHandler;

}
