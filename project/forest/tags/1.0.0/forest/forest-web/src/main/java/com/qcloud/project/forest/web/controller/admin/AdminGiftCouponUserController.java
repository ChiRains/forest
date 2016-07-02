package com.qcloud.project.forest.web.controller.admin;

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
import com.qcloud.project.forest.model.GiftCouponUser;
import com.qcloud.project.forest.service.GiftCouponUserService;
import com.qcloud.project.forest.web.handler.GiftCouponUserHandler;
import com.qcloud.project.forest.model.query.GiftCouponUserQuery;
import com.qcloud.project.forest.web.vo.admin.AdminGiftCouponUserVO;
		
@Controller
@RequestMapping(value = "/" + AdminGiftCouponUserController.DIR)
public class AdminGiftCouponUserController {
	
	public static final String DIR = "admin/giftCouponUser";
	
	@Autowired
	private GiftCouponUserService giftCouponUserService;
	@Autowired
	private GiftCouponUserHandler giftCouponUserHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, PPage pPage, GiftCouponUserQuery query) {
	    
		Page<GiftCouponUser> page = giftCouponUserService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminGiftCouponUserVO> list = giftCouponUserHandler.toVOList4Admin(page.getData());
		
	    String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   
		AcePagingView pagingView = new AcePagingView("/admin/forest-GiftCouponUser-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/forest-GiftCouponUser-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(GiftCouponUser giftCouponUser) {
		giftCouponUserService.add(giftCouponUser);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		GiftCouponUser giftCouponUser=giftCouponUserService.get(id);
		AdminGiftCouponUserVO adminGiftCouponUserVO=giftCouponUserHandler.toVO4Admin(giftCouponUser);
		ModelAndView model = new ModelAndView("/admin/forest-GiftCouponUser-edit");
		model.addObject("giftCouponUser", adminGiftCouponUserVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(GiftCouponUser giftCouponUser, String queryStr) {
		giftCouponUserService.update(giftCouponUser);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		giftCouponUserService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
