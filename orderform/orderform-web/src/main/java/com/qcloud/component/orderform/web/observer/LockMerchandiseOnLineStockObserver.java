package com.qcloud.component.orderform.web.observer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.orderform.OrderObserver;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.pirates.util.AssertUtil;

@Component
public class LockMerchandiseOnLineStockObserver implements OrderObserver<QOrderItem> {

    private Log                   logger = LogFactory.getLog(getClass());

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Override
    public void doNotify(QOrderItem t, int state, String[] variable) {

        logger.info("锁定库存" + t.getName() + " " + t.getNumber());
        //
        boolean result = commoditycenterClient.lockOnlineStock(t.getUnifiedMerchandiseId(), 0 - t.getNumber());
        if (!result) {
            QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(t.getUnifiedMerchandiseId());
            AssertUtil.assertTrue(result, "锁定库存失败." + t.getUnifiedMerchandiseId() + " " + unifiedMerchandise.getStock() + " " + t.getNumber());
        }
    }
}
