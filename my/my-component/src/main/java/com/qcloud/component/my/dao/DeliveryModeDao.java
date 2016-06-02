package com.qcloud.component.my.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.my.model.DeliveryMode;
import com.qcloud.component.my.model.query.DeliveryModeQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface DeliveryModeDao extends ISimpleDao<DeliveryMode, Long> {

    public boolean add(DeliveryMode deliveryMode);

    public DeliveryMode get(Long id);

    public boolean delete(Long id);

    public boolean update(DeliveryMode deliveryMode);

    public List<DeliveryMode> list(List<Long> idList);

    public Map<Long, DeliveryMode> map(Set<Long> idSet);

    public Page<DeliveryMode> page(DeliveryModeQuery query, int start, int size);

    public List<DeliveryMode> listAll();

    DeliveryMode getByUser(Long userId);
}
