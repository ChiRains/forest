package com.qcloud.component.goods.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.goods.model.MerchandiseMarketing;
import com.qcloud.component.goods.model.query.MerchandiseMarketingQuery;
		
public interface MerchandiseMarketingDao extends ISimpleDao<MerchandiseMarketing, Long> {

	public boolean add(MerchandiseMarketing merchandiseMarketing);	
	
	public MerchandiseMarketing get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MerchandiseMarketing merchandiseMarketing);
	
	public List<MerchandiseMarketing> list(List<Long> idList);
	
	public Map<Long, MerchandiseMarketing> map(Set<Long> idSet);
	
	public Page<MerchandiseMarketing> page(MerchandiseMarketingQuery query, int start, int size);

	public List<MerchandiseMarketing> listAll();
	
}
