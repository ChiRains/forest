package com.qcloud.project.forest.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.GiftCoupon;
import com.qcloud.project.forest.model.query.GiftCouponQuery;

public interface GiftCouponService {
	
	public boolean add(GiftCoupon giftCoupon);	
	
	public GiftCoupon get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(GiftCoupon giftCoupon);

	public Page<GiftCoupon> page(GiftCouponQuery query, int start, int count);
	
	public List<GiftCoupon> listAll();
}

