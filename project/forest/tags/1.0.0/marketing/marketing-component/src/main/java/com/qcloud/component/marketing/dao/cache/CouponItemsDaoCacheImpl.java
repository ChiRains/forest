package com.qcloud.component.marketing.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.marketing.dao.CouponItemsDao;
import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.model.query.CouponItemsQuery;

@Repository
public class CouponItemsDaoCacheImpl implements CouponItemsDao {
	
	@Autowired
	private CouponItemsDao couponItemsDaoMysqlImpl;
	
	@Autowired
	private CouponItemsDao couponItemsDaoRedisImpl;

	@Override
	public boolean add(CouponItems couponItems) {
		return couponItemsDaoMysqlImpl.add(couponItems);
	}

	@Override
	public CouponItems get(Long id) {
		//return CacheLoader.get(couponItemsDaoRedisImpl, couponItemsDaoMysqlImpl, id);
	    return couponItemsDaoMysqlImpl.get(id);
	}

	@Override
	public boolean delete(Long id){
		return couponItemsDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(CouponItems couponItems){
		return couponItemsDaoMysqlImpl.update(couponItems);
	}
	
	@Override
	public List<CouponItems> list(List<Long> idList) {
		return CacheLoader.list(couponItemsDaoRedisImpl, couponItemsDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, CouponItems> map(Set<Long> idSet){
		return CacheLoader.map(couponItemsDaoRedisImpl, couponItemsDaoMysqlImpl, idSet);
	}

	@Override
	public Page<CouponItems> page(int start, int count){
		return couponItemsDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<CouponItems> page(CouponItemsQuery query, int start, int count){
		return couponItemsDaoMysqlImpl.page(query, start, count);
	}
	
	public List<CouponItems> listAll(){
		return couponItemsDaoMysqlImpl.listAll();
	}

    @Override
    public List<CouponItems> list4Counpon(Long couponId) {
        return couponItemsDaoMysqlImpl.list4Counpon(couponId);
    }

    @Override
    public List<CouponItems> list4Extract(Long couponId) {
        return couponItemsDaoMysqlImpl.list4Extract(couponId);
    }

    @Override
    public List<CouponItems> listByCouponId(Long couponId) {

        return couponItemsDaoMysqlImpl.listByCouponId(couponId);
    }
}

