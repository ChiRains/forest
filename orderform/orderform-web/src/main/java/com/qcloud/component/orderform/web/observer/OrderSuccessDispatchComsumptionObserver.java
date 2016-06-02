package com.qcloud.component.orderform.web.observer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.OrderObserver;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.WealthType;

@Component
public class OrderSuccessDispatchComsumptionObserver implements OrderObserver<QMerchantOrder> {

    private Log                  logger = LogFactory.getLog(getClass());

    @Autowired
    private PersonalcenterClient personalcenterClient;

    @Override
    public void doNotify(QMerchantOrder t, int state, String[] variable) {

        logger.info("促成交易,赠送兑兑劵:" + t.getOrderNumber());
        personalcenterClient.calculateMyWealth(t.getOrder().getUserId(), WealthType.COMSUMPTION_CURRENCY, t.getCash() - t.getPostage(), true, "分单：" + t.getOrderNumber() + ",现金消费：" + (t.getCash() - t.getPostage()));
    }
}
