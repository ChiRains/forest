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
import com.qcloud.project.forest.model.Activity;
import com.qcloud.project.forest.service.ActivityService;
import com.qcloud.project.forest.web.handler.ActivityHandler;
import com.qcloud.project.forest.model.query.ActivityQuery;
import com.qcloud.project.forest.web.vo.admin.AdminActivityVO;
		
@Controller
@RequestMapping(value = "/" + AdminActivityController.DIR)
public class AdminActivityController {
	
	public static final String DIR = "admin/activity";
	
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ActivityHandler activityHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, PPage pPage, ActivityQuery query) {
	    
		Page<Activity> page = activityService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminActivityVO> list = activityHandler.toVOList4Admin(page.getData());
		
	    String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   
		AcePagingView pagingView = new AcePagingView("/admin/forest-Activity-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/forest-Activity-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(Activity activity) {
		activityService.add(activity);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		Activity activity=activityService.get(id);
		AdminActivityVO adminActivityVO=activityHandler.toVO4Admin(activity);
		ModelAndView model = new ModelAndView("/admin/forest-Activity-edit");
		model.addObject("activity", adminActivityVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(Activity activity, String queryStr) {
		activityService.update(activity);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		activityService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
