package com.qcloud.project.forest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.project.forest.service.UserdbService;
import com.qcloud.project.forest.web.handler.UserdbHandler;
		
@Controller
@RequestMapping(value = UserdbController.DIR)
public class UserdbController {
	
	public static final String DIR = "/userdb";
	
	@Autowired
	private UserdbService userdbService;
	@Autowired
	private UserdbHandler userdbHandler;

}
