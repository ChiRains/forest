package com.qcloud.component.my.service;

import java.util.List;
import com.qcloud.component.my.model.DeliveryMode;
import com.qcloud.component.my.model.query.DeliveryModeQuery;
import com.qcloud.pirates.data.Page;

public interface DeliveryModeService {

    public boolean add(DeliveryMode deliveryMode);

    public DeliveryMode get(Long id);

    DeliveryMode getByUser(Long userId);

    public boolean delete(Long id);

    public boolean update(DeliveryMode deliveryMode);

    public Page<DeliveryMode> page(DeliveryModeQuery query, int start, int count);

    public List<DeliveryMode> listAll();
}
