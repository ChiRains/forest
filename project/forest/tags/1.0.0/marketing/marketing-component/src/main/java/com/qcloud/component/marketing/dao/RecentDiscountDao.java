package com.qcloud.component.marketing.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.marketing.model.RecentDiscount;
import com.qcloud.component.marketing.model.query.RecentDiscountQuery;
		
public interface RecentDiscountDao extends ISimpleDao<RecentDiscount, Long> {

	public boolean add(RecentDiscount recentDiscount);	
	
	public RecentDiscount get(Long id);

	public boolean delete(Long id);
	
	public boolean update(RecentDiscount recentDiscount);
	
	public List<RecentDiscount> list(List<Long> idList);
	
	public Map<Long, RecentDiscount> map(Set<Long> idSet);
	
	public Page<RecentDiscount> page(RecentDiscountQuery query, int start, int size);

	public List<RecentDiscount> listAll();
	
	public List<RecentDiscount> list(RecentDiscountQuery query,int start,int size);
	
	public int count(RecentDiscountQuery query);
}
