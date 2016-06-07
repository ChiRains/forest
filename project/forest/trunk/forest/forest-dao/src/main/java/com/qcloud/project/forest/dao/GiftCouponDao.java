package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.GiftCoupon;
import com.qcloud.project.forest.model.query.GiftCouponQuery;
		
public interface GiftCouponDao extends ISimpleDao<GiftCoupon, Long> {

	public boolean add(GiftCoupon giftCoupon);	
	
	public GiftCoupon get(Long id);

	public boolean delete(Long id);
	
	public boolean update(GiftCoupon giftCoupon);
	
	public List<GiftCoupon> list(List<Long> idList);
	
	public Map<Long, GiftCoupon> map(Set<Long> idSet);
	
	public Page<GiftCoupon> page(GiftCouponQuery query, int start, int size);

	public List<GiftCoupon> listAll();
	
}
