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
import com.qcloud.component.brokerage.model.DataPool;
import com.qcloud.component.brokerage.service.DataPoolService;
import com.qcloud.component.brokerage.web.handler.DataPoolHandler;
import com.qcloud.component.brokerage.model.query.DataPoolQuery;
import com.qcloud.component.brokerage.web.vo.admin.AdminDataPoolVO;
		
@Controller
@RequestMapping(value = "/" + AdminDataPoolController.DIR)
public class AdminDataPoolController {
	
	public static final String DIR = "admin/dataPool";
	
	@Autowired
	private DataPoolService dataPoolService;
	@Autowired
	private DataPoolHandler dataPoolHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, DataPoolQuery query) {
		
		Page<DataPool> page = dataPoolService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminDataPoolVO> list = dataPoolHandler.toVOList4Admin(page.getData());
				
		String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
	    String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   	    
		AcePagingView pagingView = new AcePagingView("/admin/brokerage-DataPool-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/brokerage-DataPool-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(DataPool dataPool) {
		dataPoolService.add(dataPool);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		DataPool dataPool=dataPoolService.get(id);
		AdminDataPoolVO adminDataPoolVO=dataPoolHandler.toVO4Admin(dataPool);
		ModelAndView model = new ModelAndView("/admin/brokerage-DataPool-edit");
		model.addObject("dataPool", adminDataPoolVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(DataPool dataPool, String queryStr) {
		dataPoolService.update(dataPool);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		dataPoolService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
