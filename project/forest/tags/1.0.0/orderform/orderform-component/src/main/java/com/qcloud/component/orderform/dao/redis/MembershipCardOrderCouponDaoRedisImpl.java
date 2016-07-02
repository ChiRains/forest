package com.qcloud.component.orderform.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.orderform.dao.MembershipCardOrderCouponDao;
import com.qcloud.component.orderform.model.MembershipCardOrderCoupon;
import com.qcloud.component.orderform.model.query.MembershipCardOrderCouponQuery;

@Repository
public class MembershipCardOrderCouponDaoRedisImpl implements MembershipCardOrderCouponDao {

	//@Resource(name = "redis-orderform")
	//private Redis redis;

	@Override
	public boolean add(MembershipCardOrderCoupon membershipCardOrderCoupon) {			
		throw new NotImplementedException();
	}

	@Override
	public MembershipCardOrderCoupon get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(MembershipCardOrderCoupon membershipCardOrderCoupon){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MembershipCardOrderCoupon> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, MembershipCardOrderCoupon> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<MembershipCardOrderCoupon> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<MembershipCardOrderCoupon> page(MembershipCardOrderCouponQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<MembershipCardOrderCoupon> listAll(){	
		throw new NotImplementedException();
	}
}

