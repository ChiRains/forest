package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.Article;
import com.qcloud.project.forest.model.query.ArticleQuery;
		
public interface ArticleDao extends ISimpleDao<Article, Long> {

	public boolean add(Article article);	
	
	public Article get(Long id);

	public boolean delete(Long id);
	
	public boolean update(Article article);
	
	public List<Article> list(List<Long> idList);
	
	public Map<Long, Article> map(Set<Long> idSet);
	
	public Page<Article> page(ArticleQuery query, int start, int size);

	public List<Article> listAll();
	
	public List<Article> listByClassifyId(Long classifyId);

}
