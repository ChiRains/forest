package com.qcloud.component.goods.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.goods.service.MerchandiseDealRecordService;
import com.qcloud.component.goods.web.handler.MerchandiseDealRecordHandler;
		
@Controller
@RequestMapping(value = MerchandiseDealRecordController.DIR)
public class MerchandiseDealRecordController {
	
	public static final String DIR = "/merchandiseDealRecord";
	
	@Autowired
	private MerchandiseDealRecordService merchandiseDealRecordService;
	@Autowired
	private MerchandiseDealRecordHandler merchandiseDealRecordHandler;

}
