package com.qcloud.component.goods.web.controller.admin;

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
import com.qcloud.component.goods.model.MonthHotSale;
import com.qcloud.component.goods.service.MonthHotSaleService;
import com.qcloud.component.goods.web.handler.MonthHotSaleHandler;
import com.qcloud.component.goods.model.query.MonthHotSaleQuery;
import com.qcloud.component.goods.web.vo.admin.AdminMonthHotSaleVO;
		
@Controller
@RequestMapping(value = "/" + AdminMonthHotSaleController.DIR)
public class AdminMonthHotSaleController {
	
	public static final String DIR = "admin/monthHotSale";
	
	@Autowired
	private MonthHotSaleService monthHotSaleService;
	@Autowired
	private MonthHotSaleHandler monthHotSaleHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, MonthHotSaleQuery query) {
		
		Page<MonthHotSale> page = monthHotSaleService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminMonthHotSaleVO> list = monthHotSaleHandler.toVOList4Admin(page.getData());
				
		String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
	    String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   	    
		AcePagingView pagingView = new AcePagingView("/admin/goods-MonthHotSale-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/goods-MonthHotSale-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MonthHotSale monthHotSale) {
		monthHotSaleService.add(monthHotSale);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MonthHotSale monthHotSale=monthHotSaleService.get(id);
		AdminMonthHotSaleVO adminMonthHotSaleVO=monthHotSaleHandler.toVO4Admin(monthHotSale);
		ModelAndView model = new ModelAndView("/admin/goods-MonthHotSale-edit");
		model.addObject("monthHotSale", adminMonthHotSaleVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MonthHotSale monthHotSale, String queryStr) {
		monthHotSaleService.update(monthHotSale);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		monthHotSaleService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
