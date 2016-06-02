package com.qcloud.project.forest.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.ArticleEvaluation;
import com.qcloud.project.forest.service.ArticleEvaluationService;
import com.qcloud.project.forest.web.handler.ArticleEvaluationHandler;
import com.qcloud.project.forest.model.key.TypeEnum.StateType;
import com.qcloud.project.forest.model.query.ArticleEvaluationQuery;
import com.qcloud.project.forest.web.vo.admin.AdminArticleEvaluationVO;
		
@Controller
@RequestMapping(value = "/" + AdminArticleEvaluationController.DIR)
public class AdminArticleEvaluationController {
	
	public static final String DIR = "admin/articleEvaluation";
	
	@Autowired
	private ArticleEvaluationService articleEvaluationService;
	@Autowired
	private ArticleEvaluationHandler articleEvaluationHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, ArticleEvaluationQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<ArticleEvaluation> page = articleEvaluationService.page(query, start, PAGE_SIZE);
		List<AdminArticleEvaluationVO> list = articleEvaluationHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/forest-ArticleEvaluation-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("stateType",StateType.values());
	
		return pagingView;
	}

	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/forest-ArticleEvaluation-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(ArticleEvaluation articleEvaluation) {
		articleEvaluationService.add(articleEvaluation);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		ArticleEvaluation articleEvaluation=articleEvaluationService.get(id);
		AdminArticleEvaluationVO adminArticleEvaluationVO=articleEvaluationHandler.toVO4Admin(articleEvaluation);
		ModelAndView model = new ModelAndView("/admin/forest-ArticleEvaluation-edit");
		model.addObject("articleEvaluation", adminArticleEvaluationVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(ArticleEvaluation articleEvaluation) {
		ArticleEvaluation evaluation = articleEvaluationService.get(articleEvaluation.getId());
		evaluation.setState(articleEvaluation.getState());
		articleEvaluationService.update(evaluation);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		articleEvaluationService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
