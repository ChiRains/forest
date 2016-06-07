package com.qcloud.project.forest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.GiftCouponDao;
import com.qcloud.project.forest.model.GiftCoupon;
import com.qcloud.project.forest.service.GiftCouponService;
import com.qcloud.project.forest.model.query.GiftCouponQuery;
		
@Service
public class GiftCouponServiceImpl implements GiftCouponService{
	
	@Autowired
	private GiftCouponDao giftCouponDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "forest_gift_coupon";

	@Override
	public boolean add(GiftCoupon giftCoupon) {
		long id = autoIdGenerator.get(ID_KEY);
		giftCoupon.setId(id);
		
		return giftCouponDao.add(giftCoupon);
	}

	@Override
	public GiftCoupon get(Long id) {	
		return giftCouponDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return giftCouponDao.delete(id);
	}
	
	@Override
	public boolean update(GiftCoupon giftCoupon) {
		return giftCouponDao.update(giftCoupon);
	}
	
	@Override
	public Page<GiftCoupon> page(GiftCouponQuery query, int start, int count) {
		return giftCouponDao.page(query, start, count);
	}
	
	public List<GiftCoupon> listAll(){
		return giftCouponDao.listAll();
	}
}

