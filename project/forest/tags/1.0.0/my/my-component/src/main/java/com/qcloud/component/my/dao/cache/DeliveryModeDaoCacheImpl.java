package com.qcloud.component.my.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.DeliveryModeDao;
import com.qcloud.component.my.model.DeliveryMode;
import com.qcloud.component.my.model.query.DeliveryModeQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class DeliveryModeDaoCacheImpl implements DeliveryModeDao {

    @Autowired
    private DeliveryModeDao deliveryModeDaoMysqlImpl;

    // @Autowired
    // private DeliveryModeDao deliveryModeDaoRedisImpl;
    @Override
    public boolean add(DeliveryMode deliveryMode) {

        return deliveryModeDaoMysqlImpl.add(deliveryMode);
    }

    @Override
    public DeliveryMode get(Long id) {

        return deliveryModeDaoMysqlImpl.get(id);
        // return CacheLoader.get(deliveryModeDaoRedisImpl, deliveryModeDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return deliveryModeDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(DeliveryMode deliveryMode) {

        return deliveryModeDaoMysqlImpl.update(deliveryMode);
    }

    @Override
    public List<DeliveryMode> list(List<Long> idList) {

        return CacheLoader.list(deliveryModeDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, DeliveryMode> map(Set<Long> idSet) {

        return CacheLoader.map(deliveryModeDaoMysqlImpl, idSet);
    }

    @Override
    public Page<DeliveryMode> page(int start, int count) {

        return deliveryModeDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<DeliveryMode> page(DeliveryModeQuery query, int start, int count) {

        return deliveryModeDaoMysqlImpl.page(query, start, count);
    }

    public List<DeliveryMode> listAll() {

        return deliveryModeDaoMysqlImpl.listAll();
    }

    @Override
    public DeliveryMode getByUser(Long userId) {

        return deliveryModeDaoMysqlImpl.getByUser(userId);
    }
}
