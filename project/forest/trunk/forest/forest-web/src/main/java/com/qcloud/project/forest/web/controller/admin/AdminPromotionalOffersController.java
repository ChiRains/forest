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
import com.qcloud.project.forest.model.PromotionalOffers;
import com.qcloud.project.forest.service.PromotionalOffersService;
import com.qcloud.project.forest.web.handler.PromotionalOffersHandler;
import com.qcloud.project.forest.model.query.PromotionalOffersQuery;
import com.qcloud.project.forest.web.vo.admin.AdminPromotionalOffersVO;
		
@Controller
@RequestMapping(value = "/" + AdminPromotionalOffersController.DIR)
public class AdminPromotionalOffersController {
	
	public static final String DIR = "admin/promotionalOffers";
	
	@Autowired
	private PromotionalOffersService promotionalOffersService;
	@Autowired
	private PromotionalOffersHandler promotionalOffersHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, PPage pPage, PromotionalOffersQuery query) {
	    
		Page<PromotionalOffers> page = promotionalOffersService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminPromotionalOffersVO> list = promotionalOffersHandler.toVOList4Admin(page.getData());
		
	    String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   
		AcePagingView pagingView = new AcePagingView("/admin/forest-PromotionalOffers-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/forest-PromotionalOffers-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(PromotionalOffers promotionalOffers) {
		promotionalOffersService.add(promotionalOffers);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		PromotionalOffers promotionalOffers=promotionalOffersService.get(id);
		AdminPromotionalOffersVO adminPromotionalOffersVO=promotionalOffersHandler.toVO4Admin(promotionalOffers);
		ModelAndView model = new ModelAndView("/admin/forest-PromotionalOffers-edit");
		model.addObject("promotionalOffers", adminPromotionalOffersVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(PromotionalOffers promotionalOffers, String queryStr) {
		promotionalOffersService.update(promotionalOffers);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		promotionalOffersService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
