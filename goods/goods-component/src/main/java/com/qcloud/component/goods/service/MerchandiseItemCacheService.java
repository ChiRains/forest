package com.qcloud.component.goods.service;
import java.util.List;
import com.qcloud.component.goods.model.cache.MerchandiseItemCache;
public interface MerchandiseItemCacheService {
    
    boolean remove(Long unifiedMerchandiseId);

    List<MerchandiseItemCache> list(Long unifiedMerchandiseId);
}
