package com.qcloud.component.goods.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.ClassifySpecifications;
import com.qcloud.component.goods.model.query.ClassifySpecificationsQuery;

public interface ClassifySpecificationsService {
	
	public boolean add(ClassifySpecifications classifySpecifications);	
	
	public ClassifySpecifications get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(ClassifySpecifications classifySpecifications);

	public Page<ClassifySpecifications> page(ClassifySpecificationsQuery query, int start, int count);
	
	public List<ClassifySpecifications> listAll();
}

