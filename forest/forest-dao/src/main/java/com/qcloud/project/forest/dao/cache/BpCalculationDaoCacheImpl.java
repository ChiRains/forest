package com.qcloud.project.forest.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.BpCalculationDao;
import com.qcloud.project.forest.model.BpCalculation;
import com.qcloud.project.forest.model.query.BpCalculationQuery;

@Repository
public class BpCalculationDaoCacheImpl implements BpCalculationDao {

    @Autowired
    private BpCalculationDao bpCalculationDaoMysqlImpl;

    @Autowired
    private BpCalculationDao bpCalculationDaoRedisImpl;

    @Override
    public boolean add(BpCalculation bpCalculation) {

        return bpCalculationDaoMysqlImpl.add(bpCalculation);
    }

    @Override
    public BpCalculation get(Long id) {

        return bpCalculationDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return bpCalculationDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(BpCalculation bpCalculation) {

        return bpCalculationDaoMysqlImpl.update(bpCalculation);
    }

    @Override
    public List<BpCalculation> list(List<Long> idList) {

        return CacheLoader.list(bpCalculationDaoRedisImpl, bpCalculationDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, BpCalculation> map(Set<Long> idSet) {

        return CacheLoader.map(bpCalculationDaoRedisImpl, bpCalculationDaoMysqlImpl, idSet);
    }

    @Override
    public Page<BpCalculation> page(int start, int count) {

        return bpCalculationDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<BpCalculation> page(BpCalculationQuery query, int start, int count) {

        return bpCalculationDaoMysqlImpl.page(query, start, count);
    }

    public List<BpCalculation> listAll() {

        return bpCalculationDaoMysqlImpl.listAll();
    }

    @Override
    public BpCalculation getByRange(int dbp, int sbp) {

        return bpCalculationDaoMysqlImpl.getByRange(dbp, sbp);
    }
}
