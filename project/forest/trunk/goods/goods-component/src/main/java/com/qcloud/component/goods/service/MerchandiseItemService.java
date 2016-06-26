package com.qcloud.component.goods.service;

import java.util.List;
import java.util.Map;
import com.qcloud.component.goods.model.MerchandiseItem;
import com.qcloud.component.goods.model.key.TypeEnum.OrderType;
import com.qcloud.component.goods.model.key.TypeEnum.QueryItemType;
import com.qcloud.component.goods.model.query.MerchandiseItemQuery;
import com.qcloud.pirates.data.Page;

public interface MerchandiseItemService {

    public boolean add(MerchandiseItem merchandiseItem);

    public MerchandiseItem get(Long id);

    public MerchandiseItem getBySpecifications(Long merchandiseId, Long merchandiseSpecificationsId);

    MerchandiseItem getByUnifiedMerchandise(Long unifiedMerchandiseId);

    public boolean delete(Long id);

    public boolean update(MerchandiseItem merchandiseItem);

    public Page<MerchandiseItem> page(MerchandiseItemQuery query, int start, int count);

    public List<MerchandiseItem> listByMerchandise(Long merchandiseId);

    public List<MerchandiseItem> listAll();

    public MerchandiseItem get(Map where);

    public List<MerchandiseItem> list(Map where);

    public Page<MerchandiseItem> page(Map where, int start, int size);

    // 按价格排序
    public Page<MerchandiseItem> page4Price(String name, Long merchantId, Long merchantClassifyId, int start, int size, OrderType orderType, QueryItemType queryItemType);

    public Page<MerchandiseItem> page4Date(String name, Long merchantId, Long merchantClassifyId, int start, int size, OrderType orderType, QueryItemType queryItemType);

    // 前端接口
    Page<MerchandiseItem> query(MerchandiseItemQuery query, int start, int count);

    boolean lockStock(long id, int stock);

    boolean updateSalesVolume(long id, int number);

    boolean increaseGoodEvaluation(long merchandiseId);

    boolean increaseMiddleEvaluation(long merchandiseId);

    boolean increaseLowEvaluation(long merchandiseId);

    public Page<MerchandiseItem> list4Select4Admin(MerchandiseItemQuery query, int start, int count);

    public List<MerchandiseItem> merchandiseListByMerchantId(long merchantId);

    public boolean importMerchandiseItem(List<MerchandiseItem> list);
}
