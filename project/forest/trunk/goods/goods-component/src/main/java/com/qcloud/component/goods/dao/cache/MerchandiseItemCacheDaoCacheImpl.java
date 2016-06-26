package com.qcloud.component.goods.dao.cache;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.goods.dao.MerchandiseItemCacheDao;
import com.qcloud.component.goods.model.cache.MerchandiseItemCache;
@Repository
public class MerchandiseItemCacheDaoCacheImpl implements MerchandiseItemCacheDao {
    @Autowired
    private MerchandiseItemCacheDao merchandiseItemCacheDaoMemoryImpl;

    @Override
    public boolean add(MerchandiseItemCache merchandiseItemCache) {
        return merchandiseItemCacheDaoMemoryImpl.add(merchandiseItemCache);
    }

    @Override
    public boolean remove(Long id) {
        return merchandiseItemCacheDaoMemoryImpl.remove(id);
    }

    @Override
    public boolean removeByUnifiedMerchandise(Long unifiedMerchandiseId) {
        return merchandiseItemCacheDaoMemoryImpl.removeByUnifiedMerchandise(unifiedMerchandiseId);
    }

    @Override
    public boolean addList(List<MerchandiseItemCache> list) {
        for (MerchandiseItemCache merchandiseItemCache : list) {
            add(merchandiseItemCache);
        }
        return merchandiseItemCacheDaoMemoryImpl.addList(list);
    }

    @Override
    public MerchandiseItemCache get(Long id) {
        return merchandiseItemCacheDaoMemoryImpl.get(id);
    }

    @Override
    public List<Long> listMerchandiseItemIds(Long unifiedMerchandiseId) {
        return merchandiseItemCacheDaoMemoryImpl.listMerchandiseItemIds(unifiedMerchandiseId);
    }
}
