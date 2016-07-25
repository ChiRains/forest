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
import com.qcloud.project.forest.dao.GradeMerchandiseDao;
import com.qcloud.project.forest.model.GradeMerchandise;
import com.qcloud.project.forest.model.query.GradeMerchandiseQuery;

@Repository
public class GradeMerchandiseDaoRedisImpl implements GradeMerchandiseDao {

	//@Resource(name = "redis-forest")
	//private Redis redis;

	@Override
	public boolean add(GradeMerchandise gradeMerchandise) {			
		throw new NotImplementedException();
	}

	@Override
	public GradeMerchandise get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(GradeMerchandise gradeMerchandise){
		throw new NotImplementedException();
	}
	
	@Override
	public List<GradeMerchandise> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, GradeMerchandise> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<GradeMerchandise> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<GradeMerchandise> page(GradeMerchandiseQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<GradeMerchandise> listAll(){	
		throw new NotImplementedException();
	}
}

