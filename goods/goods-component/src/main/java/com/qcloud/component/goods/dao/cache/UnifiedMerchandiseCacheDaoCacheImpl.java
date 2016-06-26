package com.qcloud.component.goods.dao.cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.goods.dao.UnifiedMerchandiseCacheDao;
import com.qcloud.component.goods.model.cache.UnifiedMerchandiseCache;
@Repository
public class UnifiedMerchandiseCacheDaoCacheImpl implements UnifiedMerchandiseCacheDao {
    @Autowired
    private UnifiedMerchandiseCacheDao unifiedMerchandiseCacheDaoMemoryImpl;

    @Override
    public boolean add(UnifiedMerchandiseCache unifiedMerchandiseCache) {
        boolean result = unifiedMerchandiseCacheDaoMemoryImpl.add(unifiedMerchandiseCache);
        if (result) {
            return true;
        }
        // 干掉一半不常用的.
        return false;
    }

    @Override
    public boolean remove(Long id) {
        return unifiedMerchandiseCacheDaoMemoryImpl.remove(id);
    }

    @Override
    public UnifiedMerchandiseCache get(Long id) {
        return unifiedMerchandiseCacheDaoMemoryImpl.get(id);
    }
}
