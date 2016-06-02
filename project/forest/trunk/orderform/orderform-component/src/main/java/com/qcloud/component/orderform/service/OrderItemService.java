package com.qcloud.component.orderform.service;

import java.util.Date;
import java.util.List;
import com.qcloud.component.orderform.entity.OrderItemEntity;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.model.query.OrderItemQuery;
import com.qcloud.pirates.data.Page;

public interface OrderItemService {

    public boolean add(OrderItemEntity orderItemEntity, Date time);

    public OrderItem get(Long id, Date time);

    public boolean delete(Long id);

    public boolean update(OrderItem orderItem, Date time);

    // 更新状态专用接口
    boolean updateState(OrderItemEntity orderItemEntity, int state);

    public Page<OrderItem> page(OrderItemQuery query, int start, int count);

    List<OrderItem> listByCollectOrder(Long collectOrderId, Date time);

    List<OrderItem> listBySubOrder(Long subOrderId, Date time);
}
