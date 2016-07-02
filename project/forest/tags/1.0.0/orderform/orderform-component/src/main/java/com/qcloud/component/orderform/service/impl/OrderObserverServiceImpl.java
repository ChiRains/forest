package com.qcloud.component.orderform.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.qcloud.component.orderform.OrderObserver;
import com.qcloud.component.orderform.entity.ExchangeAfterSaleOrder;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.entity.OrderItemDetailEntity;
import com.qcloud.component.orderform.entity.OrderItemEntity;
import com.qcloud.component.orderform.entity.RefundAfterSaleOrder;
import com.qcloud.component.orderform.entity.ReturnAfterSaleOrder;
import com.qcloud.component.orderform.model.OrderObserverDefination;
import com.qcloud.component.orderform.service.OrderObserverService;
import com.qcloud.pirates.core.context.PiratesBeanFactoryAware;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.util.StringUtil;

@Service
public class OrderObserverServiceImpl implements OrderObserverService {

    private List<OrderObserverDefination> eventList = new ArrayList<OrderObserverDefination>();

    private Log                           logger    = LogFactory.getLog(getClass());

    @PostConstruct
    public void init() {

        Xml xml = XmlFactory.get("orderform-observer");
        if (xml != null) {
            List<XmlItem> list = xml.getItemList();
            for (XmlItem xmlItem : list) {
                String stateStr = xmlItem.getAttrMap().get("state");
                String typeStr = xmlItem.getAttrMap().get("type");
                String eventStr = xmlItem.getAttrMap().get("observer");
                String variablesStr = xmlItem.getAttrMap().get("variables");
                int state = Integer.parseInt(StringUtil.nullToEmpty(stateStr).trim());
                int type = Integer.parseInt(StringUtil.nullToEmpty(typeStr).trim());
                String event = StringUtil.nullToEmpty(eventStr).trim();
                String variables = StringUtil.nullToEmpty(variablesStr).trim();
                OrderObserverDefination orderObserverDefination = new OrderObserverDefination();
                orderObserverDefination.setDesc(StringUtil.nullToEmpty(xmlItem.getText()));
                OrderObserver<?> orderObserver = (OrderObserver<?>) PiratesBeanFactoryAware.getBeanFactory().getBean(event);
                orderObserverDefination.setOrderObserver(orderObserver);
                orderObserverDefination.setState(state);
                orderObserverDefination.setType(type);
                if (StringUtils.isNotEmpty(variables)) {
                    Xml variablesXml = XmlFactory.get(variables);
                    if (variablesXml != null) {
                        List<XmlItem> variableList = variablesXml.getItemList();
                        String[] variableArray = new String[variableList.size()];
                        for (int index = 0; index < variableList.size(); index++) {
                            XmlItem variableXmlItem = variableList.get(index);
                            String variableStr = variableXmlItem.getAttrMap().get("variable");
                            String variable = StringUtil.nullToEmpty(variableStr).trim();
                            variableArray[index] = variable;
                        }
                        orderObserverDefination.setVariable(variableArray);
                    } else {
                        orderObserverDefination.setVariable(new String[0]);
                    }
                } else {
                    String variableStr = xmlItem.getAttrMap().get("variable");
                    String variable = StringUtil.nullToEmpty(variableStr).trim();
                    if (StringUtils.isEmpty(variable)) {
                        orderObserverDefination.setVariable(new String[0]);
                    } else {
                        String[] variableArray = new String[1];
                        variableArray[0] = variable;
                        orderObserverDefination.setVariable(variableArray);
                    }
                }
                eventList.add(orderObserverDefination);
            }
        }
    }

    @Override
    public void doNotify(OrderEntity order, int state) {

        List<OrderObserverDefination> list = getNeedDoNotifyList(1, state);
        doNotify(list, order, state);
    }

    @Override
    public void doNotify(MerchantOrderEntity merchantOrder, int state) {

        List<OrderObserverDefination> list = getNeedDoNotifyList(2, state);
        doNotify(list, merchantOrder, state);
    }

    @Override
    public void doNotify(OrderItemEntity orderItem, int state) {

        List<OrderObserverDefination> list = getNeedDoNotifyList(3, state);
        doNotify(list, orderItem, state);
    }

    @Override
    public void doNotify(OrderItemDetailEntity orderItemDetail, int state) {

        List<OrderObserverDefination> list = getNeedDoNotifyList(4, state);
        doNotify(list, orderItemDetail, state);
    }

    @Override
    public void doNotify(RefundAfterSaleOrder afterSaleOrder, int state) {

        List<OrderObserverDefination> list = getNeedDoNotifyList(5, state);
        doNotify(list, afterSaleOrder, state);
    }

    @Override
    public void doNotify(ReturnAfterSaleOrder afterSaleOrder, int state) {

        List<OrderObserverDefination> list = getNeedDoNotifyList(6, state);
        doNotify(list, afterSaleOrder, state);
    }

    @Override
    public void doNotify(ExchangeAfterSaleOrder afterSaleOrder, int state) {

        List<OrderObserverDefination> list = getNeedDoNotifyList(7, state);
        doNotify(list, afterSaleOrder, state);
    }

    private void doNotify(List<OrderObserverDefination> list, Object obj, int state) {

        for (OrderObserverDefination orderObserverDefination : list) {
            logger.info("订单事件:" + orderObserverDefination.getDesc());
            orderObserverDefination.getOrderObserver().doNotify(obj, state, orderObserverDefination.getVariable());
        }
    }

    private List<OrderObserverDefination> getNeedDoNotifyList(int type, int state) {

        List<OrderObserverDefination> list = new ArrayList<OrderObserverDefination>();
        for (OrderObserverDefination defination : eventList) {
            if ((defination.getState() == -1 || defination.getState() == state) && defination.getType() == type) {
                list.add(defination);
            }
        }
        return list;
    }
}
