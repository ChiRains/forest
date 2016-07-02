package com.qcloud.component.commoditycenter.dao;

import com.qcloud.component.commoditycenter.model.cache.UnifiedMerchandiseCache;

/**
 * 统一商品缓存
 * @author Zoro
 *
 */
public interface UnifiedMerchandiseCacheDao {

    boolean add(UnifiedMerchandiseCache unifiedMerchandiseCache);

    boolean remove(Long id);

    UnifiedMerchandiseCache get(Long id);
}
