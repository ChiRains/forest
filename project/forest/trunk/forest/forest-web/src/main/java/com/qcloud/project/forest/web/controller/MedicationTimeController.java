package com.qcloud.project.forest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.project.forest.service.MedicationTimeService;
import com.qcloud.project.forest.web.handler.MedicationTimeHandler;
		
@Controller
@RequestMapping(value = MedicationTimeController.DIR)
public class MedicationTimeController {
	
	public static final String DIR = "/medicationTime";
	
	@Autowired
	private MedicationTimeService medicationTimeService;
	@Autowired
	private MedicationTimeHandler medicationTimeHandler;

}
