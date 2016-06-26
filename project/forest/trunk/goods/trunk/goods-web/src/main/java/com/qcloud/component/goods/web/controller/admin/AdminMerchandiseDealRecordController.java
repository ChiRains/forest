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
import com.qcloud.component.goods.model.MerchandiseDealRecord;
import com.qcloud.component.goods.service.MerchandiseDealRecordService;
import com.qcloud.component.goods.web.handler.MerchandiseDealRecordHandler;
import com.qcloud.component.goods.model.query.MerchandiseDealRecordQuery;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseDealRecordVO;
		
@Controller
@RequestMapping(value = "/" + AdminMerchandiseDealRecordController.DIR)
public class AdminMerchandiseDealRecordController {
	
	public static final String DIR = "admin/merchandiseDealRecord";
	
	@Autowired
	private MerchandiseDealRecordService merchandiseDealRecordService;
	@Autowired
	private MerchandiseDealRecordHandler merchandiseDealRecordHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, MerchandiseDealRecordQuery query) {
		
		Page<MerchandiseDealRecord> page = merchandiseDealRecordService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminMerchandiseDealRecordVO> list = merchandiseDealRecordHandler.toVOList4Admin(page.getData());
				
		String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
	    String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   	    
		AcePagingView pagingView = new AcePagingView("/admin/goods-MerchandiseDealRecord-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/goods-MerchandiseDealRecord-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MerchandiseDealRecord merchandiseDealRecord) {
		merchandiseDealRecordService.add(merchandiseDealRecord);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MerchandiseDealRecord merchandiseDealRecord=merchandiseDealRecordService.get(id);
		AdminMerchandiseDealRecordVO adminMerchandiseDealRecordVO=merchandiseDealRecordHandler.toVO4Admin(merchandiseDealRecord);
		ModelAndView model = new ModelAndView("/admin/goods-MerchandiseDealRecord-edit");
		model.addObject("merchandiseDealRecord", adminMerchandiseDealRecordVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MerchandiseDealRecord merchandiseDealRecord, String queryStr) {
		merchandiseDealRecordService.update(merchandiseDealRecord);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		merchandiseDealRecordService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
