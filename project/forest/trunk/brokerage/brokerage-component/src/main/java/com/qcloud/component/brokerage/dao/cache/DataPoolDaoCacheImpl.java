package com.qcloud.component.brokerage.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.dao.DataPoolDao;
import com.qcloud.component.brokerage.model.DataPool;
import com.qcloud.component.brokerage.model.FormulaSqlResult;
import com.qcloud.component.brokerage.model.query.DataPoolQuery;

@Repository
public class DataPoolDaoCacheImpl implements DataPoolDao {

    @Autowired
    private DataPoolDao dataPoolDaoMysqlImpl;

    // @Autowired
    // private DataPoolDao dataPoolDaoRedisImpl;
    @Override
    public boolean add(DataPool dataPool) {

        return dataPoolDaoMysqlImpl.add(dataPool);
    }

    @Override
    public DataPool get(Long id) {

        return dataPoolDaoMysqlImpl.get(id);
        // return CacheLoader.get(dataPoolDaoRedisImpl, dataPoolDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return dataPoolDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(DataPool dataPool) {

        return dataPoolDaoMysqlImpl.update(dataPool);
    }

    @Override
    public List<DataPool> list(List<Long> idList) {

        return CacheLoader.list(dataPoolDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, DataPool> map(Set<Long> idSet) {

        return CacheLoader.map(dataPoolDaoMysqlImpl, idSet);
    }

    @Override
    public Page<DataPool> page(int start, int count) {

        return dataPoolDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<DataPool> page(DataPoolQuery query, int start, int count) {

        return dataPoolDaoMysqlImpl.page(query, start, count);
    }

    public List<DataPool> listAll() {

        return dataPoolDaoMysqlImpl.listAll();
    }

    @Override
    public List<FormulaSqlResult> query(String sql) {

        return dataPoolDaoMysqlImpl.query(sql);
    }
}
