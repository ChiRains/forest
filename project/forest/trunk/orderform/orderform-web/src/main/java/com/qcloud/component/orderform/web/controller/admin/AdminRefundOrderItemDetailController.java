package com.qcloud.component.orderform.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.orderform.model.RefundOrderItem;
import com.qcloud.component.orderform.model.query.RefundOrderItemQuery;
import com.qcloud.component.orderform.service.RefundOrderItemService;
import com.qcloud.component.orderform.web.handler.RefundOrderItemHandler;
import com.qcloud.component.orderform.web.vo.admin.AdminRefundOrderItemVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
		
@Controller
@RequestMapping(value = "/" + AdminRefundOrderItemDetailController.DIR)
public class AdminRefundOrderItemDetailController {
	
	public static final String DIR = "admin/refundOrderItem";
	
	@Autowired
	private RefundOrderItemService refundOrderItemDetailService;
	@Autowired
	private RefundOrderItemHandler refundOrderItemDetailHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, RefundOrderItemQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<RefundOrderItem> page = refundOrderItemDetailService.page(query, start, PAGE_SIZE);
		List<AdminRefundOrderItemVO> list = refundOrderItemDetailHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/orderform-RefundOrderItem-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/orderform-RefundOrderItem-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(RefundOrderItem refundOrderItem) {
		refundOrderItemDetailService.add(refundOrderItem);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		RefundOrderItem refundOrderItemDetail=refundOrderItemDetailService.get(id);
		AdminRefundOrderItemVO adminRefundOrderItemVO=refundOrderItemDetailHandler.toVO4Admin(refundOrderItemDetail);
		ModelAndView model = new ModelAndView("/admin/orderform-RefundOrderItem-edit");
		model.addObject("refundOrderItemDetail", adminRefundOrderItemVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(RefundOrderItem refundOrderItem) {
		refundOrderItemDetailService.update(refundOrderItem);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		refundOrderItemDetailService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
