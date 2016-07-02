package com.qcloud.component.goods.dao.redis;

import com.qcloud.component.goods.dao.MerchandiseSpecificationsDao;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsQuery;
import com.qcloud.pirates.data.Page;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class MerchandiseSpecificationsDaoRedisImpl implements MerchandiseSpecificationsDao {

    // @Resource(name = "redis-commoditycenter")
    // private Redis redis;
    @Override
    public boolean add(MerchandiseSpecifications merchandiseSpecifications) {

        throw new NotImplementedException();
    }

    @Override
    public MerchandiseSpecifications get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MerchandiseSpecifications merchandiseSpecifications) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseSpecifications> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseSpecifications> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseSpecifications> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseSpecifications> page(MerchandiseSpecificationsQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseSpecifications> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseSpecifications> listByMerchandise(Long merchandiseId) {

        throw new NotImplementedException();
    }

    @Override
    public MerchandiseSpecifications get(HashMap where) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseSpecifications> list(HashMap where) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseSpecifications> page(HashMap where, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchandiseSpecifications> listByUnifiedMerchandise(Long unifiedMerchandiseId) {
        throw new NotImplementedException();
    }
}
