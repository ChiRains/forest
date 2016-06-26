package com.qcloud.component.goods.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.goods.model.CombinationMerchandiseItem;
import com.qcloud.component.goods.model.query.CombinationMerchandiseItemQuery;
		
public interface CombinationMerchandiseItemDao extends ISimpleDao<CombinationMerchandiseItem, Long> {

	public boolean add(CombinationMerchandiseItem combinationMerchandiseItem);	
	
	public CombinationMerchandiseItem get(Long id);

	public boolean delete(Long id);
	
	public boolean update(CombinationMerchandiseItem combinationMerchandiseItem);
	
	public List<CombinationMerchandiseItem> list(List<Long> idList);
	
	public Map<Long, CombinationMerchandiseItem> map(Set<Long> idSet);
	
	public Page<CombinationMerchandiseItem> page(CombinationMerchandiseItemQuery query, int start, int size);

	public List<CombinationMerchandiseItem> listAll();
	
}
