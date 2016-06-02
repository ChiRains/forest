package com.qcloud.component.orderform.dao;

import java.util.Date;
import java.util.List;
import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.model.query.OrderItemDetailQuery;
import com.qcloud.pirates.data.Page;

public interface OrderItemDetailDao {

    public boolean add(OrderItemDetail orderItemDetail, Date time);

    public boolean delete(Long id);

    public OrderItemDetail get(Long id, Date time);

    public boolean update(OrderItemDetail orderItemDetail, Date time);

    public Page<OrderItemDetail> page(OrderItemDetailQuery query, int start, int size);

    List<OrderItemDetail> listByCollectOrder(Long collectOrderId, Date time);

    List<OrderItemDetail> listBySubOrder(Long subOrderId, Date time);

    List<OrderItemDetail> listByOrderItem(Long orderItemId, Date time);

    public List<OrderItemDetail> listOrderItemDetail(Long orderId, Date time);

    public List<OrderItemDetail> listItemDetailByItemId(Long orderItemId, Date time);
}
