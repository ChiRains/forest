package com.qcloud.component.goods.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.goods.dao.EnumerationDao;
import com.qcloud.component.goods.model.Enumeration;
import com.qcloud.component.goods.model.query.EnumerationQuery;

@Repository
public class EnumerationDaoRedisImpl implements EnumerationDao {

	//@Resource(name = "redis-goods")
	//private Redis redis;

	@Override
	public boolean add(Enumeration enumeration) {			
		throw new NotImplementedException();
	}

	@Override
	public Enumeration get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(Enumeration enumeration){
		throw new NotImplementedException();
	}
	
	@Override
	public List<Enumeration> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, Enumeration> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<Enumeration> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<Enumeration> page(EnumerationQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<Enumeration> listAll(){	
		throw new NotImplementedException();
	}
}

