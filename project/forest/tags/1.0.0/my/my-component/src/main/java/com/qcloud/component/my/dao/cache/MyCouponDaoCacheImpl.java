package com.qcloud.component.my.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyCouponDao;
import com.qcloud.component.my.model.MyCoupon;
import com.qcloud.component.my.model.query.MyCouponQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class MyCouponDaoCacheImpl implements MyCouponDao {

    @Autowired
    private MyCouponDao myCouponDaoMysqlImpl;

    // @Autowired
    // private MyCouponDao myCouponDaoRedisImpl;
    @Override
    public boolean add(MyCoupon myCoupon) {

        return myCouponDaoMysqlImpl.add(myCoupon);
    }

    @Override
    public MyCoupon get(Long id) {

        return myCouponDaoMysqlImpl.get(id);
        // return CacheLoader.get(myCouponDaoRedisImpl, myCouponDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return myCouponDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MyCoupon myCoupon) {

        return myCouponDaoMysqlImpl.update(myCoupon);
    }

    @Override
    public List<MyCoupon> list(List<Long> idList) {

        return CacheLoader.list(myCouponDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MyCoupon> map(Set<Long> idSet) {

        return CacheLoader.map(myCouponDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MyCoupon> page(int start, int count) {

        return myCouponDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MyCoupon> page(MyCouponQuery query, int start, int count) {

        return myCouponDaoMysqlImpl.page(query, start, count);
    }

    public List<MyCoupon> listAll() {

        return myCouponDaoMysqlImpl.listAll();
    }

    @Override
    public List<MyCoupon> listByUser(long userId, Integer type, int start, int count) {

        return myCouponDaoMysqlImpl.listByUser(userId, type, start, count);
    }

    @Override
    public List<MyCoupon> listCanUseByUser(long userId, Long merchantId, double sum, int start, int count) {

        return myCouponDaoMysqlImpl.listCanUseByUser(userId, merchantId, sum, start, count);
    }

    @Override
    public List<MyCoupon> listByUserAndCoupon(long userId, long couponId) {

        return myCouponDaoMysqlImpl.listByUserAndCoupon(userId, couponId);
    }

    @Override
    public MyCoupon getByCode(String code) {

        return myCouponDaoMysqlImpl.getByCode(code);
    }

    @Override
    public int countByUser(long userId, Integer type) {

        return myCouponDaoMysqlImpl.countByUser(userId, type);
    }
}
