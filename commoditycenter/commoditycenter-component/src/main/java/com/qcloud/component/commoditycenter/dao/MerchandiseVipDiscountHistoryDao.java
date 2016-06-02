package com.qcloud.component.commoditycenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.commoditycenter.model.MerchandiseVipDiscountHistory;
import com.qcloud.component.commoditycenter.model.query.MerchandiseVipDiscountHistoryQuery;
		
public interface MerchandiseVipDiscountHistoryDao extends ISimpleDao<MerchandiseVipDiscountHistory, Long> {

	public boolean add(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory);	
	
	public MerchandiseVipDiscountHistory get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory);
	
	public List<MerchandiseVipDiscountHistory> list(List<Long> idList);
	
	public Map<Long, MerchandiseVipDiscountHistory> map(Set<Long> idSet);
	
	public Page<MerchandiseVipDiscountHistory> page(MerchandiseVipDiscountHistoryQuery query, int start, int size);

	public List<MerchandiseVipDiscountHistory> listAll();
	
}
