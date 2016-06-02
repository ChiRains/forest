package com.qcloud.component.commoditycenter.service;
import java.util.List;
import com.qcloud.component.commoditycenter.model.cache.MerchandiseItemCache;
public interface MerchandiseItemCacheService {
    
    boolean remove(Long unifiedMerchandiseId);

    List<MerchandiseItemCache> list(Long unifiedMerchandiseId);
}
