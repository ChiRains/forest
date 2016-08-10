package com.qcloud.project.forest.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.BpCalculation;
import com.qcloud.project.forest.service.BpCalculationService;
import com.qcloud.project.forest.web.handler.BpCalculationHandler;
import com.qcloud.project.forest.model.query.BpCalculationQuery;
import com.qcloud.project.forest.web.vo.admin.AdminBpCalculationVO;
		
@Controller
@RequestMapping(value = "/" + AdminBpCalculationController.DIR)
public class AdminBpCalculationController {
	
	public static final String DIR = "admin/bpCalculation";
	
	@Autowired
	private BpCalculationService bpCalculationService;
	@Autowired
	private BpCalculationHandler bpCalculationHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, PPage pPage, BpCalculationQuery query) {
	    
		Page<BpCalculation> page = bpCalculationService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminBpCalculationVO> list = bpCalculationHandler.toVOList4Admin(page.getData());
		
	    String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   
		AcePagingView pagingView = new AcePagingView("/admin/forest-BpCalculation-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/forest-BpCalculation-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(BpCalculation bpCalculation) {
		bpCalculationService.add(bpCalculation);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		BpCalculation bpCalculation=bpCalculationService.get(id);
		AdminBpCalculationVO adminBpCalculationVO=bpCalculationHandler.toVO4Admin(bpCalculation);
		ModelAndView model = new ModelAndView("/admin/forest-BpCalculation-edit");
		model.addObject("bpCalculation", adminBpCalculationVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(BpCalculation bpCalculation, String queryStr) {
		bpCalculationService.update(bpCalculation);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		bpCalculationService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
