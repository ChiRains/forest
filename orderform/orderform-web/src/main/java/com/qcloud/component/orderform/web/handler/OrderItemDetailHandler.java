package com.qcloud.component.orderform.web.handler;

import java.util.List;
import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.web.vo.admin.AdminOrderItemDetailVO;

public interface OrderItemDetailHandler {

//	List<OrderItemDetailVO> toVOList(List<OrderItemDetail> list);
//
//	OrderItemDetailVO toVO(OrderItemDetail orderItemDetail);

	List<AdminOrderItemDetailVO> toVOList4Admin(List<OrderItemDetail> list, long storeId);

	AdminOrderItemDetailVO toVO4Admin(OrderItemDetail orderItemDetail);
}
