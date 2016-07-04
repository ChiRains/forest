//package com.qcloud.component.sellercenter.web.controller.admin;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.mvc.AceAjaxView;
//import com.qcloud.pirates.mvc.AcePagingView;
//import com.qcloud.pirates.util.AssertUtil;
//import com.qcloud.pirates.util.NumberUtil;
//import com.qcloud.pirates.util.RequestUtil;
//import com.qcloud.pirates.web.security.annotation.NoReferer;
//import com.qcloud.component.sellercenter.model.MerchantConsumptionDetail;
//import com.qcloud.component.sellercenter.service.MerchantConsumptionDetailService;
//import com.qcloud.component.sellercenter.web.handler.MerchantConsumptionDetailHandler;
//import com.qcloud.component.sellercenter.model.query.MerchantConsumptionDetailQuery;
//import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantConsumptionDetailVO;
//		
//@Controller
//@RequestMapping(value = "/" + AdminMerchantConsumptionDetailController.DIR)
//public class AdminMerchantConsumptionDetailController {
//	
//	public static final String DIR = "admin/merchantConsumptionDetail";
//	
//	@Autowired
//	private MerchantConsumptionDetailService merchantConsumptionDetailService;
//	@Autowired
//	private MerchantConsumptionDetailHandler merchantConsumptionDetailHandler;
//	
//	@RequestMapping
//	@NoReferer
//	public ModelAndView list(Integer pageNum, MerchantConsumptionDetailQuery query) {
//		final int PAGE_SIZE = 10;
//		pageNum = RequestUtil.getPageid(pageNum);
//		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
//		
//		Page<MerchantConsumptionDetail> page = merchantConsumptionDetailService.page(query, start, PAGE_SIZE);
//		List<AdminMerchantConsumptionDetailVO> list = merchantConsumptionDetailHandler.toVOList4Admin(page.getData());
//				
//		AcePagingView pagingView = new AcePagingView("/admin/sellercenter-MerchantConsumptionDetail-list", DIR
//				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
//		pagingView.addObject("result", list);
//	
//		return pagingView;
//	}
//	
//	@RequestMapping
//	public ModelAndView toAdd() {
//		ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantConsumptionDetail-add");
//		return model;
//	}
//	
//	@RequestMapping
//	public AceAjaxView add(MerchantConsumptionDetail merchantConsumptionDetail) {
//		merchantConsumptionDetailService.add(merchantConsumptionDetail);
//		
//		AceAjaxView aceAjaxView = new AceAjaxView();
//		aceAjaxView.setMessage("添加成功");
//		aceAjaxView.setUrl(DIR + "/list");
//		return aceAjaxView;
//	}
//	
//	@RequestMapping
//	public ModelAndView toEdit(Long id) {
//		AssertUtil.assertNotNull(id, "ID不能为空");
//		MerchantConsumptionDetail merchantConsumptionDetail=merchantConsumptionDetailService.get(id);
//		AdminMerchantConsumptionDetailVO adminMerchantConsumptionDetailVO=merchantConsumptionDetailHandler.toVO4Admin(merchantConsumptionDetail);
//		ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantConsumptionDetail-edit");
//		model.addObject("merchantConsumptionDetail", adminMerchantConsumptionDetailVO);
//		return model;
//	}
//	
//	@RequestMapping
//	public AceAjaxView edit(MerchantConsumptionDetail merchantConsumptionDetail) {
//		merchantConsumptionDetailService.update(merchantConsumptionDetail);		
//		AceAjaxView aceAjaxView = new AceAjaxView();
//		aceAjaxView.setMessage("编辑成功");
//		aceAjaxView.setUrl(DIR + "/list");
//		return aceAjaxView;
//	}
//	
//	@RequestMapping
//	public AceAjaxView delete(Long id) {
//		AssertUtil.assertNotNull(id, "ID不能为空");
//		merchantConsumptionDetailService.delete(id);
//		AceAjaxView aceAjaxView = new AceAjaxView();
//		aceAjaxView.setMessage("删除成功");
//		aceAjaxView.setUrl(DIR + "/list");
//		return aceAjaxView;
//	}	
//}
