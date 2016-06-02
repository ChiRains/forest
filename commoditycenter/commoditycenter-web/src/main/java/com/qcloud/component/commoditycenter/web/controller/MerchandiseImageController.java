package com.qcloud.component.commoditycenter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qcloud.component.commoditycenter.service.MerchandiseImageService;
import com.qcloud.component.commoditycenter.web.handler.MerchandiseImageHandler;
		
@Controller
@RequestMapping(value = MerchandiseImageController.DIR)
public class MerchandiseImageController {
	
	public static final String DIR = "/merchandiseImage";
	
	@Autowired
	private MerchandiseImageService merchandiseImageService;
	@Autowired
	private MerchandiseImageHandler merchandiseImageHandler;

}
