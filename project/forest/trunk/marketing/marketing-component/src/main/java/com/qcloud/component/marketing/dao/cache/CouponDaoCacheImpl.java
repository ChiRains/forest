package com.qcloud.component.marketing.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.marketing.dao.CouponDao;
import com.qcloud.component.marketing.model.Coupon;
import com.qcloud.component.marketing.model.query.CouponQuery;

@Repository
public class CouponDaoCacheImpl implements CouponDao {

    @Autowired
    private CouponDao couponDaoMysqlImpl;

    // @Autowired
    // private CouponDao couponDaoRedisImpl;
    @Override
    public boolean add(Coupon coupon) {

        return couponDaoMysqlImpl.add(coupon);
    }

    @Override
    public Coupon get(Long id) {

        // return CacheLoader.get(couponDaoRedisImpl, couponDaoMysqlImpl, id);
        return couponDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return couponDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(Coupon coupon) {

        return couponDaoMysqlImpl.update(coupon);
    }

    @Override
    public List<Coupon> list(List<Long> idList) {

        return CacheLoader.list(couponDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, Coupon> map(Set<Long> idSet) {

        return CacheLoader.map(couponDaoMysqlImpl, idSet);
    }

    @Override
    public Page<Coupon> page(int start, int count) {

        return couponDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<Coupon> page(CouponQuery query, int start, int count) {

        return couponDaoMysqlImpl.page(query, start, count);
    }

    public List<Coupon> listAll() {

        return couponDaoMysqlImpl.listAll();
    }

    @Override
    public List<Coupon> listActivityCoupon(long merchantId) {

        return couponDaoMysqlImpl.listActivityCoupon(merchantId);
    }

    @Override
    public int countActivityCoupon(long merchantId) {

        return couponDaoMysqlImpl.countActivityCoupon(merchantId);
    }

    @Override
    public List<Coupon> listByPlatform() {

        return couponDaoMysqlImpl.listByPlatform();
    }
}
