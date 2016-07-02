package com.qcloud.component.orderform.web.observer;

import org.apache.commons.lang.NotImplementedException;
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
import com.qcloud.component.sellercenter.SellerMessageType;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.util.AssertUtil;

@Component
public class SendMessageToMerchantObserver extends AbstractNotifyAllStateOrderObserver {

    private Log                logger = LogFactory.getLog(getClass());

    @Autowired
    private SellercenterClient sellercenterClient;

    @Override
    protected void doOrderNotify(QOrder t, int state, String[] variable) {

        throw new NotImplementedException();
    }

    @Override
    protected void doMerchantNotify(QMerchantOrder t, int state, String[] variable) {

        AssertUtil.assertTrue(variable != null && variable.length == 1, "发站内信变量正确.");
        logger.info("分单:给商家用户发站内信:" + t.getOrderNumber() + "" + t.getMerchantId() + " " + state);
        sellercenterClient.sendMsgToMerchant(t.getMerchantId(), SellerMessageType.INSIDE_LETTER, "订单", "站内信是要从模板取的");
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

        AssertUtil.assertTrue(variable != null && variable.length == 1, "发站内信变量正确.");
        logger.info("退款:给商家用户发站内信:" + t.getAfterSaleOrderNumber() + "" + t.getMerchantId() + " " + state);
        sellercenterClient.sendMsgToMerchant(t.getMerchantId(), SellerMessageType.INSIDE_LETTER, "订单", "站内信是要从模板取的");
    }

    @Override
    protected void doReturnNotify(QAfterSaleOrder t, int state, String[] variable) {

        AssertUtil.assertTrue(variable != null && variable.length == 1, "发站内信变量正确.");
        logger.info("退货:给商家用户发站内信:" + t.getAfterSaleOrderNumber() + "" + t.getMerchantId() + " " + state);
        sellercenterClient.sendMsgToMerchant(t.getMerchantId(), SellerMessageType.INSIDE_LETTER, "订单", "站内信是要从模板取的");
    }

    @Override
    protected void doExchangeNotify(QAfterSaleOrder t, int state, String[] variable) {

        AssertUtil.assertTrue(variable != null && variable.length == 1, "发站内信变量正确.");
        logger.info("换货:给商家用户发站内信:" + t.getAfterSaleOrderNumber() + "" + t.getMerchantId() + " " + state);
        sellercenterClient.sendMsgToMerchant(t.getMerchantId(), SellerMessageType.INSIDE_LETTER, "订单", "站内信是要从模板取的");
    }
}
