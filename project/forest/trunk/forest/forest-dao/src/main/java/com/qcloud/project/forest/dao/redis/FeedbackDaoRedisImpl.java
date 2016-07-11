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
import com.qcloud.project.forest.dao.FeedbackDao;
import com.qcloud.project.forest.model.Feedback;
import com.qcloud.project.forest.model.query.FeedbackQuery;

@Repository
public class FeedbackDaoRedisImpl implements FeedbackDao {

	//@Resource(name = "redis-forest")
	//private Redis redis;

	@Override
	public boolean add(Feedback feedback) {			
		throw new NotImplementedException();
	}

	@Override
	public Feedback get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(Feedback feedback){
		throw new NotImplementedException();
	}
	
	@Override
	public List<Feedback> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, Feedback> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<Feedback> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<Feedback> page(FeedbackQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<Feedback> listAll(){	
		throw new NotImplementedException();
	}
}

