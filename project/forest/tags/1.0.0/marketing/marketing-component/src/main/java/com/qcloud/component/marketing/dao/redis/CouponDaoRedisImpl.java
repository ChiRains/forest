package com.qcloud.component.marketing.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.marketing.dao.CouponDao;
import com.qcloud.component.marketing.model.Coupon;
import com.qcloud.component.marketing.model.query.CouponQuery;

@Repository
public class CouponDaoRedisImpl implements CouponDao {

    // @Resource(name = "redis-marketing")
    // private Redis redis;
    @Override
    public boolean add(Coupon coupon) {

        throw new NotImplementedException();
    }

    @Override
    public Coupon get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(Coupon coupon) {

        throw new NotImplementedException();
    }

    @Override
    public List<Coupon> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Coupon> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Coupon> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Coupon> page(CouponQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<Coupon> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<Coupon> listActivityCoupon(long merchantId) {

        throw new NotImplementedException();
    }

    @Override
    public int countActivityCoupon(long merchantId) {

        throw new NotImplementedException();
    }

    @Override
    public List<Coupon> listByPlatform() {

        throw new NotImplementedException();
    }
}
