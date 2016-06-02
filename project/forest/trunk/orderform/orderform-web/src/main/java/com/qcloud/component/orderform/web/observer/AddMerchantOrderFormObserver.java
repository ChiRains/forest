package com.qcloud.component.orderform.web.observer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.OrderObserver;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.service.OrderConfigService;
import com.qcloud.component.sellercenter.SellercenterClient;

@Component
public class AddMerchantOrderFormObserver implements OrderObserver<QMerchantOrder> {

    private Log                logger = LogFactory.getLog(getClass());

    @Autowired
    private SellercenterClient sellercenterClient;

    @Autowired
    private OrderConfigService orderConfigService;

    @Override
    public void doNotify(QMerchantOrder t, int state, String[] variable) {

        logger.info("添加商家订单:" + t.getOrderNumber());
        sellercenterClient.addMerchantOrderForm(t.getMerchantId(), t.getOrder().getId(), t.getId(), t.getStoreId(), orderConfigService.getNormalMerchantOrderState(state), t.getOrder().getOrderDate());
    }
}
