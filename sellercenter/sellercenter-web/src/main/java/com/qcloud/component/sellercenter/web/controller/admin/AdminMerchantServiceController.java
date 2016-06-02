package com.qcloud.component.sellercenter.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.component.sellercenter.model.MerchantService;
import com.qcloud.component.sellercenter.service.MerchantServiceService;
import com.qcloud.component.sellercenter.web.handler.MerchantServiceHandler;
import com.qcloud.component.sellercenter.model.query.MerchantServiceQuery;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantServiceVO;
		
@Controller
@RequestMapping(value = "/" + AdminMerchantServiceController.DIR)
public class AdminMerchantServiceController {
	
	public static final String DIR = "admin/merchantService";
	
	@Autowired
	private MerchantServiceService merchantServiceService;
	@Autowired
	private MerchantServiceHandler merchantServiceHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, MerchantServiceQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<MerchantService> page = merchantServiceService.page(query, start, PAGE_SIZE);
		List<AdminMerchantServiceVO> list = merchantServiceHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/sellercenter-MerchantService-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantService-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MerchantService merchantService) {
		merchantServiceService.add(merchantService);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MerchantService merchantService=merchantServiceService.get(id);
		AdminMerchantServiceVO adminMerchantServiceVO=merchantServiceHandler.toVO4Admin(merchantService);
		ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantService-edit");
		model.addObject("merchantService", adminMerchantServiceVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MerchantService merchantService) {
		merchantServiceService.update(merchantService);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		merchantServiceService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
