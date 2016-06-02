package com.qcloud.component.orderform.web.observer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.OrderObserver;
import com.qcloud.component.orderform.QAfterSaleOrder;

@Component
public class OrderRefundReturnCouponObserver implements OrderObserver<QAfterSaleOrder> {

    private Log logger = LogFactory.getLog(getClass());

    @Override
    public void doNotify(QAfterSaleOrder t, int state, String[] variable) {

        logger.info("看到这句话时,说明这里是要退优惠劵,但是还没有退.");
    }
}
