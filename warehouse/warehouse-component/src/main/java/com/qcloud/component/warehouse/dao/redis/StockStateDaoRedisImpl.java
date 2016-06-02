package com.qcloud.component.warehouse.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.warehouse.dao.StockStateDao;
import com.qcloud.component.warehouse.model.StockState;
import com.qcloud.component.warehouse.model.query.StockStateQuery;

@Repository
public class StockStateDaoRedisImpl implements StockStateDao {

    // @Resource(name = "redis-warehouse")
    // private Redis redis;
    @Override
    public boolean add(StockState stockState) {

        throw new NotImplementedException();
    }

    @Override
    public StockState get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(StockState stockState) {

        throw new NotImplementedException();
    }

    @Override
    public List<StockState> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, StockState> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<StockState> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<StockState> page(StockStateQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<StockState> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<StockState> listAll(Map<String, Object> map) {

        throw new NotImplementedException();
    }
}
