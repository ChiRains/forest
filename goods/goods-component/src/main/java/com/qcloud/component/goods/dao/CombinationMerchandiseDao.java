package com.qcloud.component.goods.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.goods.model.CombinationMerchandise;
import com.qcloud.component.goods.model.query.CombinationMerchandiseQuery;
		
public interface CombinationMerchandiseDao extends ISimpleDao<CombinationMerchandise, Long> {

	public boolean add(CombinationMerchandise combinationMerchandise);	
	
	public CombinationMerchandise get(Long id);

	public boolean delete(Long id);
	
	public boolean update(CombinationMerchandise combinationMerchandise);
	
	public List<CombinationMerchandise> list(List<Long> idList);
	
	public Map<Long, CombinationMerchandise> map(Set<Long> idSet);
	
	public Page<CombinationMerchandise> page(CombinationMerchandiseQuery query, int start, int size);

	public List<CombinationMerchandise> listAll();
	
}
