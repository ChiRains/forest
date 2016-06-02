package com.qcloud.component.orderform.web.observer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.brokerage.BrokerageClient;
import com.qcloud.component.orderform.OrderObserver;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrderItem;

@Component
public class OrderSuccessDistributionMerchantObserver implements OrderObserver<QMerchantOrder> {

    @Autowired
    private BrokerageClient brokerageClient;

    @Override
    public void doNotify(QMerchantOrder t, int state, String[] variable) {

        List<QOrderItem> list = t.getOrderItemList();
        double purchaseSum = 0.0;
        double priceSum = 0.0;
        for (QOrderItem qOrderItem : list) {
            purchaseSum += qOrderItem.getPurchase() * qOrderItem.getNumber();
            priceSum += qOrderItem.getPrice() * qOrderItem.getNumber();
        }
//        distributionClient.addMerchantDealRecords(t.getOrder().getUserId(), t.getMerchantId(), purchaseSum, t.getCash() - t.getPostage(), priceSum, t.getId(), t.getOrder().getOrderDate());
    }
}
