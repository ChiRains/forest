package com.qcloud.component.commoditycenter.web.controller.admin;

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
import com.qcloud.component.commoditycenter.model.MerchandiseItem;
import com.qcloud.component.commoditycenter.model.MerchandiseVipDiscountHistory;
import com.qcloud.component.commoditycenter.service.MerchandiseItemService;
import com.qcloud.component.commoditycenter.service.MerchandiseVipDiscountHistoryService;
import com.qcloud.component.commoditycenter.web.handler.MerchandiseVipDiscountHistoryHandler;
import com.qcloud.component.commoditycenter.model.query.MerchandiseVipDiscountHistoryQuery;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminMerchandiseVipDiscountHistoryVO;
		
@Controller
@RequestMapping(value = "/" + AdminMerchandiseVipDiscountHistoryController.DIR)
public class AdminMerchandiseVipDiscountHistoryController {
	
	public static final String DIR = "admin/merchandiseVipDiscountHistory";
	
	@Autowired
	private MerchandiseVipDiscountHistoryService merchandiseVipDiscountHistoryService;
	@Autowired
	private MerchandiseVipDiscountHistoryHandler merchandiseVipDiscountHistoryHandler;
	
	@Autowired
    private MerchandiseItemService     merchandiseItemService;
	
	/**
	 * 大客户商品历史价格
	 * @param request
	 * @param pageNum
	 * @param pPage
	 * @param query
	 * @return
	 */
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, MerchandiseVipDiscountHistoryQuery query) {
		
		Page<MerchandiseVipDiscountHistory> page = merchandiseVipDiscountHistoryService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminMerchandiseVipDiscountHistoryVO> list = merchandiseVipDiscountHistoryHandler.toVOList4Admin(page.getData());
		for (AdminMerchandiseVipDiscountHistoryVO vo : list) {
			vo.setCompanyName(query.getCompanyName());
			MerchandiseItem merchandiseItem = merchandiseItemService.get(vo.getMerchandiseItemId());
			vo.setMerchandiseItemName(merchandiseItem == null ? "" : merchandiseItem.getName());
		}
		
		String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
	    String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   	    
		AcePagingView pagingView = new AcePagingView("/admin/commoditycenter-MerchandiseVipDiscountHistory-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		pagingView.addObject("query", query);
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/commoditycenter-MerchandiseVipDiscountHistory-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory) {
		merchandiseVipDiscountHistoryService.add(merchandiseVipDiscountHistory);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MerchandiseVipDiscountHistory merchandiseVipDiscountHistory=merchandiseVipDiscountHistoryService.get(id);
		AdminMerchandiseVipDiscountHistoryVO adminMerchandiseVipDiscountHistoryVO=merchandiseVipDiscountHistoryHandler.toVO4Admin(merchandiseVipDiscountHistory);
		ModelAndView model = new ModelAndView("/admin/commoditycenter-MerchandiseVipDiscountHistory-edit");
		model.addObject("merchandiseVipDiscountHistory", adminMerchandiseVipDiscountHistoryVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory, String queryStr) {
		merchandiseVipDiscountHistoryService.update(merchandiseVipDiscountHistory);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		merchandiseVipDiscountHistoryService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
