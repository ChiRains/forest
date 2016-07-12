package com.qcloud.project.forest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.project.forest.service.ConfigService;
import com.qcloud.project.forest.web.handler.ConfigHandler;
		
@Controller
@RequestMapping(value = ConfigController.DIR)
public class ConfigController {
	
	public static final String DIR = "/config";
	
	@Autowired
	private ConfigService configService;
	@Autowired
	private ConfigHandler configHandler;

}
