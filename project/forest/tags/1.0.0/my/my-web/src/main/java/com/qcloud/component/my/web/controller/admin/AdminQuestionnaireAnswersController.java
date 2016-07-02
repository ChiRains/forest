package com.qcloud.component.my.web.controller.admin;

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
import com.qcloud.component.my.model.QuestionnaireAnswers;
import com.qcloud.component.my.service.QuestionnaireAnswersService;
import com.qcloud.component.my.web.handler.QuestionnaireAnswersHandler;
import com.qcloud.component.my.model.query.QuestionnaireAnswersQuery;
import com.qcloud.component.my.web.vo.admin.AdminQuestionnaireAnswersVO;
		
@Controller
@RequestMapping(value = "/" + AdminQuestionnaireAnswersController.DIR)
public class AdminQuestionnaireAnswersController {
	
	public static final String DIR = "admin/questionnaireAnswers";
	
	@Autowired
	private QuestionnaireAnswersService questionnaireAnswersService;
	@Autowired
	private QuestionnaireAnswersHandler questionnaireAnswersHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, QuestionnaireAnswersQuery query) {
		
		Page<QuestionnaireAnswers> page = questionnaireAnswersService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminQuestionnaireAnswersVO> list = questionnaireAnswersHandler.toVOList4Admin(page.getData());
				
		String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
	    String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   	    
		AcePagingView pagingView = new AcePagingView("/admin/my-QuestionnaireAnswers-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		pagingView.addObject("questionId",query.getQuestionId());
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/my-QuestionnaireAnswers-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(QuestionnaireAnswers questionnaireAnswers) {
		questionnaireAnswersService.add(questionnaireAnswers);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		QuestionnaireAnswers questionnaireAnswers=questionnaireAnswersService.get(id);
		AdminQuestionnaireAnswersVO adminQuestionnaireAnswersVO=questionnaireAnswersHandler.toVO4Admin(questionnaireAnswers);
		ModelAndView model = new ModelAndView("/admin/my-QuestionnaireAnswers-edit");
		model.addObject("questionnaireAnswers", adminQuestionnaireAnswersVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(QuestionnaireAnswers questionnaireAnswers, String queryStr) {
		questionnaireAnswersService.update(questionnaireAnswers);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		questionnaireAnswersService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
