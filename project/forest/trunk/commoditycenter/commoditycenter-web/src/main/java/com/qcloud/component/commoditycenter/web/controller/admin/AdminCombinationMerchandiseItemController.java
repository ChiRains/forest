package com.qcloud.component.commoditycenter.web.controller.admin;

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
import com.qcloud.component.commoditycenter.model.CombinationMerchandiseItem;
import com.qcloud.component.commoditycenter.service.CombinationMerchandiseItemService;
import com.qcloud.component.commoditycenter.web.handler.CombinationMerchandiseItemHandler;
import com.qcloud.component.commoditycenter.model.query.CombinationMerchandiseItemQuery;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminCombinationMerchandiseItemVO;
		
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
	public ModelAndView list(Integer pageNum, CombinationMerchandiseItemQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<CombinationMerchandiseItem> page = combinationMerchandiseItemService.page(query, start, PAGE_SIZE);
		List<AdminCombinationMerchandiseItemVO> list = combinationMerchandiseItemHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/commoditycenter-CombinationMerchandiseItem-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/commoditycenter-CombinationMerchandiseItem-add");
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
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		CombinationMerchandiseItem combinationMerchandiseItem=combinationMerchandiseItemService.get(id);
		AdminCombinationMerchandiseItemVO adminCombinationMerchandiseItemVO=combinationMerchandiseItemHandler.toVO4Admin(combinationMerchandiseItem);
		ModelAndView model = new ModelAndView("/admin/commoditycenter-CombinationMerchandiseItem-edit");
		model.addObject("combinationMerchandiseItem", adminCombinationMerchandiseItemVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(CombinationMerchandiseItem combinationMerchandiseItem) {
		combinationMerchandiseItemService.update(combinationMerchandiseItem);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
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
