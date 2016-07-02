package com.qcloud.component.my.web.controller.admin;

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
import com.qcloud.component.my.model.SearchHistory;
import com.qcloud.component.my.service.SearchHistoryService;
import com.qcloud.component.my.web.handler.SearchHistoryHandler;
import com.qcloud.component.my.model.query.SearchHistoryQuery;
import com.qcloud.component.my.web.vo.admin.AdminSearchHistoryVO;
		
@Controller
@RequestMapping(value = "/" + AdminSearchHistoryController.DIR)
public class AdminSearchHistoryController {
	
	public static final String DIR = "admin/mySearchHistory";
	
	@Autowired
	private SearchHistoryService searchHistoryService;
	@Autowired
	private SearchHistoryHandler searchHistoryHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, SearchHistoryQuery query) {
		
		Page<SearchHistory> page = searchHistoryService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminSearchHistoryVO> list = searchHistoryHandler.toVOList4Admin(page.getData());
				
		String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
	    String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   	    
		AcePagingView pagingView = new AcePagingView("/admin/my-SearchHistory-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/my-SearchHistory-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(SearchHistory searchHistory) {
		searchHistoryService.add(searchHistory);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		SearchHistory searchHistory=searchHistoryService.get(id);
		AdminSearchHistoryVO adminSearchHistoryVO=searchHistoryHandler.toVO4Admin(searchHistory);
		ModelAndView model = new ModelAndView("/admin/my-SearchHistory-edit");
		model.addObject("searchHistory", adminSearchHistoryVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(SearchHistory searchHistory, String queryStr) {
		searchHistoryService.update(searchHistory);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		searchHistoryService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
