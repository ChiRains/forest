package com.qcloud.project.forest.web.controller.admin;

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
import com.qcloud.project.forest.model.Userdb;
import com.qcloud.project.forest.service.UserdbService;
import com.qcloud.project.forest.web.handler.UserdbHandler;
import com.qcloud.project.forest.model.query.UserdbQuery;
import com.qcloud.project.forest.web.vo.admin.AdminUserdbVO;
		
@Controller
@RequestMapping(value = "/" + AdminUserdbController.DIR)
public class AdminUserdbController {
	
	public static final String DIR = "admin/userdb";
	
	@Autowired
	private UserdbService userdbService;
	@Autowired
	private UserdbHandler userdbHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, UserdbQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<Userdb> page = userdbService.page(query, start, PAGE_SIZE);
		List<AdminUserdbVO> list = userdbHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/forest-Userdb-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/forest-Userdb-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(Userdb userdb) {
		userdbService.add(userdb);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		Userdb userdb=userdbService.get(id);
		AdminUserdbVO adminUserdbVO=userdbHandler.toVO4Admin(userdb);
		ModelAndView model = new ModelAndView("/admin/forest-Userdb-edit");
		model.addObject("userdb", adminUserdbVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(Userdb userdb) {
		userdbService.update(userdb);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		userdbService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
