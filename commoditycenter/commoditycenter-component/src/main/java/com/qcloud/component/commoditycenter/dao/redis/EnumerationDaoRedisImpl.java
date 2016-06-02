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
import com.qcloud.component.commoditycenter.dao.EnumerationDao;
import com.qcloud.component.commoditycenter.model.Enumeration;
import com.qcloud.component.commoditycenter.model.query.EnumerationQuery;

@Repository
public class EnumerationDaoRedisImpl implements EnumerationDao {

	//@Resource(name = "redis-commoditycenter")
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

    @Override
    public List<Enumeration> listByName(String name) {
        throw new NotImplementedException();
    }

    @Override
    public boolean existByName(String name) {
        throw new NotImplementedException();
    }

    @Override
    public boolean deleteByName(String name) {
        throw new NotImplementedException();
    }

    @Override
    public List<String> listNames() {
        throw new NotImplementedException();
    }
}

