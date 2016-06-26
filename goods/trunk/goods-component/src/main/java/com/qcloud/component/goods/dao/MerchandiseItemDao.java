package com.qcloud.component.goods.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.goods.model.MerchandiseItem;
import com.qcloud.component.goods.model.query.MerchandiseItemQuery;
		
public interface MerchandiseItemDao extends ISimpleDao<MerchandiseItem, Long> {

	public boolean add(MerchandiseItem merchandiseItem);	
	
	public MerchandiseItem get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MerchandiseItem merchandiseItem);
	
	public List<MerchandiseItem> list(List<Long> idList);
	
	public Map<Long, MerchandiseItem> map(Set<Long> idSet);
	
	public Page<MerchandiseItem> page(MerchandiseItemQuery query, int start, int size);

	public List<MerchandiseItem> listAll();
	
}
