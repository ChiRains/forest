package com.qcloud.component.brokerage.web.controller.admin;

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
import com.qcloud.component.brokerage.model.FormulaCalculationResult;
import com.qcloud.component.brokerage.service.FormulaCalculationResultService;
import com.qcloud.component.brokerage.web.handler.FormulaCalculationResultHandler;
import com.qcloud.component.brokerage.model.query.FormulaCalculationResultQuery;
import com.qcloud.component.brokerage.web.vo.admin.AdminFormulaCalculationResultVO;
		
@Controller
@RequestMapping(value = "/" + AdminFormulaCalculationResultController.DIR)
public class AdminFormulaCalculationResultController {
	
	public static final String DIR = "admin/formulaCalculationResult";
	
	@Autowired
	private FormulaCalculationResultService formulaCalculationResultService;
	@Autowired
	private FormulaCalculationResultHandler formulaCalculationResultHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, FormulaCalculationResultQuery query) {
		
		Page<FormulaCalculationResult> page = formulaCalculationResultService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminFormulaCalculationResultVO> list = formulaCalculationResultHandler.toVOList4Admin(page.getData());
				
		String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
	    String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   	    
		AcePagingView pagingView = new AcePagingView("/admin/brokerage-FormulaCalculationResult-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/brokerage-FormulaCalculationResult-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(FormulaCalculationResult formulaCalculationResult) {
		formulaCalculationResultService.add(formulaCalculationResult);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		FormulaCalculationResult formulaCalculationResult=formulaCalculationResultService.get(id);
		AdminFormulaCalculationResultVO adminFormulaCalculationResultVO=formulaCalculationResultHandler.toVO4Admin(formulaCalculationResult);
		ModelAndView model = new ModelAndView("/admin/brokerage-FormulaCalculationResult-edit");
		model.addObject("formulaCalculationResult", adminFormulaCalculationResultVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(FormulaCalculationResult formulaCalculationResult, String queryStr) {
		formulaCalculationResultService.update(formulaCalculationResult);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		formulaCalculationResultService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
