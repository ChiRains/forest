package com.qcloud.component.goods.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.ClassifyAttribute;
import com.qcloud.component.goods.model.query.ClassifyAttributeQuery;

public interface ClassifyAttributeService {
	
	public boolean add(ClassifyAttribute classifyAttribute);	
	
	public ClassifyAttribute get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(ClassifyAttribute classifyAttribute);

	public Page<ClassifyAttribute> page(ClassifyAttributeQuery query, int start, int count);
	
	public List<ClassifyAttribute> listAll();
}

