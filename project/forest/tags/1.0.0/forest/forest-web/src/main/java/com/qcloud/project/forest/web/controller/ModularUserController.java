package com.qcloud.project.forest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.project.forest.service.ModularUserService;
import com.qcloud.project.forest.web.handler.ModularUserHandler;
		
@Controller
@RequestMapping(value = ModularUserController.DIR)
public class ModularUserController {
	
	public static final String DIR = "/modularUser";
	
	@Autowired
	private ModularUserService modularUserService;
	@Autowired
	private ModularUserHandler modularUserHandler;

}
