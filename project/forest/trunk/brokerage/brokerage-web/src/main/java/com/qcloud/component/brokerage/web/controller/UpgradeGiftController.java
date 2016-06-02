package com.qcloud.component.brokerage.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.brokerage.service.UpgradeGiftService;
import com.qcloud.component.brokerage.web.handler.UpgradeGiftHandler;
		
@Controller
@RequestMapping(value = UpgradeGiftController.DIR)
public class UpgradeGiftController {
	
	public static final String DIR = "/upgradeGift";
	
	@Autowired
	private UpgradeGiftService upgradeGiftService;
	@Autowired
	private UpgradeGiftHandler upgradeGiftHandler;

}
