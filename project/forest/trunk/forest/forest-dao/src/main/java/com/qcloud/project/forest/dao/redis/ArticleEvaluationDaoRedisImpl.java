package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;


import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ArticleEvaluationDao;
import com.qcloud.project.forest.model.ArticleEvaluation;
import com.qcloud.project.forest.model.query.ArticleEvaluationQuery;

@Repository
public class ArticleEvaluationDaoRedisImpl implements ArticleEvaluationDao {

	//@Resource(name = "redis-forest")
	//private Redis redis;

	@Override
	public boolean add(ArticleEvaluation articleEvaluation) {			
		throw new NotImplementedException();
	}

	@Override
	public ArticleEvaluation get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(ArticleEvaluation articleEvaluation){
		throw new NotImplementedException();
	}
	
	@Override
	public List<ArticleEvaluation> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, ArticleEvaluation> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<ArticleEvaluation> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<ArticleEvaluation> page(ArticleEvaluationQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<ArticleEvaluation> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public int getCommentCount(long id) {
        throw new NotImplementedException();
    }
}

