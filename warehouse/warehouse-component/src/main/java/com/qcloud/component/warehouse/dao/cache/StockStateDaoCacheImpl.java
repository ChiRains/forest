package com.qcloud.component.warehouse.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.warehouse.dao.StockStateDao;
import com.qcloud.component.warehouse.model.StockState;
import com.qcloud.component.warehouse.model.query.StockStateQuery;

@Repository
public class StockStateDaoCacheImpl implements StockStateDao {

    @Autowired
    private StockStateDao stockStateDaoMysqlImpl;

    @Autowired
    private StockStateDao stockStateDaoRedisImpl;

    @Override
    public boolean add(StockState stockState) {

        return stockStateDaoMysqlImpl.add(stockState);
    }

    @Override
    public StockState get(Long id) {

        return stockStateDaoMysqlImpl.get(id);
        // return CacheLoader.get(stockStateDaoRedisImpl, stockStateDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return stockStateDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(StockState stockState) {

        return stockStateDaoMysqlImpl.update(stockState);
    }

    @Override
    public List<StockState> list(List<Long> idList) {

        return CacheLoader.list(stockStateDaoRedisImpl, stockStateDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, StockState> map(Set<Long> idSet) {

        return CacheLoader.map(stockStateDaoRedisImpl, stockStateDaoMysqlImpl, idSet);
    }

    @Override
    public Page<StockState> page(int start, int count) {

        return stockStateDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<StockState> page(StockStateQuery query, int start, int count) {

        return stockStateDaoMysqlImpl.page(query, start, count);
    }

    public List<StockState> listAll() {

        return stockStateDaoMysqlImpl.listAll();
    }

    @Override
    public List<StockState> listAll(Map<String, Object> map) {

        return stockStateDaoMysqlImpl.listAll(map);
    }
}
