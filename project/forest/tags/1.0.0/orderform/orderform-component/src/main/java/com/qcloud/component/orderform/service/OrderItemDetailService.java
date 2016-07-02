package com.qcloud.component.orderform.service;

import java.util.Date;
import java.util.List;
import com.qcloud.component.orderform.entity.OrderItemDetailEntity;
import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.model.query.OrderItemDetailQuery;
import com.qcloud.pirates.data.Page;

public interface OrderItemDetailService {

    public boolean add(OrderItemDetailEntity orderItemDetail, Date time);

    public OrderItemDetail get(Long id, Date time);

    public boolean delete(Long id);

    public boolean update(OrderItemDetail orderItemDetail, Date time);

    boolean update(List<OrderItemDetail> list, Date time);

    // 更新状态专用接口
    boolean updateState(OrderItemDetailEntity orderItemDetail, int state);

    public Page<OrderItemDetail> page(OrderItemDetailQuery query, int start, int count);

    List<OrderItemDetail> listByCollectOrder(Long collectOrderId, Date time);

    List<OrderItemDetail> listBySubOrder(Long subOrderId, Date time);

    List<OrderItemDetail> listByOrderItem(Long orderItemId, Date time);

    public List<OrderItemDetail> listOrderItemDetail(Long orderId, Date time);

    public List<OrderItemDetail> listItemDetailByItemId(Long orderItemId, Date time);
}
