package com.qcloud.component.goods.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.goods.dao.CombinationMerchandiseItemDao;
import com.qcloud.component.goods.model.CombinationMerchandiseItem;
import com.qcloud.component.goods.model.query.CombinationMerchandiseItemQuery;

@Repository
public class CombinationMerchandiseItemDaoRedisImpl implements CombinationMerchandiseItemDao {

    // @Resource(name = "redis-commoditycenter")
    // private Redis redis;
    @Override
    public boolean add(CombinationMerchandiseItem combinationMerchandiseItem) {

        throw new NotImplementedException();
    }

    @Override
    public CombinationMerchandiseItem get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(CombinationMerchandiseItem combinationMerchandiseItem) {

        throw new NotImplementedException();
    }

    @Override
    public List<CombinationMerchandiseItem> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, CombinationMerchandiseItem> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<CombinationMerchandiseItem> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<CombinationMerchandiseItem> page(CombinationMerchandiseItemQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<CombinationMerchandiseItem> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<CombinationMerchandiseItem> listByCombinationMerchandise(long combinationMerchandiseId) {

        throw new NotImplementedException();
    }

    @Override
    public List<CombinationMerchandiseItem> list(Map where) {
        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Map where) {
        throw new NotImplementedException();
    }

    @Override
    public List<CombinationMerchandiseItem> listByMerchandiseItem(Long merchandiseItemId, int start, int count) {
        throw new NotImplementedException();
    }

    @Override
    public int countByMerchandiseItem(Long merchandiseItemId) {
        throw new NotImplementedException();
    }
}
