package com.qcloud.project.forest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.project.forest.service.RangeGradeService;
import com.qcloud.project.forest.web.handler.RangeGradeHandler;
		
@Controller
@RequestMapping(value = RangeGradeController.DIR)
public class RangeGradeController {
	
	public static final String DIR = "/rangeGrade";
	
	@Autowired
	private RangeGradeService rangeGradeService;
	@Autowired
	private RangeGradeHandler rangeGradeHandler;

}
