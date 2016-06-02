package com.qcloud.component.orderform.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.MembershipCardOrderCouponDao;
import com.qcloud.component.orderform.model.MembershipCardOrderCoupon;
import com.qcloud.component.orderform.model.query.MembershipCardOrderCouponQuery;

@Repository
public class MembershipCardOrderCouponDaoCacheImpl implements MembershipCardOrderCouponDao {
	
	@Autowired
	private MembershipCardOrderCouponDao membershipCardOrderCouponDaoMysqlImpl;
	
	@Autowired
	private MembershipCardOrderCouponDao membershipCardOrderCouponDaoRedisImpl;

	@Override
	public boolean add(MembershipCardOrderCoupon membershipCardOrderCoupon) {
		return membershipCardOrderCouponDaoMysqlImpl.add(membershipCardOrderCoupon);
	}

	@Override
	public MembershipCardOrderCoupon get(Long id) {
		return CacheLoader.get(membershipCardOrderCouponDaoRedisImpl, membershipCardOrderCouponDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return membershipCardOrderCouponDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MembershipCardOrderCoupon membershipCardOrderCoupon){
		return membershipCardOrderCouponDaoMysqlImpl.update(membershipCardOrderCoupon);
	}
	
	@Override
	public List<MembershipCardOrderCoupon> list(List<Long> idList) {
		return CacheLoader.list(membershipCardOrderCouponDaoRedisImpl, membershipCardOrderCouponDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MembershipCardOrderCoupon> map(Set<Long> idSet){
		return CacheLoader.map(membershipCardOrderCouponDaoRedisImpl, membershipCardOrderCouponDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MembershipCardOrderCoupon> page(int start, int count){
		return membershipCardOrderCouponDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MembershipCardOrderCoupon> page(MembershipCardOrderCouponQuery query, int start, int count){
		return membershipCardOrderCouponDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MembershipCardOrderCoupon> listAll(){
		return membershipCardOrderCouponDaoMysqlImpl.listAll();
	}
}

