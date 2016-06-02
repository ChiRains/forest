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
import com.qcloud.component.sellercenter.dao.StoreDao;
import com.qcloud.component.sellercenter.model.Store;
import com.qcloud.component.sellercenter.model.query.StoreQuery;

@Repository
public class StoreDaoRedisImpl implements StoreDao {

    // @Resource(name = "redis-sellercenter")
    // private Redis redis;
    @Override
    public boolean add(Store store) {

        throw new NotImplementedException();
    }

    @Override
    public Store get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(Store store) {

        throw new NotImplementedException();
    }

    @Override
    public List<Store> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Store> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Store> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Store> page(StoreQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<Store> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<Store> listChildren(Long parentId) {

        throw new NotImplementedException();
    }

    @Override
    public List<Store> listByMerchant(Long merchantId) {

        throw new NotImplementedException();
    }

    @Override
    public List<Store> listByAddress(String province, String city, String district) {

        throw new NotImplementedException();
    }

    @Override
    public List<Store> listByParentId(Long parentId) {

        throw new NotImplementedException();
    }
}
