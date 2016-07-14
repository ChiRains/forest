package com.qcloud.project.forest.web.observer;

import java.util.Date;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.AbstractNotifyAllStateOrderObserver;
import com.qcloud.component.orderform.QAfterSaleOrder;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.orderform.QOrderItemDetail;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.project.forest.model.ForestOrder;
import com.qcloud.project.forest.service.ForestOrderService;

@Component
public class ForestAfterPayObserver extends AbstractNotifyAllStateOrderObserver {

    @Autowired
    private ForestOrderService forestOrderService;

    @Override
    protected void doOrderNotify(QOrder t, int state, String[] variable) {

        ForestOrder forestOrder = forestOrderService.getByOrder(t.getId());
        AssertUtil.assertNotNull(forestOrder, "订单数据不存在.");
        forestOrder.setPayDate(new Date());
        forestOrderService.update(forestOrder);
    }

    @Override
    protected void doMerchantNotify(QMerchantOrder t, int state, String[] variable) {

        throw new NotImplementedException();
    }

    @Override
    protected void doItemNotify(QOrderItem t, int state, String[] variable) {

        throw new NotImplementedException();
    }

    @Override
    protected void doDetailNotify(QOrderItemDetail t, int state, String[] variable) {

        throw new NotImplementedException();
    }

    @Override
    protected void doRefundNotify(QAfterSaleOrder t, int state, String[] variable) {

        throw new NotImplementedException();
    }

    @Override
    protected void doReturnNotify(QAfterSaleOrder t, int state, String[] variable) {

        throw new NotImplementedException();
    }

    @Override
    protected void doExchangeNotify(QAfterSaleOrder t, int state, String[] variable) {

        throw new NotImplementedException();
    }
}
