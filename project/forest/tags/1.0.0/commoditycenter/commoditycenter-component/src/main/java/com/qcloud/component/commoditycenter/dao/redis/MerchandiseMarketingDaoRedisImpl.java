package com.qcloud.component.commoditycenter.dao.redis;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.commoditycenter.dao.MerchandiseMarketingDao;
import com.qcloud.component.commoditycenter.model.MerchandiseMarketing;
import com.qcloud.component.commoditycenter.model.query.MerchandiseMarketingQuery;

@Repository
public class MerchandiseMarketingDaoRedisImpl implements MerchandiseMarketingDao {

    // @Resource(name = "redis-commoditycenter")
    // private Redis redis;
    @Override
    public boolean add(MerchandiseMarketing merchandiseMarketing) {

        throw new NotImplementedException();
    }

    @Override
    public MerchandiseMarketing get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MerchandiseMarketing merchandiseMarketing) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseMarketing> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseMarketing> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseMarketing> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseMarketing> page(MerchandiseMarketingQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseMarketing> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MerchandiseMarketing merchandiseMarketing, Date lastUpdateTime) {

        throw new NotImplementedException();
    }

    @Override
    public MerchandiseMarketing getByUnifiedMerchandise(Long unifiedMerchandiseId) {

        throw new NotImplementedException();
    }

    @Override
    public boolean setEnable(Long id, int enable) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseMarketing> list(int sence, double discountRange, int start, int count) {

        throw new NotImplementedException();
    }
}
