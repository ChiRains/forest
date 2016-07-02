package com.qcloud.component.orderform.web.observer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.orderform.OrderObserver;
import com.qcloud.component.orderform.QOrderItemDetail;

@Component
public class MerchandiseSalesVolumeObserver implements OrderObserver<QOrderItemDetail> {

    private Log                   logger = LogFactory.getLog(getClass());

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Override
    public void doNotify(QOrderItemDetail t, int state, String[] variable) {

        logger.info("处理销量和购买记录：" + t.getOrder().getOrderNumber() + " " + t.getName());
        commoditycenterClient.addSalesVolume(t.getUnifiedMerchandiseId(), t.getOrderItem().getNumber());
        long[] orderIds = new long[] { t.getOrder().getId(), t.getMerchantOrder().getId(), t.getOrderItem().getId(), t.getId()};
        commoditycenterClient.incrementMerchandiseDealRecoed(t.getOrder().getUserId(), t.getUnifiedMerchandiseId(), t.getOrderItem().getNumber(), t.getSpecifications(), orderIds);
    }
}
