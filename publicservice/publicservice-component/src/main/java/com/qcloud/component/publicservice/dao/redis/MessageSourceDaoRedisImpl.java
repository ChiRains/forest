package com.qcloud.component.publicservice.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.publicservice.dao.MessageSourceDao;
import com.qcloud.component.publicservice.model.MessageSource;
import com.qcloud.component.publicservice.model.query.MessageSourceQuery;

@Repository
public class MessageSourceDaoRedisImpl implements MessageSourceDao {

	//@Resource(name = "redis-publicservice")
	//private Redis redis;

	@Override
	public boolean add(MessageSource messageSource) {			
		throw new NotImplementedException();
	}

	@Override
	public MessageSource get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MessageSource messageSource){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MessageSource> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MessageSource> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MessageSource> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MessageSource> page(MessageSourceQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MessageSource> listAll(){	
		throw new NotImplementedException();
	}
}

