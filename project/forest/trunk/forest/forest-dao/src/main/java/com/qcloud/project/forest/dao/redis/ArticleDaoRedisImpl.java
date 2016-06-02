package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.project.forest.dao.ArticleDao;
import com.qcloud.project.forest.model.Article;
import com.qcloud.project.forest.model.query.ArticleQuery;

@Repository
public class ArticleDaoRedisImpl implements ArticleDao {

	//@Resource(name = "redis-forest")
	//private Redis redis;

	@Override
	public boolean add(Article article) {			
		throw new NotImplementedException();
	}

	@Override
	public Article get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(Article article){
		throw new NotImplementedException();
	}
	
	@Override
	public List<Article> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, Article> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<Article> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<Article> page(ArticleQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<Article> listAll(){	
		throw new NotImplementedException();
	}

	@Override
	public Page<Article> page(Long classifyId) {
		throw new NotImplementedException();
	}

	@Override
	public Long getBySort(long classifyId, int sort, int i) {
		throw new NotImplementedException();
	}
}

