package com.qcloud.component.commoditycenter.web.controller.admin;

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
import com.qcloud.component.commoditycenter.model.UnifiedMerchandise;
import com.qcloud.component.commoditycenter.service.UnifiedMerchandiseService;
import com.qcloud.component.commoditycenter.web.handler.UnifiedMerchandiseHandler;
import com.qcloud.component.commoditycenter.model.query.UnifiedMerchandiseQuery;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminUnifiedMerchandiseVO;
		
@Controller
@RequestMapping(value = "/" + AdminUnifiedMerchandiseController.DIR)
public class AdminUnifiedMerchandiseController {
	
	public static final String DIR = "admin/unifiedMerchandise";
	
	@Autowired
	private UnifiedMerchandiseService unifiedMerchandiseService;
	@Autowired
	private UnifiedMerchandiseHandler unifiedMerchandiseHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, UnifiedMerchandiseQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<UnifiedMerchandise> page = unifiedMerchandiseService.page(query, start, PAGE_SIZE);
		List<AdminUnifiedMerchandiseVO> list = unifiedMerchandiseHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/commoditycenter-UnifiedMerchandise-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/commoditycenter-UnifiedMerchandise-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(UnifiedMerchandise unifiedMerchandise) {
		unifiedMerchandiseService.add(unifiedMerchandise);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		UnifiedMerchandise unifiedMerchandise=unifiedMerchandiseService.get(id);
		AdminUnifiedMerchandiseVO adminUnifiedMerchandiseVO=unifiedMerchandiseHandler.toVO4Admin(unifiedMerchandise);
		ModelAndView model = new ModelAndView("/admin/commoditycenter-UnifiedMerchandise-edit");
		model.addObject("unifiedMerchandise", adminUnifiedMerchandiseVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(UnifiedMerchandise unifiedMerchandise) {
		unifiedMerchandiseService.update(unifiedMerchandise);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		unifiedMerchandiseService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
