package com.qcloud.component.commoditycenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.commoditycenter.dao.UnifiedMerchandiseDao;
import com.qcloud.component.commoditycenter.model.UnifiedMerchandise;
import com.qcloud.component.commoditycenter.model.query.UnifiedMerchandiseQuery;

@Repository
public class UnifiedMerchandiseDaoRedisImpl implements UnifiedMerchandiseDao {

	//@Resource(name = "redis-commoditycenter")
	//private Redis redis;

	@Override
	public boolean add(UnifiedMerchandise unifiedMerchandise) {			
		throw new NotImplementedException();
	}

	@Override
	public UnifiedMerchandise get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(UnifiedMerchandise unifiedMerchandise){
		throw new NotImplementedException();
	}
	
	@Override
	public List<UnifiedMerchandise> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, UnifiedMerchandise> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<UnifiedMerchandise> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<UnifiedMerchandise> page(UnifiedMerchandiseQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<UnifiedMerchandise> listAll(){	
		throw new NotImplementedException();
	}
}
