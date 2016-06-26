package com.qcloud.component.goods.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.goods.service.MerchandiseBrowsingHistoryService;
import com.qcloud.component.goods.web.handler.MerchandiseBrowsingHistoryHandler;
		
@Controller
@RequestMapping(value = MerchandiseBrowsingHistoryController.DIR)
public class MerchandiseBrowsingHistoryController {
	
	public static final String DIR = "/merchandiseBrowsingHistory";
	
	@Autowired
	private MerchandiseBrowsingHistoryService merchandiseBrowsingHistoryService;
	@Autowired
	private MerchandiseBrowsingHistoryHandler merchandiseBrowsingHistoryHandler;

}
