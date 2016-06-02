package com.qcloud.component.orderform.web.controller.admin;

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
import com.qcloud.component.orderform.model.ExchangeOrderItemDetail;
import com.qcloud.component.orderform.service.ExchangeOrderItemDetailService;
import com.qcloud.component.orderform.web.handler.ExchangeOrderItemDetailHandler;
import com.qcloud.component.orderform.model.query.ExchangeOrderItemDetailQuery;
import com.qcloud.component.orderform.web.vo.admin.AdminExchangeOrderItemDetailVO;
		
@Controller
@RequestMapping(value = "/" + AdminExchangeOrderItemDetailController.DIR)
public class AdminExchangeOrderItemDetailController {
	
	public static final String DIR = "admin/exchangeOrderItemDetail";
	
	@Autowired
	private ExchangeOrderItemDetailService exchangeOrderItemDetailService;
	@Autowired
	private ExchangeOrderItemDetailHandler exchangeOrderItemDetailHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, ExchangeOrderItemDetailQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<ExchangeOrderItemDetail> page = exchangeOrderItemDetailService.page(query, start, PAGE_SIZE);
		List<AdminExchangeOrderItemDetailVO> list = exchangeOrderItemDetailHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/orderform-ExchangeOrderItemDetail-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/orderform-ExchangeOrderItemDetail-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(ExchangeOrderItemDetail exchangeOrderItemDetail) {
		exchangeOrderItemDetailService.add(exchangeOrderItemDetail);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		ExchangeOrderItemDetail exchangeOrderItemDetail=exchangeOrderItemDetailService.get(id);
		AdminExchangeOrderItemDetailVO adminExchangeOrderItemDetailVO=exchangeOrderItemDetailHandler.toVO4Admin(exchangeOrderItemDetail);
		ModelAndView model = new ModelAndView("/admin/orderform-ExchangeOrderItemDetail-edit");
		model.addObject("exchangeOrderItemDetail", adminExchangeOrderItemDetailVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(ExchangeOrderItemDetail exchangeOrderItemDetail) {
		exchangeOrderItemDetailService.update(exchangeOrderItemDetail);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		exchangeOrderItemDetailService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
