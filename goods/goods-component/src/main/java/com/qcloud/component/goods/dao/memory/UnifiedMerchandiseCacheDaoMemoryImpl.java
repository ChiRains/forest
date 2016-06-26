package com.qcloud.component.goods.dao.memory;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.qcloud.component.goods.dao.UnifiedMerchandiseCacheDao;
import com.qcloud.component.goods.model.cache.UnifiedMerchandiseCache;
@Repository
public class UnifiedMerchandiseCacheDaoMemoryImpl implements UnifiedMerchandiseCacheDao {
    // 放 65536个
    private Map<Long, UnifiedMerchandiseCache> map = Collections.synchronizedMap(new HashMap<Long, UnifiedMerchandiseCache>());

    @Override
    public boolean add(UnifiedMerchandiseCache unifiedMerchandiseCache) {
//      缓存应放到redis
//        if (map.size() >= 65536) {
//            return false;
//        }
//        map.put(unifiedMerchandiseCache.getId(), unifiedMerchandiseCache);
        return true;
    }

    @Override
    public boolean remove(Long id) {
        map.remove(id);
        return true;
    }

    @Override
    public UnifiedMerchandiseCache get(Long id) {
        return map.get(id);
    }
}
