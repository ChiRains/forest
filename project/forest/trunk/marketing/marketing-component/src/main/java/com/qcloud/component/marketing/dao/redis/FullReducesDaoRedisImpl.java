package com.qcloud.component.marketing.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.marketing.dao.FullReducesDao;
import com.qcloud.component.marketing.model.FullReduces;
import com.qcloud.component.marketing.model.query.FullReducesQuery;

@Repository
public class FullReducesDaoRedisImpl implements FullReducesDao {

	//@Resource(name = "redis-marketing")
	//private Redis redis;

	@Override
	public boolean add(FullReduces fullReduces) {			
		throw new NotImplementedException();
	}

	@Override
	public FullReduces get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(FullReduces fullReduces){
		throw new NotImplementedException();
	}
	
	@Override
	public List<FullReduces> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, FullReduces> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<FullReduces> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<FullReduces> page(FullReducesQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<FullReduces> listAll(){	
		throw new NotImplementedException();
	}
}

