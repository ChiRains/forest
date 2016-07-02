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
import com.qcloud.component.sellercenter.dao.StoreDeliveryTimeDao;
import com.qcloud.component.sellercenter.model.StoreDeliveryTime;
import com.qcloud.component.sellercenter.model.query.StoreDeliveryTimeQuery;

@Repository
public class StoreDeliveryTimeDaoRedisImpl implements StoreDeliveryTimeDao {

    // @Resource(name = "redis-sellercenter")
    // private Redis redis;
    @Override
    public boolean add(StoreDeliveryTime storeDeliveryTime) {

        throw new NotImplementedException();
    }

    @Override
    public StoreDeliveryTime get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(StoreDeliveryTime storeDeliveryTime) {

        throw new NotImplementedException();
    }

    @Override
    public List<StoreDeliveryTime> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, StoreDeliveryTime> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<StoreDeliveryTime> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<StoreDeliveryTime> page(StoreDeliveryTimeQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<StoreDeliveryTime> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public StoreDeliveryTime getByStore(Long storeId) {

        throw new NotImplementedException();
    }
}
