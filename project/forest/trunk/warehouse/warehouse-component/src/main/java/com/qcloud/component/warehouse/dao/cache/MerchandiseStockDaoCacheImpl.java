package com.qcloud.component.warehouse.dao.cache;

import com.qcloud.component.warehouse.dao.MerchandiseStockDao;
import com.qcloud.component.warehouse.model.MerchandiseStock;
import com.qcloud.component.warehouse.model.query.MerchandiseStockQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class MerchandiseStockDaoCacheImpl implements MerchandiseStockDao {

    @Autowired
    private MerchandiseStockDao merchandiseStockDaoMysqlImpl;

    @Autowired
    private MerchandiseStockDao merchandiseStockDaoRedisImpl;

    @Override
    public boolean add(MerchandiseStock merchandiseStock) {
        return merchandiseStockDaoMysqlImpl.add(merchandiseStock);
    }

    @Override
    public MerchandiseStock get(Long id) {
        return merchandiseStockDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {
        return merchandiseStockDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MerchandiseStock merchandiseStock) {
        return merchandiseStockDaoMysqlImpl.update(merchandiseStock);
    }

    @Override
    public List<MerchandiseStock> list(List<Long> idList) {
        return CacheLoader.list(merchandiseStockDaoRedisImpl, merchandiseStockDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MerchandiseStock> map(Set<Long> idSet) {
        return CacheLoader.map(merchandiseStockDaoRedisImpl, merchandiseStockDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MerchandiseStock> page(int start, int count) {
        return merchandiseStockDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MerchandiseStock> page(MerchandiseStockQuery query, int start, int count) {
        return merchandiseStockDaoMysqlImpl.page(query, start, count);
    }

    @Override
    public List<MerchandiseStock> listAll() {
        return merchandiseStockDaoMysqlImpl.listAll();
    }

    @Override
    public MerchandiseStock get(HashMap where) {
        return merchandiseStockDaoMysqlImpl.get(where);
    }

    @Override
    public List<MerchandiseStock> list(HashMap where) {
        return merchandiseStockDaoMysqlImpl.list(where);
    }

    @Override
    public Page<MerchandiseStock> page(HashMap where, int start, int size) {
        return merchandiseStockDaoMysqlImpl.page(where, start, size);
    }
}

