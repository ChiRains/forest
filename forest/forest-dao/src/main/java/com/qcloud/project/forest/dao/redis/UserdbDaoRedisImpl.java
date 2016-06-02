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
import com.qcloud.project.forest.dao.UserdbDao;
import com.qcloud.project.forest.model.Userdb;
import com.qcloud.project.forest.model.query.UserdbQuery;

@Repository
public class UserdbDaoRedisImpl implements UserdbDao {

	//@Resource(name = "redis-forest")
	//private Redis redis;

	@Override
	public boolean add(Userdb userdb) {			
		throw new NotImplementedException();
	}

	@Override
	public Userdb get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(Userdb userdb){
		throw new NotImplementedException();
	}
	
	@Override
	public List<Userdb> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, Userdb> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<Userdb> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<Userdb> page(UserdbQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<Userdb> listAll(){	
		throw new NotImplementedException();
	}
}

