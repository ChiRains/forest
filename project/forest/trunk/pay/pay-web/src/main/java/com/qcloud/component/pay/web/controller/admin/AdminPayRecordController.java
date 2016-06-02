package com.qcloud.component.pay.web.controller.admin;

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
import com.qcloud.component.pay.model.PayRecord;
import com.qcloud.component.pay.service.PayRecordService;
import com.qcloud.component.pay.web.handler.PayRecordHandler;
import com.qcloud.component.pay.model.query.PayRecordQuery;
import com.qcloud.component.pay.web.vo.admin.AdminPayRecordVO;
		
@Controller
@RequestMapping(value = "/" + AdminPayRecordController.DIR)
public class AdminPayRecordController {
	
	public static final String DIR = "admin/payRecord";
	
	@Autowired
	private PayRecordService payRecordService;
	@Autowired
	private PayRecordHandler payRecordHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, PayRecordQuery query) {
		
		Page<PayRecord> page = payRecordService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminPayRecordVO> list = payRecordHandler.toVOList4Admin(page.getData());
				
		String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
	    String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   	    
		AcePagingView pagingView = new AcePagingView("/admin/pay-PayRecord-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/pay-PayRecord-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(PayRecord payRecord) {
		payRecordService.add(payRecord);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		PayRecord payRecord=payRecordService.get(id);
		AdminPayRecordVO adminPayRecordVO=payRecordHandler.toVO4Admin(payRecord);
		ModelAndView model = new ModelAndView("/admin/pay-PayRecord-edit");
		model.addObject("payRecord", adminPayRecordVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(PayRecord payRecord, String queryStr) {
		payRecordService.update(payRecord);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		payRecordService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
