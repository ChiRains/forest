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
import com.qcloud.component.goods.model.MerchandiseAttribute;
import com.qcloud.component.goods.service.MerchandiseAttributeService;
import com.qcloud.component.goods.web.handler.MerchandiseAttributeHandler;
import com.qcloud.component.goods.model.query.MerchandiseAttributeQuery;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseAttributeVO;
		
@Controller
@RequestMapping(value = "/" + AdminMerchandiseAttributeController.DIR)
public class AdminMerchandiseAttributeController {
	
	public static final String DIR = "admin/merchandiseAttribute";
	
	@Autowired
	private MerchandiseAttributeService merchandiseAttributeService;
	@Autowired
	private MerchandiseAttributeHandler merchandiseAttributeHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, MerchandiseAttributeQuery query) {
		
		Page<MerchandiseAttribute> page = merchandiseAttributeService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminMerchandiseAttributeVO> list = merchandiseAttributeHandler.toVOList4Admin(page.getData());
				
		String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
	    String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   	    
		AcePagingView pagingView = new AcePagingView("/admin/goods-MerchandiseAttribute-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/goods-MerchandiseAttribute-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MerchandiseAttribute merchandiseAttribute) {
		merchandiseAttributeService.add(merchandiseAttribute);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MerchandiseAttribute merchandiseAttribute=merchandiseAttributeService.get(id);
		AdminMerchandiseAttributeVO adminMerchandiseAttributeVO=merchandiseAttributeHandler.toVO4Admin(merchandiseAttribute);
		ModelAndView model = new ModelAndView("/admin/goods-MerchandiseAttribute-edit");
		model.addObject("merchandiseAttribute", adminMerchandiseAttributeVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MerchandiseAttribute merchandiseAttribute, String queryStr) {
		merchandiseAttributeService.update(merchandiseAttribute);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		merchandiseAttributeService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
