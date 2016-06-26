package com.qcloud.component.goods.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.CombinationMerchandise;
import com.qcloud.component.goods.model.query.CombinationMerchandiseQuery;

public interface CombinationMerchandiseService {
	
	public boolean add(CombinationMerchandise combinationMerchandise);	
	
	public CombinationMerchandise get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(CombinationMerchandise combinationMerchandise);

	public Page<CombinationMerchandise> page(CombinationMerchandiseQuery query, int start, int count);
	
	public List<CombinationMerchandise> listAll();
}

