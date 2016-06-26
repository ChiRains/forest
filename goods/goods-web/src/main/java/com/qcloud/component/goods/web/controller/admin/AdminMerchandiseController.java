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
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.service.MerchandiseService;
import com.qcloud.component.goods.web.handler.MerchandiseHandler;
import com.qcloud.component.goods.model.query.MerchandiseQuery;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseVO;
		
@Controller
@RequestMapping(value = "/" + AdminMerchandiseController.DIR)
public class AdminMerchandiseController {
	
	public static final String DIR = "admin/merchandise";
	
	@Autowired
	private MerchandiseService merchandiseService;
	@Autowired
	private MerchandiseHandler merchandiseHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, MerchandiseQuery query) {
		
		Page<Merchandise> page = merchandiseService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminMerchandiseVO> list = merchandiseHandler.toVOList4Admin(page.getData());
				
		String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
	    String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   	    
		AcePagingView pagingView = new AcePagingView("/admin/goods-Merchandise-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/goods-Merchandise-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(Merchandise merchandise) {
		merchandiseService.add(merchandise);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		Merchandise merchandise=merchandiseService.get(id);
		AdminMerchandiseVO adminMerchandiseVO=merchandiseHandler.toVO4Admin(merchandise);
		ModelAndView model = new ModelAndView("/admin/goods-Merchandise-edit");
		model.addObject("merchandise", adminMerchandiseVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(Merchandise merchandise, String queryStr) {
		merchandiseService.update(merchandise);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		merchandiseService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
