package com.qcloud.component.brokerage.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.dao.DistributionBrokerageDao;
import com.qcloud.component.brokerage.model.DistributionBrokerage;
import com.qcloud.component.brokerage.model.query.DistributionBrokerageQuery;

@Repository
public class DistributionBrokerageDaoCacheImpl implements DistributionBrokerageDao {

    @Autowired
    private DistributionBrokerageDao distributionBrokerageDaoMysqlImpl;

    // @Autowired
    // private DistributionBrokerageDao distributionBrokerageDaoRedisImpl;
    @Override
    public boolean add(DistributionBrokerage distributionBrokerage) {

        return distributionBrokerageDaoMysqlImpl.add(distributionBrokerage);
    }

    @Override
    public DistributionBrokerage get(Long id) {

        return distributionBrokerageDaoMysqlImpl.get(id);
        // return CacheLoader.get(distributionBrokerageDaoRedisImpl, distributionBrokerageDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return distributionBrokerageDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(DistributionBrokerage distributionBrokerage) {

        return distributionBrokerageDaoMysqlImpl.update(distributionBrokerage);
    }

    @Override
    public List<DistributionBrokerage> list(List<Long> idList) {

        return CacheLoader.list(distributionBrokerageDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, DistributionBrokerage> map(Set<Long> idSet) {

        return CacheLoader.map(distributionBrokerageDaoMysqlImpl, idSet);
    }

    @Override
    public Page<DistributionBrokerage> page(int start, int count) {

        return distributionBrokerageDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<DistributionBrokerage> page(DistributionBrokerageQuery query, int start, int count) {

        return distributionBrokerageDaoMysqlImpl.page(query, start, count);
    }

    public List<DistributionBrokerage> listAll() {

        return distributionBrokerageDaoMysqlImpl.listAll();
    }
}
