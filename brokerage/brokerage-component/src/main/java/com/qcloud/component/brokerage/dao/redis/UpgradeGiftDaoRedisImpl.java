package com.qcloud.component.brokerage.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.brokerage.dao.UpgradeGiftDao;
import com.qcloud.component.brokerage.model.UpgradeGift;
import com.qcloud.component.brokerage.model.query.UpgradeGiftQuery;

@Repository
public class UpgradeGiftDaoRedisImpl implements UpgradeGiftDao {

    // @Resource(name = "redis-brokerage")
    // private Redis redis;
    @Override
    public boolean add(UpgradeGift upgradeGift) {

        throw new NotImplementedException();
    }

    @Override
    public UpgradeGift get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(UpgradeGift upgradeGift) {

        throw new NotImplementedException();
    }

    @Override
    public List<UpgradeGift> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, UpgradeGift> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<UpgradeGift> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<UpgradeGift> page(UpgradeGiftQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<UpgradeGift> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<UpgradeGift> listCanGift(long gradeId) {

        throw new NotImplementedException();
    }
}
