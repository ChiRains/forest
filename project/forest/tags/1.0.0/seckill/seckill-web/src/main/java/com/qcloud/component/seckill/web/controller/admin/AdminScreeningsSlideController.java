package com.qcloud.component.seckill.web.controller.admin;

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
import com.qcloud.component.seckill.model.ScreeningsSlide;
import com.qcloud.component.seckill.service.ScreeningsSlideService;
import com.qcloud.component.seckill.web.handler.ScreeningsSlideHandler;
import com.qcloud.component.seckill.model.query.ScreeningsSlideQuery;
import com.qcloud.component.seckill.web.vo.admin.AdminScreeningsSlideVO;
		
@Controller
@RequestMapping(value = "/" + AdminScreeningsSlideController.DIR)
public class AdminScreeningsSlideController {
	
	public static final String DIR = "admin/screeningsSlide";
	
	@Autowired
	private ScreeningsSlideService screeningsSlideService;
	@Autowired
	private ScreeningsSlideHandler screeningsSlideHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, ScreeningsSlideQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<ScreeningsSlide> page = screeningsSlideService.page(query, start, PAGE_SIZE);
		List<AdminScreeningsSlideVO> list = screeningsSlideHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/seckill-ScreeningsSlide-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/seckill-ScreeningsSlide-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(ScreeningsSlide screeningsSlide) {
		screeningsSlideService.add(screeningsSlide);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		ScreeningsSlide screeningsSlide=screeningsSlideService.get(id);
		AdminScreeningsSlideVO adminScreeningsSlideVO=screeningsSlideHandler.toVO4Admin(screeningsSlide);
		ModelAndView model = new ModelAndView("/admin/seckill-ScreeningsSlide-edit");
		model.addObject("screeningsSlide", adminScreeningsSlideVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(ScreeningsSlide screeningsSlide) {
		screeningsSlideService.update(screeningsSlide);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		screeningsSlideService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
