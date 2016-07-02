package com.qcloud.component.orderform.web.handler;

import java.util.List;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.web.vo.admin.AdminOrderItemVO;

public interface OrderItemHandler {

    // List<OrderItemVO> toVOList(List<OrderItem> list);
    //
    // OrderItemVO toVO(OrderItem orderItem);
    List<AdminOrderItemVO> toVOList4Admin(List<OrderItem> list);

    AdminOrderItemVO toVO4Admin(OrderItem orderItem);

//    List<OrderItemVO> toOrderItemVOList(List<OrderItem> list, Date time);
}
