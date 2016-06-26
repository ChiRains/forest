package com.qcloud.component.goods.dao;

import com.qcloud.component.goods.model.cache.UnifiedMerchandiseCache;

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
