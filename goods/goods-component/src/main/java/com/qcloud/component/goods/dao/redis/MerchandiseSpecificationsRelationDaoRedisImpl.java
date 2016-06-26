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
import com.qcloud.component.goods.dao.MerchandiseSpecificationsRelationDao;
import com.qcloud.component.goods.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsRelationQuery;

@Repository
public class MerchandiseSpecificationsRelationDaoRedisImpl implements MerchandiseSpecificationsRelationDao {

    // @Resource(name = "redis-commoditycenter")
    // private Redis redis;
    @Override
    public boolean add(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation) {

        throw new NotImplementedException();
    }

    @Override
    public MerchandiseSpecificationsRelation get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseSpecificationsRelation> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseSpecificationsRelation> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseSpecificationsRelation> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseSpecificationsRelation> page(MerchandiseSpecificationsRelationQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseSpecificationsRelation> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public boolean updateSpecByMap(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseSpecificationsRelation> listByMerchandiseId(Long merchandiseId) {

        throw new NotImplementedException();
    }

    @Override
    public boolean deleteByMerchandiseId(Long merchandiseIds) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseSpecificationsRelation> listByMap(Long merchandiseId, Long attributeId) {

        throw new NotImplementedException();
    }
}
