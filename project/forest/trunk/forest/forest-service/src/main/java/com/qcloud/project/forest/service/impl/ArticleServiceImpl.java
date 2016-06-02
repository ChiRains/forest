package com.qcloud.project.forest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ArticleDao;
import com.qcloud.project.forest.model.Article;
import com.qcloud.project.forest.service.ArticleService;
import com.qcloud.project.forest.model.query.ArticleQuery;
		
@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "forest_article";

	@Override
	public boolean add(Article article) {
		long id = autoIdGenerator.get(ID_KEY);
		article.setId(id);
		
		return articleDao.add(article);
	}

	@Override
	public Article get(Long id) {	
		return articleDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return articleDao.delete(id);
	}
	
	@Override
	public boolean update(Article article) {
		return articleDao.update(article);
	}
	
	@Override
	public Page<Article> page(ArticleQuery query, int start, int count) {
		return articleDao.page(query, start, count);
	}
	
	public List<Article> listAll(){
		return articleDao.listAll();
	}

	@Override
	public Page<Article> page(Long classifyId) {
		
		return articleDao.page(classifyId);
	}

	@Override
	public Long getBySort(long classifyId, int sort, int i) {
		return articleDao.getBySort(classifyId,sort,i);
	}
}

