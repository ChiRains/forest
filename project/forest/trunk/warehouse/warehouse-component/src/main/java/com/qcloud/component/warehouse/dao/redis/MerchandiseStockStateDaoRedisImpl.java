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
import com.qcloud.component.warehouse.dao.MerchandiseStockStateDao;
import com.qcloud.component.warehouse.model.MerchandiseStockState;
import com.qcloud.component.warehouse.model.query.MerchandiseStockStateQuery;

@Repository
public class MerchandiseStockStateDaoRedisImpl implements MerchandiseStockStateDao {

    // @Resource(name = "redis-warehouse")
    // private Redis redis;
    @Override
    public boolean add(MerchandiseStockState merchandiseStockState) {

        throw new NotImplementedException();
    }

    @Override
    public MerchandiseStockState get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MerchandiseStockState merchandiseStockState) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseStockState> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseStockState> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseStockState> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseStockState> page(MerchandiseStockStateQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseStockState> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseStockState> listAll(Map<String, Object> map) {

        throw new NotImplementedException();
    }
}
