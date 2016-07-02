package com.qcloud.component.goods.dao;

import java.util.List;
import com.qcloud.component.goods.model.cache.MerchandiseItemCache;

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
