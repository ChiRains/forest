package com.qcloud.component.commoditycenter.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.commoditycenter.model.MerchandiseItem;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.OrderType;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.QueryItemType;
import com.qcloud.component.commoditycenter.model.query.MerchandiseItemQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

/**
 * 单商品条目:指定规格,指定价格,指定图片
 *
 * @author Zoro
 */
public interface MerchandiseItemDao extends ISimpleDao<MerchandiseItem, Long> {

    public boolean add(MerchandiseItem merchandiseItem);

    public MerchandiseItem get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseItem merchandiseItem);

    public boolean update(MerchandiseItem merchandiseItem, Date lastUpdateTime);

    public List<MerchandiseItem> list(List<Long> idList);

    public Map<Long, MerchandiseItem> map(Set<Long> idSet);

    public Page<MerchandiseItem> page(MerchandiseItemQuery query, int start, int size);

    public List<MerchandiseItem> listAll();

    public List<MerchandiseItem> listByMerchandise(Long merchandiseId);

    MerchandiseItem getByUnifiedMerchandise(Long unifiedMerchandiseId);

    public List<MerchandiseItem> list(Map where);

    public MerchandiseItem get(Map where);

    public Page<MerchandiseItem> page(Map where, int start, int size);

    Page<MerchandiseItem> query(MerchandiseItemQuery query, int start, int count);
    
    Page<MerchandiseItem> page4Price(String name, Long merchantId, Long merchantClassifyId, int start, int size, OrderType orderType, QueryItemType queryItemType);

    Page<MerchandiseItem> page4Date(String name, Long merchantId, Long merchantClassifyId, int start, int size, OrderType orderType, QueryItemType queryItemType);

    MerchandiseItem getBySpecifications(Long merchandiseId, Long merchandiseSpecificationsId);
    
    public Page<MerchandiseItem> list4Select4Admin(MerchandiseItemQuery query,int start ,int count);
    
    public List<MerchandiseItem> merchandiseListByMerchantId(long merchantId);
}
