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
import com.qcloud.component.sellercenter.model.MerchantPay;
import com.qcloud.component.sellercenter.service.MerchantPayService;
import com.qcloud.component.sellercenter.web.handler.MerchantPayHandler;
import com.qcloud.component.sellercenter.model.query.MerchantPayQuery;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantPayVO;
		
@Controller
@RequestMapping(value = "/" + AdminMerchantPayController.DIR)
public class AdminMerchantPayController {
	
	public static final String DIR = "admin/merchantPay";
	
	@Autowired
	private MerchantPayService merchantPayService;
	@Autowired
	private MerchantPayHandler merchantPayHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, MerchantPayQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<MerchantPay> page = merchantPayService.page(query, start, PAGE_SIZE);
		List<AdminMerchantPayVO> list = merchantPayHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/sellercenter-MerchantPay-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantPay-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MerchantPay merchantPay) {
		merchantPayService.add(merchantPay);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MerchantPay merchantPay=merchantPayService.get(id);
		AdminMerchantPayVO adminMerchantPayVO=merchantPayHandler.toVO4Admin(merchantPay);
		ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantPay-edit");
		model.addObject("merchantPay", adminMerchantPayVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MerchantPay merchantPay) {
		merchantPayService.update(merchantPay);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		merchantPayService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
