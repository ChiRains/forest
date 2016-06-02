package com.qcloud.component.my.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.my.model.MyCoupon;
import com.qcloud.component.my.model.query.MyCouponQuery;
import com.qcloud.component.my.service.MyCouponService;
import com.qcloud.component.my.web.handler.MyCouponHandler;
import com.qcloud.component.my.web.vo.admin.AdminMyCouponVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
		
@Controller
@RequestMapping(value = "/" + AdminMyCouponController.DIR)
public class AdminMyCouponController {
	
	public static final String DIR = "admin/myCoupon";
	
	@Autowired
	private MyCouponService myCouponService;
	@Autowired
	private MyCouponHandler myCouponHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, MyCouponQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<MyCoupon> page = myCouponService.page(query, start, PAGE_SIZE);
		List<AdminMyCouponVO> list = myCouponHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MyCoupon-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/personalcenter-MyCoupon-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MyCoupon myCoupon) {
		myCouponService.add(myCoupon);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MyCoupon myCoupon=myCouponService.get(id);
		AdminMyCouponVO adminMyCouponVO=myCouponHandler.toVO4Admin(myCoupon);
		ModelAndView model = new ModelAndView("/admin/personalcenter-MyCoupon-edit");
		model.addObject("myCoupon", adminMyCouponVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MyCoupon myCoupon) {
		myCouponService.update(myCoupon);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		myCouponService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
