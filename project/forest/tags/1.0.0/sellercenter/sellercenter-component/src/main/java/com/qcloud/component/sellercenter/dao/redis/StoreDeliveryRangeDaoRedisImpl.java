package com.qcloud.component.sellercenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.sellercenter.dao.StoreDeliveryRangeDao;
import com.qcloud.component.sellercenter.model.StoreDeliveryRange;
import com.qcloud.component.sellercenter.model.query.StoreDeliveryRangeQuery;

@Repository
public class StoreDeliveryRangeDaoRedisImpl implements StoreDeliveryRangeDao {

    // @Resource(name = "redis-sellercenter")
    // private Redis redis;
    @Override
    public boolean add(StoreDeliveryRange storeDeliveryRange) {

        throw new NotImplementedException();
    }

    @Override
    public StoreDeliveryRange get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(StoreDeliveryRange storeDeliveryRange) {

        throw new NotImplementedException();
    }

    @Override
    public List<StoreDeliveryRange> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, StoreDeliveryRange> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<StoreDeliveryRange> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<StoreDeliveryRange> page(StoreDeliveryRangeQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<StoreDeliveryRange> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public StoreDeliveryRange getByStore(Long storeId) {

        throw new NotImplementedException();
    }
}
