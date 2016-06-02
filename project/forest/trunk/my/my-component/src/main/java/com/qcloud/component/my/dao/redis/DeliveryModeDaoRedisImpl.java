package com.qcloud.component.my.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.DeliveryModeDao;
import com.qcloud.component.my.model.DeliveryMode;
import com.qcloud.component.my.model.query.DeliveryModeQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class DeliveryModeDaoRedisImpl implements DeliveryModeDao {

    // @Resource(name = "redis-my")
    // private Redis redis;
    @Override
    public boolean add(DeliveryMode deliveryMode) {

        throw new NotImplementedException();
    }

    @Override
    public DeliveryMode get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(DeliveryMode deliveryMode) {

        throw new NotImplementedException();
    }

    @Override
    public List<DeliveryMode> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, DeliveryMode> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<DeliveryMode> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<DeliveryMode> page(DeliveryModeQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<DeliveryMode> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public DeliveryMode getByUser(Long userId) {

        throw new NotImplementedException();
    }
}
