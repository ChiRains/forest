package com.qcloud.component.goods.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;

public interface UnifiedMerchandiseService {
	
	public boolean add(UnifiedMerchandise unifiedMerchandise);	
	
	public UnifiedMerchandise get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(UnifiedMerchandise unifiedMerchandise);

	public Page<UnifiedMerchandise> page(UnifiedMerchandiseQuery query, int start, int count);
	
	public List<UnifiedMerchandise> listAll();
	
	public Long addThenRetrunId(UnifiedMerchandise unifiedMerchandise);
}

