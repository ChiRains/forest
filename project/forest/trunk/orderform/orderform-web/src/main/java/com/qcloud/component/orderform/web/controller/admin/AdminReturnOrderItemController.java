package com.qcloud.component.orderform.web.controller.admin;

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
import com.qcloud.component.orderform.model.ReturnOrderItem;
import com.qcloud.component.orderform.service.ReturnOrderItemService;
import com.qcloud.component.orderform.web.handler.ReturnOrderItemHandler;
import com.qcloud.component.orderform.model.query.ReturnOrderItemQuery;
import com.qcloud.component.orderform.web.vo.admin.AdminReturnOrderItemVO;
		
@Controller
@RequestMapping(value = "/" + AdminReturnOrderItemController.DIR)
public class AdminReturnOrderItemController {
	
	public static final String DIR = "admin/returnOrderItem";
	
	@Autowired
	private ReturnOrderItemService returnOrderItemService;
	@Autowired
	private ReturnOrderItemHandler returnOrderItemHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, ReturnOrderItemQuery query) {
		
		Page<ReturnOrderItem> page = returnOrderItemService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminReturnOrderItemVO> list = returnOrderItemHandler.toVOList4Admin(page.getData());
				
		String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
	    String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   	    
		AcePagingView pagingView = new AcePagingView("/admin/orderform-ReturnOrderItem-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/orderform-ReturnOrderItem-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(ReturnOrderItem returnOrderItem) {
		returnOrderItemService.add(returnOrderItem);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		ReturnOrderItem returnOrderItem=returnOrderItemService.get(id);
		AdminReturnOrderItemVO adminReturnOrderItemVO=returnOrderItemHandler.toVO4Admin(returnOrderItem);
		ModelAndView model = new ModelAndView("/admin/orderform-ReturnOrderItem-edit");
		model.addObject("returnOrderItem", adminReturnOrderItemVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(ReturnOrderItem returnOrderItem, String queryStr) {
		returnOrderItemService.update(returnOrderItem);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		returnOrderItemService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
