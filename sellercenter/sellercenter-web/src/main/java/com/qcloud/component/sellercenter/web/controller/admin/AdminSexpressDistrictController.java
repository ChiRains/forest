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
import com.qcloud.component.sellercenter.model.SexpressDistrict;
import com.qcloud.component.sellercenter.service.SexpressDistrictService;
import com.qcloud.component.sellercenter.web.handler.SexpressDistrictHandler;
import com.qcloud.component.sellercenter.model.query.SexpressDistrictQuery;
import com.qcloud.component.sellercenter.web.vo.admin.AdminSexpressDistrictVO;
		
@Controller
@RequestMapping(value = "/" + AdminSexpressDistrictController.DIR)
public class AdminSexpressDistrictController {
	
	public static final String DIR = "admin/sexpressDistrict";
	
	@Autowired
	private SexpressDistrictService sexpressDistrictService;
	@Autowired
	private SexpressDistrictHandler sexpressDistrictHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, SexpressDistrictQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<SexpressDistrict> page = sexpressDistrictService.page(query, start, PAGE_SIZE);
		List<AdminSexpressDistrictVO> list = sexpressDistrictHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/sellercenter-SexpressDistrict-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/sellercenter-SexpressDistrict-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(SexpressDistrict sexpressDistrict) {
		sexpressDistrictService.add(sexpressDistrict);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		SexpressDistrict sexpressDistrict=sexpressDistrictService.get(id);
		AdminSexpressDistrictVO adminSexpressDistrictVO=sexpressDistrictHandler.toVO4Admin(sexpressDistrict);
		ModelAndView model = new ModelAndView("/admin/sellercenter-SexpressDistrict-edit");
		model.addObject("sexpressDistrict", adminSexpressDistrictVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(SexpressDistrict sexpressDistrict) {
		sexpressDistrictService.update(sexpressDistrict);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		sexpressDistrictService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
