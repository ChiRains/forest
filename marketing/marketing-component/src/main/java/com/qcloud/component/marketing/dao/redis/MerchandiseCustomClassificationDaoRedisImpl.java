package com.qcloud.component.marketing.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.marketing.dao.MerchandiseCustomClassificationDao;
import com.qcloud.component.marketing.model.MerchandiseCustomClassification;
import com.qcloud.component.marketing.model.query.MerchandiseCustomClassificationQuery;

@Repository
public class MerchandiseCustomClassificationDaoRedisImpl implements MerchandiseCustomClassificationDao {

    // @Resource(name = "redis-marketing")
    // private Redis redis;
    @Override
    public boolean add(MerchandiseCustomClassification merchandiseCustomClassification) {

        throw new NotImplementedException();
    }

    @Override
    public MerchandiseCustomClassification get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MerchandiseCustomClassification merchandiseCustomClassification) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseCustomClassification> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseCustomClassification> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseCustomClassification> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseCustomClassification> page(MerchandiseCustomClassificationQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseCustomClassification> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseCustomClassification> listAll(Map<String, Object> map) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Map<String, Object> map) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseCustomClassification> list(long merchantId, long customClassifyId, int start, int size) {

        throw new NotImplementedException();
    }
}
