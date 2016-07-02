package com.qcloud.component.my.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyCouponDao;
import com.qcloud.component.my.model.MyCoupon;
import com.qcloud.component.my.model.query.MyCouponQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MyCouponDaoRedisImpl implements MyCouponDao {

    // @Resource(name = "redis-my")
    // private Redis redis;
    @Override
    public boolean add(MyCoupon myCoupon) {

        throw new NotImplementedException();
    }

    @Override
    public MyCoupon get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MyCoupon myCoupon) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyCoupon> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyCoupon> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyCoupon> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyCoupon> page(MyCouponQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyCoupon> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<MyCoupon> listByUser(long userId, Integer type, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyCoupon> listByUserAndCoupon(long userId, long couponId) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyCoupon> listCanUseByUser(long userId, Long merchantId, double sum, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public MyCoupon getByCode(String code) {

        throw new NotImplementedException();
    }

    @Override
    public int countByUser(long userId, Integer type) {

        throw new NotImplementedException();
    }
}
