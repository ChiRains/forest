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
import com.qcloud.project.forest.model.IntegralOrder;
import com.qcloud.project.forest.service.IntegralOrderService;
import com.qcloud.project.forest.web.handler.IntegralOrderHandler;
import com.qcloud.project.forest.model.query.IntegralOrderQuery;
import com.qcloud.project.forest.web.vo.admin.AdminIntegralOrderVO;
		
@Controller
@RequestMapping(value = "/" + AdminIntegralOrderController.DIR)
public class AdminIntegralOrderController {
	
	public static final String DIR = "admin/integralOrder";
	
	@Autowired
	private IntegralOrderService integralOrderService;
	@Autowired
	private IntegralOrderHandler integralOrderHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, PPage pPage, IntegralOrderQuery query) {
	    
		Page<IntegralOrder> page = integralOrderService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminIntegralOrderVO> list = integralOrderHandler.toVOList4Admin(page.getData());
		
	    String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   
		AcePagingView pagingView = new AcePagingView("/admin/forest-IntegralOrder-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/forest-IntegralOrder-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(IntegralOrder integralOrder) {
		integralOrderService.add(integralOrder);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		IntegralOrder integralOrder=integralOrderService.get(id);
		AdminIntegralOrderVO adminIntegralOrderVO=integralOrderHandler.toVO4Admin(integralOrder);
		ModelAndView model = new ModelAndView("/admin/forest-IntegralOrder-edit");
		model.addObject("integralOrder", adminIntegralOrderVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(IntegralOrder integralOrder, String queryStr) {
		integralOrderService.update(integralOrder);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		integralOrderService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
