package com.qcloud.component.goods.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;
		
public interface UnifiedMerchandiseDao extends ISimpleDao<UnifiedMerchandise, Long> {

	public boolean add(UnifiedMerchandise unifiedMerchandise);	
	
	public UnifiedMerchandise get(Long id);

	public boolean delete(Long id);
	
	public boolean update(UnifiedMerchandise unifiedMerchandise);
	
	public List<UnifiedMerchandise> list(List<Long> idList);
	
	public Map<Long, UnifiedMerchandise> map(Set<Long> idSet);
	
	public Page<UnifiedMerchandise> page(UnifiedMerchandiseQuery query, int start, int size);

	public List<UnifiedMerchandise> listAll();
	
}
