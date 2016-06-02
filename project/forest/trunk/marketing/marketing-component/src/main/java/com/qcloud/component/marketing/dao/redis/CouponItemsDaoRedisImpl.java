package com.qcloud.component.marketing.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.marketing.dao.CouponItemsDao;
import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.model.query.CouponItemsQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class CouponItemsDaoRedisImpl implements CouponItemsDao {

    // @Resource(name = "redis-marketing")
    // private Redis redis;
    @Override
    public boolean add(CouponItems couponItems) {

        throw new NotImplementedException();
    }

    @Override
    public CouponItems get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(CouponItems couponItems) {

        throw new NotImplementedException();
    }

    @Override
    public List<CouponItems> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, CouponItems> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<CouponItems> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<CouponItems> page(CouponItemsQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<CouponItems> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<CouponItems> list4Counpon(Long couponId) {

        throw new NotImplementedException();
    }

    @Override
    public List<CouponItems> list4Extract(Long couponId) {

        throw new NotImplementedException();
    }

    @Override
    public List<CouponItems> listByCouponId(Long couponId) {

        throw new NotImplementedException();
    }
}
