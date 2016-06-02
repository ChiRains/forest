package com.qcloud.component.commoditycenter.service;

import com.qcloud.component.commoditycenter.model.cache.UnifiedMerchandiseCache;

public interface UnifiedMerchandiseCacheService {
    
    UnifiedMerchandiseCache get(Long unifiedMerchandiseId);
    
    boolean remove(Long unifiedMerchandiseId);
}
