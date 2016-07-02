package com.qcloud.component.orderform.web.pay;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.engine.OrderStateService;
import com.qcloud.component.orderform.web.util.OrderStateType;
import com.qcloud.component.pay.PayObject;
import com.qcloud.component.pay.PaymentOperate;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;

// 订单支付
@Service
public class OrderPayOperator implements PaymentOperate {

    @Autowired
    private OrderformClient   orderformClient;

    @Autowired
    private OrderStateService orderStateService;

    @Override
    public PayObject getPayObject(Long objectId, Date occurTime) {

        final QOrder order = orderformClient.getOrder(objectId, occurTime);
        AssertUtil.assertNotNull(order, "订单不存在." + objectId + " " + DateUtil.date2String(occurTime));
        return new PayObject() {

            @Override
            public boolean canPay() {

                return order.getState() == OrderStateType.NORMAL_TOPAY.getKey();
            }

            @Override
            public Long getObjectId() {

                return order.getId();
            }

            @Override
            public Date getOccurTime() {

                return order.getOrderDate();
            }

            @Override
            public String getObjectNumber() {

                return order.getOrderNumber();
            }

            @Override
            public double getCash() {

                return order.getCash();
            }
        };
    }

    @Override
    public boolean paid(Long objectId, Date occurTime) {

        final QOrder order = orderformClient.getOrder(objectId, occurTime);
        AssertUtil.assertNotNull(order, "订单不存在." + objectId + " " + DateUtil.date2String(occurTime));
        return orderStateService.exchangeOrderState(objectId, occurTime, OrderStateType.NORMAL_TO_PACKING.getKey(), -1L);
    }
}
