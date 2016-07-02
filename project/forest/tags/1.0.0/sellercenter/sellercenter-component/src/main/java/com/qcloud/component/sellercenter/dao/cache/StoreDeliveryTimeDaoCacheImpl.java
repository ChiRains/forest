package com.qcloud.component.sellercenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.StoreDeliveryTimeDao;
import com.qcloud.component.sellercenter.model.StoreDeliveryTime;
import com.qcloud.component.sellercenter.model.query.StoreDeliveryTimeQuery;

@Repository
public class StoreDeliveryTimeDaoCacheImpl implements StoreDeliveryTimeDao {

    @Autowired
    private StoreDeliveryTimeDao storeDeliveryTimeDaoMysqlImpl;

    // @Autowired
    // private StoreDeliveryTimeDao storeDeliveryTimeDaoRedisImpl;
    @Override
    public boolean add(StoreDeliveryTime storeDeliveryTime) {

        return storeDeliveryTimeDaoMysqlImpl.add(storeDeliveryTime);
    }

    @Override
    public StoreDeliveryTime get(Long id) {

        return storeDeliveryTimeDaoMysqlImpl.get(id);
        // return CacheLoader.get(storeDeliveryTimeDaoRedisImpl, storeDeliveryTimeDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return storeDeliveryTimeDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(StoreDeliveryTime storeDeliveryTime) {

        return storeDeliveryTimeDaoMysqlImpl.update(storeDeliveryTime);
    }

    @Override
    public List<StoreDeliveryTime> list(List<Long> idList) {

        return CacheLoader.list(storeDeliveryTimeDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, StoreDeliveryTime> map(Set<Long> idSet) {

        return CacheLoader.map(storeDeliveryTimeDaoMysqlImpl, idSet);
    }

    @Override
    public Page<StoreDeliveryTime> page(int start, int count) {

        return storeDeliveryTimeDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<StoreDeliveryTime> page(StoreDeliveryTimeQuery query, int start, int count) {

        return storeDeliveryTimeDaoMysqlImpl.page(query, start, count);
    }

    public List<StoreDeliveryTime> listAll() {

        return storeDeliveryTimeDaoMysqlImpl.listAll();
    }

    @Override
    public StoreDeliveryTime getByStore(Long storeId) {

        return storeDeliveryTimeDaoMysqlImpl.getByStore(storeId);
    }
}
