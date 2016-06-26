package com.qcloud.component.goods.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.AttributeDefinitionDao;
import com.qcloud.component.goods.model.AttributeDefinition;
import com.qcloud.component.goods.model.query.AttributeDefinitionQuery;

@Repository
public class AttributeDefinitionDaoCacheImpl implements AttributeDefinitionDao {
	
	@Autowired
	private AttributeDefinitionDao attributeDefinitionDaoMysqlImpl;
	
	@Autowired
	private AttributeDefinitionDao attributeDefinitionDaoRedisImpl;

	@Override
	public boolean add(AttributeDefinition attributeDefinition) {
		return attributeDefinitionDaoMysqlImpl.add(attributeDefinition);
	}

	@Override
	public AttributeDefinition get(Long id) {
		return CacheLoader.get(attributeDefinitionDaoRedisImpl, attributeDefinitionDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return attributeDefinitionDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(AttributeDefinition attributeDefinition){
		return attributeDefinitionDaoMysqlImpl.update(attributeDefinition);
	}
	
	@Override
	public List<AttributeDefinition> list(List<Long> idList) {
		return CacheLoader.list(attributeDefinitionDaoRedisImpl, attributeDefinitionDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, AttributeDefinition> map(Set<Long> idSet){
		return CacheLoader.map(attributeDefinitionDaoRedisImpl, attributeDefinitionDaoMysqlImpl, idSet);
	}

	@Override
	public Page<AttributeDefinition> page(int start, int count){
		return attributeDefinitionDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<AttributeDefinition> page(AttributeDefinitionQuery query, int start, int count){
		return attributeDefinitionDaoMysqlImpl.page(query, start, count);
	}
	
	public List<AttributeDefinition> listAll(){
		return attributeDefinitionDaoMysqlImpl.listAll();
	}
}

