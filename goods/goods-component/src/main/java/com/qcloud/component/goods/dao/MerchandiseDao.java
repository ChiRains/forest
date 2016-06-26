package com.qcloud.component.goods.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.query.MerchandiseQuery;
		
public interface MerchandiseDao extends ISimpleDao<Merchandise, Long> {

	public boolean add(Merchandise merchandise);	
	
	public Merchandise get(Long id);

	public boolean delete(Long id);
	
	public boolean update(Merchandise merchandise);
	
	public List<Merchandise> list(List<Long> idList);
	
	public Map<Long, Merchandise> map(Set<Long> idSet);
	
	public Page<Merchandise> page(MerchandiseQuery query, int start, int size);

	public List<Merchandise> listAll();
	
}
