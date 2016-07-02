package com.qcloud.component.commoditycenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.commoditycenter.dao.MerchandiseImageDao;
import com.qcloud.component.commoditycenter.model.MerchandiseImage;
import com.qcloud.component.commoditycenter.model.query.MerchandiseImageQuery;

@Repository
public class MerchandiseImageDaoCacheImpl implements MerchandiseImageDao {

    @Autowired
    private MerchandiseImageDao merchandiseImageDaoMysqlImpl;

    // @Autowired
    // private MerchandiseImageDao merchandiseImageDaoRedisImpl;
    @Override
    public boolean add(MerchandiseImage merchandiseImage) {

        return merchandiseImageDaoMysqlImpl.add(merchandiseImage);
    }

    @Override
    public MerchandiseImage get(Long id) {

        return merchandiseImageDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseImageDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MerchandiseImage merchandiseImage) {

        return merchandiseImageDaoMysqlImpl.update(merchandiseImage);
    }

    @Override
    public List<MerchandiseImage> list(List<Long> idList) {

        return CacheLoader.list(merchandiseImageDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MerchandiseImage> map(Set<Long> idSet) {

        return CacheLoader.map(merchandiseImageDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MerchandiseImage> page(int start, int count) {

        return merchandiseImageDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MerchandiseImage> page(MerchandiseImageQuery query, int start, int count) {

        return merchandiseImageDaoMysqlImpl.page(query, start, count);
    }

    public List<MerchandiseImage> listAll() {

        return merchandiseImageDaoMysqlImpl.listAll();
    }

    @Override
    public List<MerchandiseImage> listByMerchandise(Long merchandiseId) {

        return merchandiseImageDaoMysqlImpl.listByMerchandise(merchandiseId);
    }

    @Override
    public List<MerchandiseImage> listByMerchandiseAndAttribute(Long merchandiseId, Long attributeId, String value) {

        return merchandiseImageDaoMysqlImpl.listByMerchandiseAndAttribute(merchandiseId, attributeId, value);
    }
}
