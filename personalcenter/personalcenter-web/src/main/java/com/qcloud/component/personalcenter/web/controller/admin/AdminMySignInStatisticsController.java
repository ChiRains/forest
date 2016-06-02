package com.qcloud.component.personalcenter.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.personalcenter.model.MySignInStatistics;
import com.qcloud.component.personalcenter.model.query.MySignInStatisticsQuery;
import com.qcloud.component.personalcenter.service.MySignInStatisticsService;
import com.qcloud.component.personalcenter.web.handler.MySignInStatisticsHandler;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMySignInStatisticsVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.security.annotation.NoReferer;
		
@Controller
@RequestMapping(value = "/" + AdminMySignInStatisticsController.DIR)
public class AdminMySignInStatisticsController {
	
	public static final String DIR = "admin/mySignInStatistics";
	
	@Autowired
	private MySignInStatisticsService mySignInStatisticsService;
	@Autowired
	private MySignInStatisticsHandler mySignInStatisticsHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(PPage pPage, MySignInStatisticsQuery query) {
		
		Page<MySignInStatistics> page = mySignInStatisticsService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminMySignInStatisticsVO> list = mySignInStatisticsHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MySignInStatistics-list", DIR
				+ "/list", pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/personalcenter-MySignInStatistics-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MySignInStatistics mySignInStatistics) {
		mySignInStatisticsService.add(mySignInStatistics);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MySignInStatistics mySignInStatistics=mySignInStatisticsService.get(id);
		AdminMySignInStatisticsVO adminMySignInStatisticsVO=mySignInStatisticsHandler.toVO4Admin(mySignInStatistics);
		ModelAndView model = new ModelAndView("/admin/personalcenter-MySignInStatistics-edit");
		model.addObject("mySignInStatistics", adminMySignInStatisticsVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MySignInStatistics mySignInStatistics) {
		mySignInStatisticsService.update(mySignInStatistics);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		mySignInStatisticsService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
