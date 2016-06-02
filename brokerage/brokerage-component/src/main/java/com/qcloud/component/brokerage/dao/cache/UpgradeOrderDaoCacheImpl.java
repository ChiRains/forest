package com.qcloud.component.brokerage.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.dao.UpgradeOrderDao;
import com.qcloud.component.brokerage.model.UpgradeOrder;
import com.qcloud.component.brokerage.model.query.UpgradeOrderQuery;

@Repository
public class UpgradeOrderDaoCacheImpl implements UpgradeOrderDao {

    @Autowired
    private UpgradeOrderDao upgradeOrderDaoMysqlImpl;

    // @Autowired
    // private UpgradeOrderDao upgradeOrderDaoRedisImpl;
    @Override
    public boolean add(UpgradeOrder upgradeOrder) {

        return upgradeOrderDaoMysqlImpl.add(upgradeOrder);
    }

    @Override
    public UpgradeOrder get(Long id) {

        return upgradeOrderDaoMysqlImpl.get(id);
        // return CacheLoader.get(upgradeOrderDaoRedisImpl, upgradeOrderDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return upgradeOrderDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(UpgradeOrder upgradeOrder) {

        return upgradeOrderDaoMysqlImpl.update(upgradeOrder);
    }

    @Override
    public List<UpgradeOrder> list(List<Long> idList) {

        return CacheLoader.list(upgradeOrderDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, UpgradeOrder> map(Set<Long> idSet) {

        return CacheLoader.map(upgradeOrderDaoMysqlImpl, idSet);
    }

    @Override
    public Page<UpgradeOrder> page(int start, int count) {

        return upgradeOrderDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<UpgradeOrder> page(UpgradeOrderQuery query, int start, int count) {

        return upgradeOrderDaoMysqlImpl.page(query, start, count);
    }

    public List<UpgradeOrder> listAll() {

        return upgradeOrderDaoMysqlImpl.listAll();
    }
}
