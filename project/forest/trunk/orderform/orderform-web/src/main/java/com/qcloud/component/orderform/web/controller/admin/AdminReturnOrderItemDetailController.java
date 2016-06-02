package com.qcloud.component.orderform.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.orderform.model.ReturnOrderItem;
import com.qcloud.component.orderform.model.query.ReturnOrderItemQuery;
import com.qcloud.component.orderform.service.ReturnOrderItemService;
import com.qcloud.component.orderform.web.handler.ReturnOrderItemHandler;
import com.qcloud.component.orderform.web.vo.admin.AdminReturnOrderItemVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
		
@Controller
@RequestMapping(value = "/" + AdminReturnOrderItemDetailController.DIR)
public class AdminReturnOrderItemDetailController {
	
	public static final String DIR = "admin/returnOrderItem";
	
	@Autowired
	private ReturnOrderItemService returnOrderItemService;
	@Autowired
	private ReturnOrderItemHandler returnOrderItemHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, ReturnOrderItemQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<ReturnOrderItem> page = returnOrderItemService.page(query, start, PAGE_SIZE);
		List<AdminReturnOrderItemVO> list = returnOrderItemHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/orderform-ReturnOrderItem-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/orderform-ReturnOrderItem-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(ReturnOrderItem returnOrderItem) {
		returnOrderItemService.add(returnOrderItem);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		ReturnOrderItem returnOrderItem=returnOrderItemService.get(id);
		AdminReturnOrderItemVO adminReturnOrderItemDetailVO=returnOrderItemHandler.toVO4Admin(returnOrderItem);
		ModelAndView model = new ModelAndView("/admin/orderform-ReturnOrderItem-edit");
		model.addObject("returnOrderItemDetail", adminReturnOrderItemDetailVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(ReturnOrderItem returnOrderItem) {
		returnOrderItemService.update(returnOrderItem);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		returnOrderItemService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
