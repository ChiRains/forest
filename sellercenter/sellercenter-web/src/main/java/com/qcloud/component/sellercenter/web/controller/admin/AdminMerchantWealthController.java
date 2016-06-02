package com.qcloud.component.sellercenter.web.controller.admin;

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
import com.qcloud.component.sellercenter.model.MerchantWealth;
import com.qcloud.component.sellercenter.service.MerchantWealthService;
import com.qcloud.component.sellercenter.web.handler.MerchantWealthHandler;
import com.qcloud.component.sellercenter.model.query.MerchantWealthQuery;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantWealthVO;
		
@Controller
@RequestMapping(value = "/" + AdminMerchantWealthController.DIR)
public class AdminMerchantWealthController {
	
	public static final String DIR = "admin/merchantWealth";
	
	@Autowired
	private MerchantWealthService merchantWealthService;
	@Autowired
	private MerchantWealthHandler merchantWealthHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, MerchantWealthQuery query) {
		
		Page<MerchantWealth> page = merchantWealthService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminMerchantWealthVO> list = merchantWealthHandler.toVOList4Admin(page.getData());
				
		String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
	    String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   	    
		AcePagingView pagingView = new AcePagingView("/admin/sellercenter-MerchantWealth-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantWealth-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MerchantWealth merchantWealth) {
		merchantWealthService.add(merchantWealth);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MerchantWealth merchantWealth=merchantWealthService.get(id);
		AdminMerchantWealthVO adminMerchantWealthVO=merchantWealthHandler.toVO4Admin(merchantWealth);
		ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantWealth-edit");
		model.addObject("merchantWealth", adminMerchantWealthVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MerchantWealth merchantWealth, String queryStr) {
		merchantWealthService.update(merchantWealth);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		merchantWealthService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
