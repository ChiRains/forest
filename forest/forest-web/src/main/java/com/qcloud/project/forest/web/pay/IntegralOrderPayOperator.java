package com.qcloud.project.forest.web.pay;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.pay.PayObject;
import com.qcloud.component.pay.PaymentOperate;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.project.forest.model.IntegralOrder;
import com.qcloud.project.forest.model.key.TypeEnum.IntegralOrderStateType;
import com.qcloud.project.forest.service.IntegralOrderService;

@Service
public class IntegralOrderPayOperator implements PaymentOperate {

    @Autowired
    private IntegralOrderService integralOrderService;

    @Override
    public PayObject getPayObject(Long objectId, Date occurTime) {

        final IntegralOrder order = integralOrderService.getByOrder(objectId);
        AssertUtil.assertNotNull(order, "订单不存在." + objectId);
        return new PayObject() {

            @Override
            public boolean canPay() {

                return order.getState() == IntegralOrderStateType.TO_PAY.getKey();
            }

            @Override
            public Long getObjectId() {

                return order.getId();
            }

            @Override
            public Date getOccurTime() {

                return order.getTime();
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

        IntegralOrder order = integralOrderService.getByOrder(objectId);
        AssertUtil.assertNotNull(order, "订单不存在." + objectId);
        order.setState(IntegralOrderStateType.TO_SHIP.getKey());
        return integralOrderService.update(order);
    }
}
