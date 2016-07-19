package com.qcloud.project.forest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.project.forest.service.ActivityService;
import com.qcloud.project.forest.web.handler.ActivityHandler;
		
@Controller
@RequestMapping(value = ActivityController.DIR)
public class ActivityController {
	
	public static final String DIR = "/activity";
	
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ActivityHandler activityHandler;

}
