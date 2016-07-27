package com.qcloud.component.marketing.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.marketing.model.FullReduces;
import com.qcloud.component.marketing.model.query.FullReducesQuery;

public interface FullReducesService {
	
	public boolean add(FullReduces fullReduces);	
	
	public FullReduces get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(FullReduces fullReduces);

	public Page<FullReduces> page(FullReducesQuery query, int start, int count);
	
	public List<FullReduces> listAll();
}

