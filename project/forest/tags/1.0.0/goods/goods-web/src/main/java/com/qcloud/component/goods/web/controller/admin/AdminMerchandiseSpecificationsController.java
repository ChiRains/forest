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
	public ModelAndView list(Integer pageNum, MerchandiseSpecificationsQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<MerchandiseSpecifications> page = merchandiseSpecificationsService.page(query, start, PAGE_SIZE);
		List<AdminMerchandiseSpecificationsVO> list = merchandiseSpecificationsHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/goods-MerchandiseSpecifications-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
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
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MerchandiseSpecifications merchandiseSpecifications=merchandiseSpecificationsService.get(id);
		AdminMerchandiseSpecificationsVO adminMerchandiseSpecificationsVO=merchandiseSpecificationsHandler.toVO4Admin(merchandiseSpecifications);
		ModelAndView model = new ModelAndView("/admin/goods-MerchandiseSpecifications-edit");
		model.addObject("merchandiseSpecifications", adminMerchandiseSpecificationsVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MerchandiseSpecifications merchandiseSpecifications) {
		merchandiseSpecificationsService.update(merchandiseSpecifications);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
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
