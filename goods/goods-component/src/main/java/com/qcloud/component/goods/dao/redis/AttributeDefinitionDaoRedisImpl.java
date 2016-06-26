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
import com.qcloud.component.goods.dao.AttributeDefinitionDao;
import com.qcloud.component.goods.model.AttributeDefinition;
import com.qcloud.component.goods.model.query.AttributeDefinitionQuery;

@Repository
public class AttributeDefinitionDaoRedisImpl implements AttributeDefinitionDao {

	//@Resource(name = "redis-goods")
	//private Redis redis;

	@Override
	public boolean add(AttributeDefinition attributeDefinition) {			
		throw new NotImplementedException();
	}

	@Override
	public AttributeDefinition get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(AttributeDefinition attributeDefinition){
		throw new NotImplementedException();
	}
	
	@Override
	public List<AttributeDefinition> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, AttributeDefinition> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<AttributeDefinition> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<AttributeDefinition> page(AttributeDefinitionQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<AttributeDefinition> listAll(){	
		throw new NotImplementedException();
	}
}

