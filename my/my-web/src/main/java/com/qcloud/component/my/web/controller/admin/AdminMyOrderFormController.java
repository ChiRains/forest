package com.qcloud.component.my.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.my.model.MyOrderForm;
import com.qcloud.component.my.model.query.MyOrderFormQuery;
import com.qcloud.component.my.service.MyOrderFormService;
import com.qcloud.component.my.web.handler.MyOrderFormHandler;
import com.qcloud.component.my.web.vo.admin.AdminMyOrderFormVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
		
@Controller
@RequestMapping(value = "/" + AdminMyOrderFormController.DIR)
public class AdminMyOrderFormController {
	
	public static final String DIR = "admin/myOrderForm";
	
	@Autowired
	private MyOrderFormService myOrderFormService;
	@Autowired
	private MyOrderFormHandler myOrderFormHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, MyOrderFormQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<MyOrderForm> page = myOrderFormService.page(query, start, PAGE_SIZE);
		List<AdminMyOrderFormVO> list = myOrderFormHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MyOrderForm-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/personalcenter-MyOrderForm-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MyOrderForm myOrderForm) {
		myOrderFormService.add(myOrderForm);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MyOrderForm myOrderForm=myOrderFormService.get(id);
		AdminMyOrderFormVO adminMyOrderFormVO=myOrderFormHandler.toVO4Admin(myOrderForm);
		ModelAndView model = new ModelAndView("/admin/personalcenter-MyOrderForm-edit");
		model.addObject("myOrderForm", adminMyOrderFormVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MyOrderForm myOrderForm) {
		myOrderFormService.update(myOrderForm);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		myOrderFormService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
