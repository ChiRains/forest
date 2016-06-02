package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.ArticleEvaluation;
import com.qcloud.project.forest.web.vo.ArticleEvaluationVO;
import com.qcloud.project.forest.web.vo.admin.AdminArticleEvaluationVO;

public interface ArticleEvaluationHandler {

	List<ArticleEvaluationVO> toVOList(List<ArticleEvaluation> list);

	ArticleEvaluationVO toVO(ArticleEvaluation articleEvaluation);

	List<AdminArticleEvaluationVO> toVOList4Admin(List<ArticleEvaluation> list);

	AdminArticleEvaluationVO toVO4Admin(ArticleEvaluation articleEvaluation);
}
