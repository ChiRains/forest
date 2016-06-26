package com.qcloud.component.goods.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.goods.service.MerchandiseEvaluationService;
import com.qcloud.component.goods.web.handler.MerchandiseEvaluationHandler;
		
@Controller
@RequestMapping(value = MerchandiseEvaluationController.DIR)
public class MerchandiseEvaluationController {
	
	public static final String DIR = "/merchandiseEvaluation";
	
	@Autowired
	private MerchandiseEvaluationService merchandiseEvaluationService;
	@Autowired
	private MerchandiseEvaluationHandler merchandiseEvaluationHandler;

}
