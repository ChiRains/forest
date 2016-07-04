//package com.qcloud.component.sellercenter.web.controller.admin;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.net.URLEncoder;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.mvc.AceAjaxView;
//import com.qcloud.pirates.mvc.AcePagingView;
//import com.qcloud.pirates.util.AssertUtil;
//import com.qcloud.pirates.util.StringUtil;
//import com.qcloud.pirates.web.page.PPage;
//import com.qcloud.pirates.web.page.PageParameterUtil;
//import com.qcloud.pirates.web.page.PiratesParameterKey;
//import com.qcloud.pirates.web.security.annotation.NoReferer;
//import com.qcloud.component.sellercenter.model.MerchantWealthDetail;
//import com.qcloud.component.sellercenter.service.MerchantWealthDetailService;
//import com.qcloud.component.sellercenter.web.handler.MerchantWealthDetailHandler;
//import com.qcloud.component.sellercenter.model.query.MerchantWealthDetailQuery;
//import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantWealthDetailVO;
//		
//@Controller
//@RequestMapping(value = "/" + AdminMerchantWealthDetailController.DIR)
//public class AdminMerchantWealthDetailController {
//	
//	public static final String DIR = "admin/merchantWealthDetail";
//	
//	@Autowired
//	private MerchantWealthDetailService merchantWealthDetailService;
//	@Autowired
//	private MerchantWealthDetailHandler merchantWealthDetailHandler;
//	
//	@RequestMapping
//	@NoReferer
//	public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, MerchantWealthDetailQuery query) {
//		
//		Page<MerchantWealthDetail> page = merchantWealthDetailService.page(query, pPage.getPageStart(), pPage.getPageSize());
//		List<AdminMerchantWealthDetailVO> list = merchantWealthDetailHandler.toVOList4Admin(page.getData());
//				
//		String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
//	    String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   	    
//		AcePagingView pagingView = new AcePagingView("/admin/sellercenter-MerchantWealthDetail-list", DIR
//				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
//		pagingView.addObject("result", list);
//		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
//		
//		return pagingView;
//	}
//	
//	@RequestMapping
//	public ModelAndView toAdd() {
//		ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantWealthDetail-add");
//		return model;
//	}
//	
//	@RequestMapping
//	public AceAjaxView add(MerchantWealthDetail merchantWealthDetail) {
//		merchantWealthDetailService.add(merchantWealthDetail);
//		
//		AceAjaxView aceAjaxView = new AceAjaxView();
//		aceAjaxView.setMessage("添加成功");
//		aceAjaxView.setUrl(DIR + "/list");
//		return aceAjaxView;
//	}
//	
//	@RequestMapping
//	public ModelAndView toEdit(Long id, String queryStr) {
//		AssertUtil.assertNotNull(id, "ID不能为空");
//		MerchantWealthDetail merchantWealthDetail=merchantWealthDetailService.get(id);
//		AdminMerchantWealthDetailVO adminMerchantWealthDetailVO=merchantWealthDetailHandler.toVO4Admin(merchantWealthDetail);
//		ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantWealthDetail-edit");
//		model.addObject("merchantWealthDetail", adminMerchantWealthDetailVO);
//		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
//		return model;
//	}
//	
//	@RequestMapping
//	public AceAjaxView edit(MerchantWealthDetail merchantWealthDetail, String queryStr) {
//		merchantWealthDetailService.update(merchantWealthDetail);		
//		AceAjaxView aceAjaxView = new AceAjaxView();
//		aceAjaxView.setMessage("编辑成功");
//		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
//		return aceAjaxView;
//	}
//	
//	@RequestMapping
//	public AceAjaxView delete(Long id) {
//		AssertUtil.assertNotNull(id, "ID不能为空");
//		merchantWealthDetailService.delete(id);
//		AceAjaxView aceAjaxView = new AceAjaxView();
//		aceAjaxView.setMessage("删除成功");
//		aceAjaxView.setUrl(DIR + "/list");
//		return aceAjaxView;
//	}	
//}
