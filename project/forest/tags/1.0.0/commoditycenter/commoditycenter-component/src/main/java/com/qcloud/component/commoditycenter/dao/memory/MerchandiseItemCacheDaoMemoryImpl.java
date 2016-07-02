package com.qcloud.component.commoditycenter.dao.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import com.qcloud.component.commoditycenter.dao.MerchandiseItemCacheDao;
import com.qcloud.component.commoditycenter.model.cache.MerchandiseItemCache;

@Repository
public class MerchandiseItemCacheDaoMemoryImpl implements MerchandiseItemCacheDao {

    // 放 65536个
    private Map<Long, MerchandiseItemCache> merchandiseItemMap = new HashMap<Long, MerchandiseItemCache>();

    // 放 65536个
    private Map<Long, Set<Long>>            keysMap            = new HashMap<Long, Set<Long>>();

    @Override
    public boolean add(MerchandiseItemCache merchandiseItemCache) {
//        缓存应放到redis
//        if (merchandiseItemMap.size() >= 65536) {
//            return false;
//        }
//        Set<Long> set = keysMap.get(merchandiseItemCache.getUnifiedMerchandiseId());
//        if (set == null) {
//            set = new HashSet<Long>();
//            keysMap.put(merchandiseItemCache.getUnifiedMerchandiseId(), set);
//        }
//        set.add(merchandiseItemCache.getId());
//        merchandiseItemMap.put(merchandiseItemCache.getId(), merchandiseItemCache);
        return true;
    }

    @Override
    public boolean remove(Long id) {

        merchandiseItemMap.remove(id);
        return true;
    }

    @Override
    public boolean removeByUnifiedMerchandise(Long unifiedMerchandiseId) {

        List<Long> list = listMerchandiseItemIds(unifiedMerchandiseId);
        if (list != null) {
            for (Long key : list) {
                remove(key);
            }
        }
        keysMap.remove(unifiedMerchandiseId);
        return true;
    }

    @Override
    public boolean addList(List<MerchandiseItemCache> list) {

        if (merchandiseItemMap.size() + list.size() >= 65536) {
            return false;
        }
        for (MerchandiseItemCache merchandiseItemCache : list) {
            add(merchandiseItemCache);
        }
        return true;
    }

    @Override
    public MerchandiseItemCache get(Long id) {

        return merchandiseItemMap.get(id);
    }

    @Override
    public List<Long> listMerchandiseItemIds(Long unifiedMerchandiseId) {

        Set<Long> keys = keysMap.get(unifiedMerchandiseId);
        if (CollectionUtils.isEmpty(keys)) {
            return new ArrayList<Long>(0);
        }
        return new ArrayList<Long>(keys);
    }
}
