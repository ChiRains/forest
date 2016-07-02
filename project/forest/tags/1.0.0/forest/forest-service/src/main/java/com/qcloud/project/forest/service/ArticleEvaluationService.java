package com.qcloud.project.forest.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.ArticleEvaluation;
import com.qcloud.project.forest.model.query.ArticleEvaluationQuery;

public interface ArticleEvaluationService {
	
	public boolean add(ArticleEvaluation articleEvaluation);	
	
	public ArticleEvaluation get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(ArticleEvaluation articleEvaluation);

	public List<ArticleEvaluation> listByArticleId(Long articleId);

	public List<ArticleEvaluation> listByUserId(Long userId);

	public Page<ArticleEvaluation> page(ArticleEvaluationQuery query, int start, int count);
	
	public List<ArticleEvaluation> listAll();
}

