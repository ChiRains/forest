package com.qcloud.component.orderform.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.model.MembershipCardOrderCoupon;
import com.qcloud.component.orderform.model.query.MembershipCardOrderCouponQuery;

public interface MembershipCardOrderCouponService {
	
	public boolean add(MembershipCardOrderCoupon membershipCardOrderCoupon);	
	
	public MembershipCardOrderCoupon get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(MembershipCardOrderCoupon membershipCardOrderCoupon);

	public Page<MembershipCardOrderCoupon> page(MembershipCardOrderCouponQuery query, int start, int count);
	
	public List<MembershipCardOrderCoupon> listAll();
}

