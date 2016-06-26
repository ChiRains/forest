package com.qcloud.component.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.dao.AttributeDefinitionDao;
import com.qcloud.component.goods.model.AttributeDefinition;
import com.qcloud.component.goods.service.AttributeDefinitionService;
import com.qcloud.component.goods.model.query.AttributeDefinitionQuery;
		
@Service
public class AttributeDefinitionServiceImpl implements AttributeDefinitionService{
	
	@Autowired
	private AttributeDefinitionDao attributeDefinitionDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "goods_attribute_definition";

	@Override
	public boolean add(AttributeDefinition attributeDefinition) {
		long id = autoIdGenerator.get(ID_KEY);
		attributeDefinition.setId(id);
		
		return attributeDefinitionDao.add(attributeDefinition);
	}

	@Override
	public AttributeDefinition get(Long id) {	
		return attributeDefinitionDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return attributeDefinitionDao.delete(id);
	}
	
	@Override
	public boolean update(AttributeDefinition attributeDefinition) {
		return attributeDefinitionDao.update(attributeDefinition);
	}
	
	@Override
	public Page<AttributeDefinition> page(AttributeDefinitionQuery query, int start, int count) {
		return attributeDefinitionDao.page(query, start, count);
	}
	
	public List<AttributeDefinition> listAll(){
		return attributeDefinitionDao.listAll();
	}
}

