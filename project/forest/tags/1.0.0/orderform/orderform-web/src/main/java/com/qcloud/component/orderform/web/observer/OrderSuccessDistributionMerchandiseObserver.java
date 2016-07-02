package com.qcloud.component.orderform.web.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.brokerage.BrokerageClient;
import com.qcloud.component.orderform.OrderObserver;
import com.qcloud.component.orderform.QOrderItem;

@Component
public class OrderSuccessDistributionMerchandiseObserver implements OrderObserver<QOrderItem> {

    @Autowired
    BrokerageClient brokerageClient;

    @Override
    public void doNotify(QOrderItem t, int state, String[] variable) {

        if (t.getSence() == -1) {
//            distributionClient.addMerchandiseDealRecords(t.getOrder().getUserId(), t.getUnifiedMerchandiseId(), t.getPurchase(), t.getCash(), t.getPrice(), t.getNumber(), t.getOrder().getId(), t.getId(), t.getOrder().getOrderDate());
        }
    }
}
