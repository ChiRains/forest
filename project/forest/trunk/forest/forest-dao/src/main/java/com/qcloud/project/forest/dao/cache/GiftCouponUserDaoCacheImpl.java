package com.qcloud.project.forest.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.GiftCouponUserDao;
import com.qcloud.project.forest.model.GiftCouponUser;
import com.qcloud.project.forest.model.query.GiftCouponUserQuery;

@Repository
public class GiftCouponUserDaoCacheImpl implements GiftCouponUserDao {

    @Autowired
    private GiftCouponUserDao giftCouponUserDaoMysqlImpl;

    @Autowired
    private GiftCouponUserDao giftCouponUserDaoRedisImpl;

    @Override
    public boolean add(GiftCouponUser giftCouponUser) {

        return giftCouponUserDaoMysqlImpl.add(giftCouponUser);
    }

    @Override
    public GiftCouponUser get(Long id) {

        return giftCouponUserDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return giftCouponUserDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(GiftCouponUser giftCouponUser) {

        return giftCouponUserDaoMysqlImpl.update(giftCouponUser);
    }

    @Override
    public List<GiftCouponUser> list(List<Long> idList) {

        return CacheLoader.list(giftCouponUserDaoRedisImpl, giftCouponUserDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, GiftCouponUser> map(Set<Long> idSet) {

        return CacheLoader.map(giftCouponUserDaoRedisImpl, giftCouponUserDaoMysqlImpl, idSet);
    }

    public List<GiftCouponUser> listByUserId(Long userId) {

        return giftCouponUserDaoMysqlImpl.listByUserId(userId);
    }

    @Override
    public Page<GiftCouponUser> page(int start, int count) {

        return giftCouponUserDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<GiftCouponUser> page(GiftCouponUserQuery query, int start, int count) {

        return giftCouponUserDaoMysqlImpl.page(query, start, count);
    }

    public List<GiftCouponUser> listAll() {

        return giftCouponUserDaoMysqlImpl.listAll();
    }
}
