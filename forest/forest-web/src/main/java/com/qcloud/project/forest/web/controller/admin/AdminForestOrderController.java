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
import com.qcloud.project.forest.model.ForestOrder;
import com.qcloud.project.forest.service.ForestOrderService;
import com.qcloud.project.forest.web.handler.ForestOrderHandler;
import com.qcloud.project.forest.model.query.ForestOrderQuery;
import com.qcloud.project.forest.web.vo.admin.AdminForestOrderVO;
		
@Controller
@RequestMapping(value = "/" + AdminForestOrderController.DIR)
public class AdminForestOrderController {
	
	public static final String DIR = "admin/forestOrder";
	
	@Autowired
	private ForestOrderService forestOrderService;
	@Autowired
	private ForestOrderHandler forestOrderHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, PPage pPage, ForestOrderQuery query) {
	    
		Page<ForestOrder> page = forestOrderService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminForestOrderVO> list = forestOrderHandler.toVOList4Admin(page.getData());
		
	    String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   
		AcePagingView pagingView = new AcePagingView("/admin/forest-ForestOrder-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/forest-ForestOrder-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(ForestOrder forestOrder) {
		forestOrderService.add(forestOrder);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		ForestOrder forestOrder=forestOrderService.get(id);
		AdminForestOrderVO adminForestOrderVO=forestOrderHandler.toVO4Admin(forestOrder);
		ModelAndView model = new ModelAndView("/admin/forest-ForestOrder-edit");
		model.addObject("forestOrder", adminForestOrderVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(ForestOrder forestOrder, String queryStr) {
		forestOrderService.update(forestOrder);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		forestOrderService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
