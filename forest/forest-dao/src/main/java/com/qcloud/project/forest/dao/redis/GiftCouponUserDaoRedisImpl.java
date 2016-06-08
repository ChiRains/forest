package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.project.forest.dao.GiftCouponUserDao;
import com.qcloud.project.forest.model.GiftCouponUser;
import com.qcloud.project.forest.model.query.GiftCouponUserQuery;

@Repository
public class GiftCouponUserDaoRedisImpl implements GiftCouponUserDao {

    // @Resource(name = "redis-forest")
    // private Redis redis;
    @Override
    public boolean add(GiftCouponUser giftCouponUser) {

        throw new NotImplementedException();
    }

    @Override
    public GiftCouponUser get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(GiftCouponUser giftCouponUser) {

        throw new NotImplementedException();
    }

    @Override
    public List<GiftCouponUser> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, GiftCouponUser> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<GiftCouponUser> listByUserId(Long userId) {

        throw new NotImplementedException();
    }

    @Override
    public Page<GiftCouponUser> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<GiftCouponUser> page(GiftCouponUserQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<GiftCouponUser> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<GiftCouponUser> listCanUse(long userId) {

        throw new NotImplementedException();
    }
}
