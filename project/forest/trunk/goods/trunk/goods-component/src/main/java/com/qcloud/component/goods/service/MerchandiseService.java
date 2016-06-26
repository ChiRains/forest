package com.qcloud.component.goods.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.query.MerchandiseQuery;

public interface MerchandiseService {
	
	public boolean add(Merchandise merchandise);	
	
	public Merchandise get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(Merchandise merchandise);

	public Page<Merchandise> page(MerchandiseQuery query, int start, int count);
	
	public List<Merchandise> listAll();
}

