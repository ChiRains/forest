package com.qcloud.component.goods.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsQuery;
		
public interface MerchandiseSpecificationsDao extends ISimpleDao<MerchandiseSpecifications, Long> {

	public boolean add(MerchandiseSpecifications merchandiseSpecifications);	
	
	public MerchandiseSpecifications get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MerchandiseSpecifications merchandiseSpecifications);
	
	public List<MerchandiseSpecifications> list(List<Long> idList);
	
	public Map<Long, MerchandiseSpecifications> map(Set<Long> idSet);
	
	public Page<MerchandiseSpecifications> page(MerchandiseSpecificationsQuery query, int start, int size);

	public List<MerchandiseSpecifications> listAll();
	
}
