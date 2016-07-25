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
import com.qcloud.project.forest.model.GradeMerchandise;
import com.qcloud.project.forest.service.GradeMerchandiseService;
import com.qcloud.project.forest.web.handler.GradeMerchandiseHandler;
import com.qcloud.project.forest.model.query.GradeMerchandiseQuery;
import com.qcloud.project.forest.web.vo.admin.AdminGradeMerchandiseVO;
		
@Controller
@RequestMapping(value = "/" + AdminGradeMerchandiseController.DIR)
public class AdminGradeMerchandiseController {
	
	public static final String DIR = "admin/gradeMerchandise";
	
	@Autowired
	private GradeMerchandiseService gradeMerchandiseService;
	@Autowired
	private GradeMerchandiseHandler gradeMerchandiseHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, PPage pPage, GradeMerchandiseQuery query) {
	    
		Page<GradeMerchandise> page = gradeMerchandiseService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminGradeMerchandiseVO> list = gradeMerchandiseHandler.toVOList4Admin(page.getData());
		
	    String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   
		AcePagingView pagingView = new AcePagingView("/admin/forest-GradeMerchandise-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/forest-GradeMerchandise-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(GradeMerchandise gradeMerchandise) {
		gradeMerchandiseService.add(gradeMerchandise);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		GradeMerchandise gradeMerchandise=gradeMerchandiseService.get(id);
		AdminGradeMerchandiseVO adminGradeMerchandiseVO=gradeMerchandiseHandler.toVO4Admin(gradeMerchandise);
		ModelAndView model = new ModelAndView("/admin/forest-GradeMerchandise-edit");
		model.addObject("gradeMerchandise", adminGradeMerchandiseVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(GradeMerchandise gradeMerchandise, String queryStr) {
		gradeMerchandiseService.update(gradeMerchandise);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		gradeMerchandiseService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
