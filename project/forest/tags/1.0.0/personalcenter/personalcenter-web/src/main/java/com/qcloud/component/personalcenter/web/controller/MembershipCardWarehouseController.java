package com.qcloud.component.personalcenter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.personalcenter.service.MembershipCardWarehouseService;
import com.qcloud.component.personalcenter.web.handler.MembershipCardWarehouseHandler;
		
@Controller
@RequestMapping(value = MembershipCardWarehouseController.DIR)
public class MembershipCardWarehouseController {
	
	public static final String DIR = "/membershipCardWarehouse";
	
	@Autowired
	private MembershipCardWarehouseService membershipCardWarehouseService;
	@Autowired
	private MembershipCardWarehouseHandler membershipCardWarehouseHandler;

}
