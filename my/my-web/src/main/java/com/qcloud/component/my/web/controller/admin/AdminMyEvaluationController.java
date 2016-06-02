package com.qcloud.component.my.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.my.model.MyEvaluation;
import com.qcloud.component.my.model.query.MyEvaluationQuery;
import com.qcloud.component.my.service.MyEvaluationService;
import com.qcloud.component.my.web.handler.MyEvaluationHandler;
import com.qcloud.component.my.web.vo.admin.AdminMyEvaluationVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
		
@Controller
@RequestMapping(value = "/" + AdminMyEvaluationController.DIR)
public class AdminMyEvaluationController {
	
	public static final String DIR = "admin/myEvaluation";
	
	@Autowired
	private MyEvaluationService myEvaluationService;
	@Autowired
	private MyEvaluationHandler myEvaluationHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, MyEvaluationQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<MyEvaluation> page = myEvaluationService.page(query, start, PAGE_SIZE);
		List<AdminMyEvaluationVO> list = myEvaluationHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MyEvaluation-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/personalcenter-MyEvaluation-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MyEvaluation myEvaluation) {
		myEvaluationService.add(myEvaluation);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MyEvaluation myEvaluation= myEvaluationService.get(id);
		AdminMyEvaluationVO adminUserEvaluationVO=myEvaluationHandler.toVO4Admin(myEvaluation);
		ModelAndView model = new ModelAndView("/admin/personalcenter-MyEvaluation-edit");
		model.addObject("myEvaluation", adminUserEvaluationVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MyEvaluation myEvaluation) {
		myEvaluationService.update(myEvaluation);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		myEvaluationService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
