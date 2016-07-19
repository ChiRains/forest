package com.qcloud.component.my.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.my.service.MyToAppendEvaluationService;
import com.qcloud.component.my.web.handler.MyToAppendEvaluationHandler;
		
@Controller
@RequestMapping(value = MyToAppendEvaluationController.DIR)
public class MyToAppendEvaluationController {
	
	public static final String DIR = "/myToAppendEvaluation";
	
	@Autowired
	private MyToAppendEvaluationService myToAppendEvaluationService;
	@Autowired
	private MyToAppendEvaluationHandler myToAppendEvaluationHandler;

}
