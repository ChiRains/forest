package com.qcloud.component.goods.web.controller.admin;

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
import com.qcloud.component.goods.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.goods.service.MerchandiseSpecificationsRelationService;
import com.qcloud.component.goods.web.handler.MerchandiseSpecificationsRelationHandler;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsRelationQuery;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseSpecificationsRelationVO;
		
@Controller
@RequestMapping(value = "/" + AdminMerchandiseSpecificationsRelationController.DIR)
public class AdminMerchandiseSpecificationsRelationController {
	
	public static final String DIR = "admin/merchandiseSpecificationsRelation";
	
	@Autowired
	private MerchandiseSpecificationsRelationService merchandiseSpecificationsRelationService;
	@Autowired
	private MerchandiseSpecificationsRelationHandler merchandiseSpecificationsRelationHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, MerchandiseSpecificationsRelationQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<MerchandiseSpecificationsRelation> page = merchandiseSpecificationsRelationService.page(query, start, PAGE_SIZE);
		List<AdminMerchandiseSpecificationsRelationVO> list = merchandiseSpecificationsRelationHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/goods-MerchandiseSpecificationsRelation-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/goods-MerchandiseSpecificationsRelation-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation) {
		merchandiseSpecificationsRelationService.add(merchandiseSpecificationsRelation);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MerchandiseSpecificationsRelation merchandiseSpecificationsRelation=merchandiseSpecificationsRelationService.get(id);
		AdminMerchandiseSpecificationsRelationVO adminMerchandiseSpecificationsRelationVO=merchandiseSpecificationsRelationHandler.toVO4Admin(merchandiseSpecificationsRelation);
		ModelAndView model = new ModelAndView("/admin/goods-MerchandiseSpecificationsRelation-edit");
		model.addObject("merchandiseSpecificationsRelation", adminMerchandiseSpecificationsRelationVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation) {
		merchandiseSpecificationsRelationService.update(merchandiseSpecificationsRelation);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		merchandiseSpecificationsRelationService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
