package com.qcloud.component.orderform.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.orderform.model.MembershipCardOrderCoupon;
import com.qcloud.component.orderform.model.query.MembershipCardOrderCouponQuery;
		
public interface MembershipCardOrderCouponDao extends ISimpleDao<MembershipCardOrderCoupon, Long> {

	public boolean add(MembershipCardOrderCoupon membershipCardOrderCoupon);	
	
	public MembershipCardOrderCoupon get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MembershipCardOrderCoupon membershipCardOrderCoupon);
	
	public List<MembershipCardOrderCoupon> list(List<Long> idList);
	
	public Map<Long, MembershipCardOrderCoupon> map(Set<Long> idSet);
	
	public Page<MembershipCardOrderCoupon> page(MembershipCardOrderCouponQuery query, int start, int size);

	public List<MembershipCardOrderCoupon> listAll();
	
}
