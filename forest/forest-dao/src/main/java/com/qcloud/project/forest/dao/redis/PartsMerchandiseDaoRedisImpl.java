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
import com.qcloud.project.forest.dao.PartsMerchandiseDao;
import com.qcloud.project.forest.model.PartsMerchandise;
import com.qcloud.project.forest.model.query.PartsMerchandiseQuery;

@Repository
public class PartsMerchandiseDaoRedisImpl implements PartsMerchandiseDao {

	//@Resource(name = "redis-forest")
	//private Redis redis;

	@Override
	public boolean add(PartsMerchandise partsMerchandise) {			
		throw new NotImplementedException();
	}

	@Override
	public PartsMerchandise get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(PartsMerchandise partsMerchandise){
		throw new NotImplementedException();
	}
	
	@Override
	public List<PartsMerchandise> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, PartsMerchandise> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
			
																					public List<PartsMerchandise> listByClassifyId(Long classifyId){
					throw new NotImplementedException();
				}

					
	@Override
	public Page<PartsMerchandise> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<PartsMerchandise> page(PartsMerchandiseQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<PartsMerchandise> listAll(){	
		throw new NotImplementedException();
	}
}

