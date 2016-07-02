package com.qcloud.component.orderform.engine;

import java.util.Date;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.OrderEntity;

public interface OrderSelecterService {

    OrderEntity getOrder(Long orderId, Date orderDate);

    MerchantOrderEntity getMerchantOrder(Long subOrderId, Date orderDate);
}
