package com.qcloud.component.orderform.web.observer;

import java.util.HashMap;
import java.util.Map;
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
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicservice.SmsClient;
import com.qcloud.pirates.util.AssertUtil;

@Component
public class SendSmsToUserObserver extends AbstractNotifyAllStateOrderObserver {

    private Log                  logger = LogFactory.getLog(getClass());

    @Autowired
    private SmsClient            smsClient;

    @Autowired
    private PersonalcenterClient personalcenterClient;

    @Override
    protected void doOrderNotify(QOrder t, int state, String[] variable) {

        AssertUtil.assertTrue(variable != null && variable.length == 1, "发端信变量正确.");
        logger.info("总单:给用户发短信:" + t.getOrderNumber() + "" + t.getUserId() + " " + state);
        QUser user = personalcenterClient.getUser(t.getUserId());
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderNumber", t.getOrderNumber());
        smsClient.send(variable[0], user.getMobile(), map);
    }

    @Override
    protected void doMerchantNotify(QMerchantOrder t, int state, String[] variable) {

        AssertUtil.assertTrue(variable != null && variable.length == 1, "发端信变量正确.");
        logger.info("分单：给用户发短信:" + t.getOrderNumber() + "" + t.getOrder().getUserId() + " " + state);
        QUser user = personalcenterClient.getUser(t.getOrder().getUserId());
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderNumber", t.getOrder().getOrderNumber());
        map.put("merchantOrderNumber", t.getOrderNumber());
        smsClient.send(variable[0], user.getMobile(), map);
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

        logger.info("退款:给用户发短信:" + t.getAfterSaleOrderNumber() + "" + t.getUserId() + " " + state);
        QUser user = personalcenterClient.getUser(t.getUserId());
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderNumber", t.getMerchantOrder().getOrder().getOrderNumber());
        map.put("merchantOrderNumber", t.getMerchantOrder().getOrderNumber());
        map.put("afterSaleOrderNumber", t.getAfterSaleOrderNumber());
        smsClient.send(variable[0], user.getMobile(), map);
    }

    @Override
    protected void doReturnNotify(QAfterSaleOrder t, int state, String[] variable) {

        logger.info("退货:给用户发短信:" + t.getAfterSaleOrderNumber() + "" + t.getUserId() + " " + state);
        QUser user = personalcenterClient.getUser(t.getUserId());
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderNumber", t.getMerchantOrder().getOrder().getOrderNumber());
        map.put("merchantOrderNumber", t.getMerchantOrder().getOrderNumber());
        map.put("afterSaleOrderNumber", t.getAfterSaleOrderNumber());
        smsClient.send(variable[0], user.getMobile(), map);
    }

    @Override
    protected void doExchangeNotify(QAfterSaleOrder t, int state, String[] variable) {

        logger.info("换货:给用户发短信:" + t.getAfterSaleOrderNumber() + "" + t.getUserId() + " " + state);
        QUser user = personalcenterClient.getUser(t.getUserId());
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderNumber", t.getMerchantOrder().getOrder().getOrderNumber());
        map.put("merchantOrderNumber", t.getMerchantOrder().getOrderNumber());
        map.put("afterSaleOrderNumber", t.getAfterSaleOrderNumber());
        smsClient.send(variable[0], user.getMobile(), map);
    }
}
