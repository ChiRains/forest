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
import com.qcloud.project.forest.dao.ForestOrderDao;
import com.qcloud.project.forest.model.ForestOrder;
import com.qcloud.project.forest.model.query.ForestOrderQuery;

@Repository
public class ForestOrderDaoRedisImpl implements ForestOrderDao {

    // @Resource(name = "redis-forest")
    // private Redis redis;
    @Override
    public boolean add(ForestOrder forestOrder) {

        throw new NotImplementedException();
    }

    @Override
    public ForestOrder get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(ForestOrder forestOrder) {

        throw new NotImplementedException();
    }

    @Override
    public List<ForestOrder> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ForestOrder> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ForestOrder> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ForestOrder> page(ForestOrderQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<ForestOrder> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public ForestOrder getByOrder(long orderId) {

        throw new NotImplementedException();
    }
}
