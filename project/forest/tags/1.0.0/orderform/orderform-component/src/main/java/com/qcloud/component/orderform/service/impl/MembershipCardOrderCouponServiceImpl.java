package com.qcloud.component.orderform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.MembershipCardOrderCouponDao;
import com.qcloud.component.orderform.model.MembershipCardOrderCoupon;
import com.qcloud.component.orderform.service.MembershipCardOrderCouponService;
import com.qcloud.component.orderform.model.query.MembershipCardOrderCouponQuery;
		
@Service
public class MembershipCardOrderCouponServiceImpl implements MembershipCardOrderCouponService{
	
	@Autowired
	private MembershipCardOrderCouponDao membershipCardOrderCouponDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "orderform_membership_card_order_coupon";

	@Override
	public boolean add(MembershipCardOrderCoupon membershipCardOrderCoupon) {
		long id = autoIdGenerator.get(ID_KEY);
		membershipCardOrderCoupon.setId(id);
		
		return membershipCardOrderCouponDao.add(membershipCardOrderCoupon);
	}

	@Override
	public MembershipCardOrderCoupon get(Long id) {	
		return membershipCardOrderCouponDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return membershipCardOrderCouponDao.delete(id);
	}
	
	@Override
	public boolean update(MembershipCardOrderCoupon membershipCardOrderCoupon) {
		return membershipCardOrderCouponDao.update(membershipCardOrderCoupon);
	}
	
	@Override
	public Page<MembershipCardOrderCoupon> page(MembershipCardOrderCouponQuery query, int start, int count) {
		return membershipCardOrderCouponDao.page(query, start, count);
	}
	
	public List<MembershipCardOrderCoupon> listAll(){
		return membershipCardOrderCouponDao.listAll();
	}
}

