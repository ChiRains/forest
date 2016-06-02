package com.qcloud.component.sellercenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.sellercenter.dao.MerchantWealthDao;
import com.qcloud.component.sellercenter.model.MerchantWealth;
import com.qcloud.component.sellercenter.model.query.MerchantWealthQuery;

@Repository
public class MerchantWealthDaoRedisImpl implements MerchantWealthDao {

    // @Resource(name = "redis-sellercenter")
    // private Redis redis;
    @Override
    public boolean add(MerchantWealth merchantWealth) {

        throw new NotImplementedException();
    }

    @Override
    public MerchantWealth get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MerchantWealth merchantWealth) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchantWealth> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchantWealth> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchantWealth> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchantWealth> page(MerchantWealthQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchantWealth> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public MerchantWealth getByMerchant(long merchantId) {

        throw new NotImplementedException();
    }

    @Override
    public boolean updateLock(MerchantWealth merchantWealth) {

        throw new NotImplementedException();
    }
}
