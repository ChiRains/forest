package com.qcloud.component.marketing.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.marketing.dao.MerchandiseCustomClassificationDao;
import com.qcloud.component.marketing.model.MerchandiseCustomClassification;
import com.qcloud.component.marketing.model.query.MerchandiseCustomClassificationQuery;

@Repository
public class MerchandiseCustomClassificationDaoCacheImpl implements MerchandiseCustomClassificationDao {

    @Autowired
    private MerchandiseCustomClassificationDao merchandiseCustomClassificationDaoMysqlImpl;

    // @Autowired
    // private MerchandiseCustomClassificationDao merchandiseCustomClassificationDaoRedisImpl;
    @Override
    public boolean add(MerchandiseCustomClassification merchandiseCustomClassification) {

        return merchandiseCustomClassificationDaoMysqlImpl.add(merchandiseCustomClassification);
    }

    @Override
    public MerchandiseCustomClassification get(Long id) {

        return merchandiseCustomClassificationDaoMysqlImpl.get(id);
        // return CacheLoader.get(merchandiseCustomClassificationDaoRedisImpl, merchandiseCustomClassificationDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseCustomClassificationDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MerchandiseCustomClassification merchandiseCustomClassification) {

        return merchandiseCustomClassificationDaoMysqlImpl.update(merchandiseCustomClassification);
    }

    @Override
    public List<MerchandiseCustomClassification> list(List<Long> idList) {

        return CacheLoader.list(merchandiseCustomClassificationDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MerchandiseCustomClassification> map(Set<Long> idSet) {

        return CacheLoader.map(merchandiseCustomClassificationDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MerchandiseCustomClassification> page(int start, int count) {

        return merchandiseCustomClassificationDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MerchandiseCustomClassification> page(MerchandiseCustomClassificationQuery query, int start, int count) {

        return merchandiseCustomClassificationDaoMysqlImpl.page(query, start, count);
    }

    public List<MerchandiseCustomClassification> listAll() {

        return merchandiseCustomClassificationDaoMysqlImpl.listAll();
    }

    @Override
    public List<MerchandiseCustomClassification> listAll(Map<String, Object> map) {

        return merchandiseCustomClassificationDaoMysqlImpl.listAll(map);
    }

    @Override
    public boolean delete(Map<String, Object> map) {

        return merchandiseCustomClassificationDaoMysqlImpl.delete(map);
    }

    @Override
    public List<MerchandiseCustomClassification> list(long merchantId, long customClassifyId, int start, int size) {

        return merchandiseCustomClassificationDaoMysqlImpl.list(merchantId, customClassifyId, start, size);
    }
}
