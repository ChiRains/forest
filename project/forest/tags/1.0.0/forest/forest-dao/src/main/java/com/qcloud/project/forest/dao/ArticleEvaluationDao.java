package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.ArticleEvaluation;
import com.qcloud.project.forest.model.query.ArticleEvaluationQuery;
		
public interface ArticleEvaluationDao extends ISimpleDao<ArticleEvaluation, Long> {

	public boolean add(ArticleEvaluation articleEvaluation);	
	
	public ArticleEvaluation get(Long id);

	public boolean delete(Long id);
	
	public boolean update(ArticleEvaluation articleEvaluation);
	
	public List<ArticleEvaluation> list(List<Long> idList);
	
	public Map<Long, ArticleEvaluation> map(Set<Long> idSet);
	
	public Page<ArticleEvaluation> page(ArticleEvaluationQuery query, int start, int size);

	public List<ArticleEvaluation> listAll();
	
	public List<ArticleEvaluation> listByArticleId(Long articleId);

	public List<ArticleEvaluation> listByUserId(Long userId);

}
