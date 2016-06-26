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
import com.qcloud.component.goods.model.CombinationMerchandiseItem;
import com.qcloud.component.goods.service.CombinationMerchandiseItemService;
import com.qcloud.component.goods.web.handler.CombinationMerchandiseItemHandler;
import com.qcloud.component.goods.model.query.CombinationMerchandiseItemQuery;
import com.qcloud.component.goods.web.vo.admin.AdminCombinationMerchandiseItemVO;
		
@Controller
@RequestMapping(value = "/" + AdminCombinationMerchandiseItemController.DIR)
public class AdminCombinationMerchandiseItemController {
	
	public static final String DIR = "admin/combinationMerchandiseItem";
	
	@Autowired
	private CombinationMerchandiseItemService combinationMerchandiseItemService;
	@Autowired
	private CombinationMerchandiseItemHandler combinationMerchandiseItemHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, CombinationMerchandiseItemQuery query) {
		
		Page<CombinationMerchandiseItem> page = combinationMerchandiseItemService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminCombinationMerchandiseItemVO> list = combinationMerchandiseItemHandler.toVOList4Admin(page.getData());
				
		String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
	    String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   	    
		AcePagingView pagingView = new AcePagingView("/admin/goods-CombinationMerchandiseItem-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/goods-CombinationMerchandiseItem-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(CombinationMerchandiseItem combinationMerchandiseItem) {
		combinationMerchandiseItemService.add(combinationMerchandiseItem);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		CombinationMerchandiseItem combinationMerchandiseItem=combinationMerchandiseItemService.get(id);
		AdminCombinationMerchandiseItemVO adminCombinationMerchandiseItemVO=combinationMerchandiseItemHandler.toVO4Admin(combinationMerchandiseItem);
		ModelAndView model = new ModelAndView("/admin/goods-CombinationMerchandiseItem-edit");
		model.addObject("combinationMerchandiseItem", adminCombinationMerchandiseItemVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(CombinationMerchandiseItem combinationMerchandiseItem, String queryStr) {
		combinationMerchandiseItemService.update(combinationMerchandiseItem);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		combinationMerchandiseItemService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
