package com.qcloud.component.goods.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.Enumeration;
import com.qcloud.component.goods.model.query.EnumerationQuery;

public interface EnumerationService {
	
	public boolean add(Enumeration enumeration);	
	
	public Enumeration get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(Enumeration enumeration);

	public Page<Enumeration> page(EnumerationQuery query, int start, int count);
	
	public List<Enumeration> listAll();
}

