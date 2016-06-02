package com.qcloud.component.personalcenter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.personalcenter.service.MySignInRecordService;
import com.qcloud.component.personalcenter.web.handler.MySignInRecordHandler;
		
@Controller
@RequestMapping(value = MySignInRecordController.DIR)
public class MySignInRecordController {
	
	public static final String DIR = "/mySignInRecord";
	
	@Autowired
	private MySignInRecordService mySignInRecordService;
	@Autowired
	private MySignInRecordHandler mySignInRecordHandler;

}
