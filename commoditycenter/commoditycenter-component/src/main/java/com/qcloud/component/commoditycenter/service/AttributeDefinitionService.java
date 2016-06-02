package com.qcloud.component.commoditycenter.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.commoditycenter.model.AttributeDefinition;
import com.qcloud.component.commoditycenter.model.query.AttributeDefinitionQuery;

public interface AttributeDefinitionService {
	
	public Long add(AttributeDefinition attributeDefinition);	
	
	public AttributeDefinition get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(AttributeDefinition attributeDefinition);

	public Page<AttributeDefinition> page(AttributeDefinitionQuery query, int start, int count);
	
	public List<AttributeDefinition> listAll();
	
	public List<AttributeDefinition> list(List<Long> idList);
	
	public Page<AttributeDefinition> list4Select(AttributeDefinitionQuery query, int start, int size);
}

