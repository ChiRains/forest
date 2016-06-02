package com.qcloud.component.sellercenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.sellercenter.model.StoreDeliveryTime;
import com.qcloud.component.sellercenter.model.query.StoreDeliveryTimeQuery;

public interface StoreDeliveryTimeDao extends ISimpleDao<StoreDeliveryTime, Long> {

    public boolean add(StoreDeliveryTime storeDeliveryTime);

    public StoreDeliveryTime get(Long id);

    StoreDeliveryTime getByStore(Long storeId);

    public boolean delete(Long id);

    public boolean update(StoreDeliveryTime storeDeliveryTime);

    public List<StoreDeliveryTime> list(List<Long> idList);

    public Map<Long, StoreDeliveryTime> map(Set<Long> idSet);

    public Page<StoreDeliveryTime> page(StoreDeliveryTimeQuery query, int start, int size);

    public List<StoreDeliveryTime> listAll();
}
