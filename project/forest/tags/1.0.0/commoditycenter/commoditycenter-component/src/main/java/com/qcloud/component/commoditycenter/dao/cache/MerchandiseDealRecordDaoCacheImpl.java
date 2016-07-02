package com.qcloud.component.commoditycenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.commoditycenter.dao.MerchandiseDealRecordDao;
import com.qcloud.component.commoditycenter.model.MerchandiseDealRecord;
import com.qcloud.component.commoditycenter.model.query.MerchandiseDealRecordQuery;

@Repository
public class MerchandiseDealRecordDaoCacheImpl implements MerchandiseDealRecordDao {

    @Autowired
    private MerchandiseDealRecordDao merchandiseDealRecordDaoMysqlImpl;

    // @Autowired
    // private MerchandiseDealRecordDao merchandiseDealRecordDaoRedisImpl;
    @Override
    public boolean add(MerchandiseDealRecord merchandiseDealRecord) {

        return merchandiseDealRecordDaoMysqlImpl.add(merchandiseDealRecord);
    }

    @Override
    public MerchandiseDealRecord get(Long id) {

        return merchandiseDealRecordDaoMysqlImpl.get(id);
        // return CacheLoader.get(merchandiseDealRecordDaoRedisImpl, merchandiseDealRecordDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseDealRecordDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MerchandiseDealRecord merchandiseDealRecord) {

        return merchandiseDealRecordDaoMysqlImpl.update(merchandiseDealRecord);
    }

    @Override
    public List<MerchandiseDealRecord> list(List<Long> idList) {

        return CacheLoader.list(merchandiseDealRecordDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MerchandiseDealRecord> map(Set<Long> idSet) {

        return CacheLoader.map(merchandiseDealRecordDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MerchandiseDealRecord> page(int start, int count) {

        return merchandiseDealRecordDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MerchandiseDealRecord> page(MerchandiseDealRecordQuery query, int start, int count) {

        return merchandiseDealRecordDaoMysqlImpl.page(query, start, count);
    }

    public List<MerchandiseDealRecord> listAll() {

        return merchandiseDealRecordDaoMysqlImpl.listAll();
    }

    @Override
    public List<MerchandiseDealRecord> listByMerchandise(Long merchandiseId, int start, int count) {
        return merchandiseDealRecordDaoMysqlImpl.listByMerchandise(merchandiseId, start, count);
    }
}
