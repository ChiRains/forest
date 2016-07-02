package com.qcloud.component.goods.dao.cache;

import com.qcloud.component.goods.dao.MerchandiseSpecificationsDao;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class MerchandiseSpecificationsDaoCacheImpl implements MerchandiseSpecificationsDao {

    @Autowired
    private MerchandiseSpecificationsDao merchandiseSpecificationsDaoMysqlImpl;

    // @Autowired
    // private MerchandiseSpecificationsDao merchandiseSpecificationsDaoRedisImpl;
    @Override
    public boolean add(MerchandiseSpecifications merchandiseSpecifications) {

        return merchandiseSpecificationsDaoMysqlImpl.add(merchandiseSpecifications);
    }

    @Override
    public MerchandiseSpecifications get(Long id) {

        return merchandiseSpecificationsDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseSpecificationsDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MerchandiseSpecifications merchandiseSpecifications) {

        return merchandiseSpecificationsDaoMysqlImpl.update(merchandiseSpecifications);
    }

    @Override
    public List<MerchandiseSpecifications> list(List<Long> idList) {

        return CacheLoader.list(merchandiseSpecificationsDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MerchandiseSpecifications> map(Set<Long> idSet) {

        return CacheLoader.map(merchandiseSpecificationsDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MerchandiseSpecifications> page(int start, int count) {

        return merchandiseSpecificationsDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MerchandiseSpecifications> page(MerchandiseSpecificationsQuery query, int start, int count) {

        return merchandiseSpecificationsDaoMysqlImpl.page(query, start, count);
    }

    public List<MerchandiseSpecifications> listAll() {

        return merchandiseSpecificationsDaoMysqlImpl.listAll();
    }

    @Override
    public List<MerchandiseSpecifications> listByMerchandise(Long merchandiseId) {

        return merchandiseSpecificationsDaoMysqlImpl.listByMerchandise(merchandiseId);
    }

    @Override
    public MerchandiseSpecifications get(HashMap where) {

        return merchandiseSpecificationsDaoMysqlImpl.get(where);
    }

    @Override
    public List<MerchandiseSpecifications> list(HashMap where) {

        return merchandiseSpecificationsDaoMysqlImpl.list(where);
    }

    @Override
    public Page<MerchandiseSpecifications> page(HashMap where, int start, int size) {

        return merchandiseSpecificationsDaoMysqlImpl.page(where, start, size);
    }
}
