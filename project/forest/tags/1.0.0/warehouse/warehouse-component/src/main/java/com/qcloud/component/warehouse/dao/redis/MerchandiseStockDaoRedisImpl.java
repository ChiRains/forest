package com.qcloud.component.warehouse.dao.redis;

import com.qcloud.component.warehouse.dao.MerchandiseStockDao;
import com.qcloud.component.warehouse.model.MerchandiseStock;
import com.qcloud.component.warehouse.model.query.MerchandiseStockQuery;
import com.qcloud.pirates.data.Page;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class MerchandiseStockDaoRedisImpl implements MerchandiseStockDao {

    //@Resource(name = "redis-warehouse")
    //private Redis redis;

    @Override
    public boolean add(MerchandiseStock merchandiseStock) {
        throw new NotImplementedException();
    }

    @Override
    public MerchandiseStock get(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public boolean update(MerchandiseStock merchandiseStock) {
        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseStock> list(List<Long> idList) {
        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseStock> map(Set<Long> idSet) {
        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseStock> page(int start, int count) {
        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseStock> page(MerchandiseStockQuery query, int start, int count) {
        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseStock> listAll() {
        throw new NotImplementedException();
    }

    @Override
    public MerchandiseStock get(HashMap where) {
        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseStock> list(HashMap where) {
        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseStock> page(HashMap where, int start, int size) {
        throw new NotImplementedException();
    }
}

