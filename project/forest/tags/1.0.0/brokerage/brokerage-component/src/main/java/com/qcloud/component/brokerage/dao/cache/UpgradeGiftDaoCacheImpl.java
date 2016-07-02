package com.qcloud.component.brokerage.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.dao.UpgradeGiftDao;
import com.qcloud.component.brokerage.model.UpgradeGift;
import com.qcloud.component.brokerage.model.query.UpgradeGiftQuery;

@Repository
public class UpgradeGiftDaoCacheImpl implements UpgradeGiftDao {

    @Autowired
    private UpgradeGiftDao upgradeGiftDaoMysqlImpl;

    @Autowired
    private UpgradeGiftDao upgradeGiftDaoRedisImpl;

    @Override
    public boolean add(UpgradeGift upgradeGift) {

        return upgradeGiftDaoMysqlImpl.add(upgradeGift);
    }

    @Override
    public UpgradeGift get(Long id) {

        return upgradeGiftDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return upgradeGiftDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(UpgradeGift upgradeGift) {

        return upgradeGiftDaoMysqlImpl.update(upgradeGift);
    }

    @Override
    public List<UpgradeGift> list(List<Long> idList) {

        return CacheLoader.list(upgradeGiftDaoRedisImpl, upgradeGiftDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, UpgradeGift> map(Set<Long> idSet) {

        return CacheLoader.map(upgradeGiftDaoRedisImpl, upgradeGiftDaoMysqlImpl, idSet);
    }

    @Override
    public Page<UpgradeGift> page(int start, int count) {

        return upgradeGiftDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<UpgradeGift> page(UpgradeGiftQuery query, int start, int count) {

        return upgradeGiftDaoMysqlImpl.page(query, start, count);
    }

    public List<UpgradeGift> listAll() {

        return upgradeGiftDaoMysqlImpl.listAll();
    }

    @Override
    public List<UpgradeGift> listCanGift(long gradeId) {

        return upgradeGiftDaoMysqlImpl.listCanGift(gradeId);
    }
}
