package com.qcloud.component.goods.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.CombinationMerchandiseItem;
import com.qcloud.component.goods.model.query.CombinationMerchandiseItemQuery;

public interface CombinationMerchandiseItemService {
	
	public boolean add(CombinationMerchandiseItem combinationMerchandiseItem);	
	
	public CombinationMerchandiseItem get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(CombinationMerchandiseItem combinationMerchandiseItem);

	public Page<CombinationMerchandiseItem> page(CombinationMerchandiseItemQuery query, int start, int count);
	
	public List<CombinationMerchandiseItem> listAll();
}

