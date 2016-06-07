package com.qcloud.project.forest.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.GiftCouponDao;
import com.qcloud.project.forest.model.GiftCoupon;
import com.qcloud.project.forest.model.query.GiftCouponQuery;

@Repository
public class GiftCouponDaoCacheImpl implements GiftCouponDao {
	
	@Autowired
	private GiftCouponDao giftCouponDaoMysqlImpl;
	
	@Autowired
	private GiftCouponDao giftCouponDaoRedisImpl;

	@Override
	public boolean add(GiftCoupon giftCoupon) {
		return giftCouponDaoMysqlImpl.add(giftCoupon);
	}

	@Override
	public GiftCoupon get(Long id) {
		return CacheLoader.get(giftCouponDaoRedisImpl, giftCouponDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return giftCouponDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(GiftCoupon giftCoupon){
		return giftCouponDaoMysqlImpl.update(giftCoupon);
	}
	
	@Override
	public List<GiftCoupon> list(List<Long> idList) {
		return CacheLoader.list(giftCouponDaoRedisImpl, giftCouponDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, GiftCoupon> map(Set<Long> idSet){
		return CacheLoader.map(giftCouponDaoRedisImpl, giftCouponDaoMysqlImpl, idSet);
	}

	@Override
	public Page<GiftCoupon> page(int start, int count){
		return giftCouponDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<GiftCoupon> page(GiftCouponQuery query, int start, int count){
		return giftCouponDaoMysqlImpl.page(query, start, count);
	}
	
	public List<GiftCoupon> listAll(){
		return giftCouponDaoMysqlImpl.listAll();
	}
}

