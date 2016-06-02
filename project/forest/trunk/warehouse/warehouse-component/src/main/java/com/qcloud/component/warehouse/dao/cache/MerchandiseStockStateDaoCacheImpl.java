package com.qcloud.component.warehouse.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.warehouse.dao.MerchandiseStockStateDao;
import com.qcloud.component.warehouse.model.MerchandiseStockState;
import com.qcloud.component.warehouse.model.query.MerchandiseStockStateQuery;

@Repository
public class MerchandiseStockStateDaoCacheImpl implements MerchandiseStockStateDao {

    @Autowired
    private MerchandiseStockStateDao merchandiseStockStateDaoMysqlImpl;

    @Autowired
    private MerchandiseStockStateDao merchandiseStockStateDaoRedisImpl;

    @Override
    public boolean add(MerchandiseStockState merchandiseStockState) {

        return merchandiseStockStateDaoMysqlImpl.add(merchandiseStockState);
    }

    @Override
    public MerchandiseStockState get(Long id) {

        return merchandiseStockStateDaoMysqlImpl.get(id);
        // return CacheLoader.get(merchandiseStockStateDaoRedisImpl, merchandiseStockStateDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseStockStateDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MerchandiseStockState merchandiseStockState) {

        return merchandiseStockStateDaoMysqlImpl.update(merchandiseStockState);
    }

    @Override
    public List<MerchandiseStockState> list(List<Long> idList) {

        return CacheLoader.list(merchandiseStockStateDaoRedisImpl, merchandiseStockStateDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MerchandiseStockState> map(Set<Long> idSet) {

        return CacheLoader.map(merchandiseStockStateDaoRedisImpl, merchandiseStockStateDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MerchandiseStockState> page(int start, int count) {

        return merchandiseStockStateDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MerchandiseStockState> page(MerchandiseStockStateQuery query, int start, int count) {

        return merchandiseStockStateDaoMysqlImpl.page(query, start, count);
    }

    public List<MerchandiseStockState> listAll() {

        return merchandiseStockStateDaoMysqlImpl.listAll();
    }

    @Override
    public List<MerchandiseStockState> listAll(Map<String, Object> map) {

        return merchandiseStockStateDaoMysqlImpl.listAll(map);
    }
}
