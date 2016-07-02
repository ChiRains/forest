package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.project.forest.dao.GiftCouponDao;
import com.qcloud.project.forest.model.GiftCoupon;
import com.qcloud.project.forest.model.query.GiftCouponQuery;

@Repository
public class GiftCouponDaoRedisImpl implements GiftCouponDao {

	//@Resource(name = "redis-forest")
	//private Redis redis;

	@Override
	public boolean add(GiftCoupon giftCoupon) {			
		throw new NotImplementedException();
	}

	@Override
	public GiftCoupon get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(GiftCoupon giftCoupon){
		throw new NotImplementedException();
	}
	
	@Override
	public List<GiftCoupon> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, GiftCoupon> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<GiftCoupon> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<GiftCoupon> page(GiftCouponQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<GiftCoupon> listAll(){	
		throw new NotImplementedException();
	}
}

