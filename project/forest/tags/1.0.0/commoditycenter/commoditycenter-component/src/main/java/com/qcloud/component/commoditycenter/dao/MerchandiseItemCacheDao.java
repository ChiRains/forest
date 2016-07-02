package com.qcloud.component.commoditycenter.dao;

import java.util.List;
import com.qcloud.component.commoditycenter.model.cache.MerchandiseItemCache;

/**
 * 单商品条目缓存
 * 
 * @author Zoro
 *
 */
public interface MerchandiseItemCacheDao {

    boolean add(MerchandiseItemCache merchandiseItemCache);

    boolean remove(Long id);

    boolean removeByUnifiedMerchandise(Long unifiedMerchandiseId);

    boolean addList(List<MerchandiseItemCache> list);

    MerchandiseItemCache get(Long id);

    List<Long> listMerchandiseItemIds(Long unifiedMerchandiseId);
}
