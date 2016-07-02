package com.qcloud.component.sellercenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.sellercenter.model.StoreDeliveryRange;
import com.qcloud.component.sellercenter.model.query.StoreDeliveryRangeQuery;

public interface StoreDeliveryRangeDao extends ISimpleDao<StoreDeliveryRange, Long> {

    public boolean add(StoreDeliveryRange storeDeliveryRange);

    public StoreDeliveryRange get(Long id);

    StoreDeliveryRange getByStore(Long storeId);

    public boolean delete(Long id);

    public boolean update(StoreDeliveryRange storeDeliveryRange);

    public List<StoreDeliveryRange> list(List<Long> idList);

    public Map<Long, StoreDeliveryRange> map(Set<Long> idSet);

    public Page<StoreDeliveryRange> page(StoreDeliveryRangeQuery query, int start, int size);

    public List<StoreDeliveryRange> listAll();
}
