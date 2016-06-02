package com.qcloud.component.commoditycenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.commoditycenter.dao.MerchandiseVipDiscountDao;
import com.qcloud.component.commoditycenter.model.MerchandiseVipDiscount;
import com.qcloud.component.commoditycenter.model.query.MerchandiseVipDiscountQuery;

@Repository
public class MerchandiseVipDiscountDaoRedisImpl implements MerchandiseVipDiscountDao {

    // @Resource(name = "redis-commoditycenter")
    // private Redis redis;
    @Override
    public boolean add(MerchandiseVipDiscount merchandiseVipDiscount) {

        throw new NotImplementedException();
    }

    @Override
    public MerchandiseVipDiscount get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MerchandiseVipDiscount merchandiseVipDiscount) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseVipDiscount> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseVipDiscount> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseVipDiscount> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseVipDiscount> page(MerchandiseVipDiscountQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseVipDiscount> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public Double statMin(Long merchandiseItemId) {

        throw new NotImplementedException();
    }

    @Override
    public Double statMax(Long merchandiseItemId) {

        throw new NotImplementedException();
    }

    @Override
    public MerchandiseVipDiscount get(Long userId, Long merchandiseItemId) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseVipDiscount> listByUser(long userId, long classifyId, String classifyBSID, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public boolean deleteByUser(long userId) {

        throw new NotImplementedException();
    }

    @Override
    public int countByUser(long userId, long classifyId, String classifyBSID) {

        throw new NotImplementedException();
    }
}
