package com.qcloud.component.orderform.web.observer;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.AbstractNotifyAllStateOrderObserver;
import com.qcloud.component.orderform.QAfterSaleOrder;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.orderform.QOrderItemDetail;
import com.qcloud.component.orderform.model.RecordStateTime;
import com.qcloud.component.orderform.model.key.TypeEnum.RecordStateTimeType;
import com.qcloud.component.orderform.service.RecordStateTimeService;

@Component
public class OrderStateRecordTimeObserver extends AbstractNotifyAllStateOrderObserver {

    private Log                    logger = LogFactory.getLog(getClass());

    @Autowired
    private RecordStateTimeService recordStateTimeService;

    @Override
    protected void doOrderNotify(QOrder t, int state, String[] variable) {

        logger.info("总单状态记录:" + t.getOrderNumber());
        RecordStateTime recordStateTime = new RecordStateTime();
        recordStateTime.setOrderId(t.getId());
        recordStateTime.setOrderDate(t.getOrderDate());
        recordStateTime.setState(state);
        recordStateTime.setTime(new Date());
        recordStateTime.setDataId(t.getId());
        recordStateTime.setDataIdType(RecordStateTimeType.COLLECT.getKey());
        recordStateTimeService.add(recordStateTime);
    }

    @Override
    protected void doMerchantNotify(QMerchantOrder m, int state, String[] variable) {

        logger.info("子单状态记录:" + m.getOrderNumber());
        RecordStateTime recordStateTime = new RecordStateTime();
        recordStateTime.setOrderId(m.getOrder().getId());
        recordStateTime.setOrderDate(m.getOrder().getOrderDate());
        recordStateTime.setState(state);
        recordStateTime.setTime(new Date());
        recordStateTime.setDataId(m.getId());
        recordStateTime.setDataIdType(RecordStateTimeType.SUB.getKey());
        recordStateTimeService.add(recordStateTime);
    }

    @Override
    protected void doItemNotify(QOrderItem i, int state, String[] variable) {

        logger.info("订单项状态记录:" + i.getName());
        RecordStateTime recordStateTime = new RecordStateTime();
        recordStateTime.setOrderId(i.getOrder().getId());
        recordStateTime.setOrderDate(i.getOrder().getOrderDate());
        recordStateTime.setState(state);
        recordStateTime.setTime(new Date());
        recordStateTime.setDataId(i.getId());
        recordStateTime.setDataIdType(RecordStateTimeType.ITEM.getKey());
        recordStateTimeService.add(recordStateTime);
    }

    @Override
    protected void doDetailNotify(QOrderItemDetail d, int state, String[] variable) {

        logger.info("订单项明细状态记录:" + d.getName());
        RecordStateTime recordStateTime = new RecordStateTime();
        recordStateTime.setOrderId(d.getOrder().getId());
        recordStateTime.setOrderDate(d.getOrder().getOrderDate());
        recordStateTime.setState(state);
        recordStateTime.setTime(new Date());
        recordStateTime.setDataId(d.getId());
        recordStateTime.setDataIdType(RecordStateTimeType.DETAIL.getKey());
        recordStateTimeService.add(recordStateTime);
    }

    @Override
    protected void doRefundNotify(QAfterSaleOrder t, int state, String[] variable) {

        QOrder order = t.getMerchantOrder().getOrder();
        RecordStateTime recordStateTime = new RecordStateTime();
        recordStateTime.setOrderId(order.getId());
        recordStateTime.setOrderDate(order.getOrderDate());
        recordStateTime.setState(state);
        recordStateTime.setTime(new Date());
        recordStateTime.setDataId(t.getAfterSaleId());
        recordStateTime.setDataIdType(RecordStateTimeType.REFUND.getKey());
        recordStateTimeService.add(recordStateTime);
    }

    @Override
    protected void doReturnNotify(QAfterSaleOrder t, int state, String[] variable) {

        QOrder order = t.getMerchantOrder().getOrder();
        RecordStateTime recordStateTime = new RecordStateTime();
        recordStateTime.setOrderId(order.getId());
        recordStateTime.setOrderDate(order.getOrderDate());
        recordStateTime.setState(state);
        recordStateTime.setTime(new Date());
        recordStateTime.setDataId(t.getAfterSaleId());
        recordStateTime.setDataIdType(RecordStateTimeType.RETURN.getKey());
        recordStateTimeService.add(recordStateTime);
    }

    @Override
    protected void doExchangeNotify(QAfterSaleOrder t, int state, String[] variable) {

        QOrder order = t.getMerchantOrder().getOrder();
        RecordStateTime recordStateTime = new RecordStateTime();
        recordStateTime.setOrderId(order.getId());
        recordStateTime.setOrderDate(order.getOrderDate());
        recordStateTime.setState(state);
        recordStateTime.setTime(new Date());
        recordStateTime.setDataId(t.getAfterSaleId());
        recordStateTime.setDataIdType(RecordStateTimeType.EXCAHANGE.getKey());
        recordStateTimeService.add(recordStateTime);
    }
}
