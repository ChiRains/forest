package com.qcloud.component.orderform.dao;

import java.util.Date;
import java.util.List;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.model.query.OrderItemQuery;
import com.qcloud.pirates.data.Page;

public interface OrderItemDao {

    public boolean add(OrderItem orderItem, Date time);

    public boolean delete(Long id);

    public OrderItem get(Long id, Date time);

    public boolean update(OrderItem orderItem, Date time);

    public Page<OrderItem> page(OrderItemQuery query, int start, int size);

    List<OrderItem> listByCollectOrder(Long collectOrderId, Date time);

    List<OrderItem> listBySubOrder(Long subOrderId, Date time);
}
