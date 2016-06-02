package com.qcloud.project.forest.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.Article;
import com.qcloud.project.forest.model.query.ArticleQuery;

public interface ArticleService {
	
	public boolean add(Article article);	
	
	public Article get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(Article article);

	public Page<Article> page(ArticleQuery query, int start, int count);
	
	public List<Article> listAll();

	public Page<Article> page(Long classifyId);

	public Long getBySort(long classifyId, int sort, int i);
}

