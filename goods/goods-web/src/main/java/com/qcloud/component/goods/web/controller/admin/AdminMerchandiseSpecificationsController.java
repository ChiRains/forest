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
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.service.MerchandiseSpecificationsService;
import com.qcloud.component.goods.web.handler.MerchandiseSpecificationsHandler;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsQuery;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseSpecificationsVO;
		
@Controller
@RequestMapping(value = "/" + AdminMerchandiseSpecificationsController.DIR)
public class AdminMerchandiseSpecificationsController {
	
	public static final String DIR = "admin/merchandiseSpecifications";
	
	@Autowired
	private MerchandiseSpecificationsService merchandiseSpecificationsService;
	@Autowired
	private MerchandiseSpecificationsHandler merchandiseSpecificationsHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, MerchandiseSpecificationsQuery query) {
		
		Page<MerchandiseSpecifications> page = merchandiseSpecificationsService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminMerchandiseSpecificationsVO> list = merchandiseSpecificationsHandler.toVOList4Admin(page.getData());
				
		String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
	    String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   	    
		AcePagingView pagingView = new AcePagingView("/admin/goods-MerchandiseSpecifications-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/goods-MerchandiseSpecifications-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MerchandiseSpecifications merchandiseSpecifications) {
		merchandiseSpecificationsService.add(merchandiseSpecifications);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MerchandiseSpecifications merchandiseSpecifications=merchandiseSpecificationsService.get(id);
		AdminMerchandiseSpecificationsVO adminMerchandiseSpecificationsVO=merchandiseSpecificationsHandler.toVO4Admin(merchandiseSpecifications);
		ModelAndView model = new ModelAndView("/admin/goods-MerchandiseSpecifications-edit");
		model.addObject("merchandiseSpecifications", adminMerchandiseSpecificationsVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MerchandiseSpecifications merchandiseSpecifications, String queryStr) {
		merchandiseSpecificationsService.update(merchandiseSpecifications);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		merchandiseSpecificationsService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
