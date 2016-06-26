package com.qcloud.component.goods.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.MerchandiseItem;
import com.qcloud.component.goods.model.query.MerchandiseItemQuery;

public interface MerchandiseItemService {
	
	public boolean add(MerchandiseItem merchandiseItem);	
	
	public MerchandiseItem get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(MerchandiseItem merchandiseItem);

	public Page<MerchandiseItem> page(MerchandiseItemQuery query, int start, int count);
	
	public List<MerchandiseItem> listAll();
}

