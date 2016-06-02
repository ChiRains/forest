package com.qcloud.component.orderform.engine.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.my.AfterSaleType;
import com.qcloud.component.orderform.AutoChangeStateCondition;
import com.qcloud.component.orderform.AutoChangeTimeLimit;
import com.qcloud.component.orderform.engine.AfterSaleSelecterService;
import com.qcloud.component.orderform.engine.AfterSaleStateService;
import com.qcloud.component.orderform.engine.AutoChangeService;
import com.qcloud.component.orderform.engine.OrderSelecterService;
import com.qcloud.component.orderform.engine.OrderStateService;
import com.qcloud.component.orderform.entity.ExchangeAfterSaleOrder;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.RefundAfterSaleOrder;
import com.qcloud.component.orderform.entity.ReturnAfterSaleOrder;
import com.qcloud.component.orderform.model.AutoChangeDefination;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.model.ExchangeOrder;
import com.qcloud.component.orderform.model.RefundOrder;
import com.qcloud.component.orderform.model.ReturnOrder;
import com.qcloud.component.orderform.service.CollectOrderService;
import com.qcloud.component.orderform.service.ExchangeOrderService;
import com.qcloud.component.orderform.service.RefundOrderService;
import com.qcloud.component.orderform.service.ReturnOrderService;
import com.qcloud.pirates.core.context.PiratesBeanFactoryAware;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.StringUtil;

@Service
public class AutoChangeServiceImpl implements AutoChangeService {

    private List<AutoChangeDefination>      changeList     = new ArrayList<AutoChangeDefination>();

    private Map<AutoChangeDefination, Date> executeTimeMap = new HashMap<AutoChangeDefination, Date>();

    @Autowired
    private CollectOrderService             collectOrderService;

    @Autowired
    private OrderStateService               orderStateService;

    @Autowired
    private RefundOrderService              refundOrderService;

    @Autowired
    private ReturnOrderService              returnOrderService;

    @Autowired
    private ExchangeOrderService            exchangeOrderService;

    @Autowired
    private AfterSaleStateService           afterSaleStateService;

    @Autowired
    private AfterSaleSelecterService        afterSaleSelecterService;

    @Autowired
    private OrderSelecterService            orderSelecterService;

    @SuppressWarnings("rawtypes")
    @PostConstruct
    public void init() {

        Xml xml = XmlFactory.get("orderform-auto-change");
        if (xml != null) {
            List<XmlItem> list = xml.getItemList();
            for (XmlItem xmlItem : list) {
                String sourceStr = xmlItem.getAttrMap().get("source");
                String destStr = xmlItem.getAttrMap().get("dest");
                String typeStr = xmlItem.getAttrMap().get("type");
                String timeSplitStr = xmlItem.getAttrMap().get("timeSplit");
                String timeLimitStr = xmlItem.getAttrMap().get("timeLimit");
                String conditionStr = xmlItem.getAttrMap().get("condition");
                AutoChangeDefination autoChangeDefination = new AutoChangeDefination();
                int source = Integer.parseInt(StringUtil.nullToEmpty(sourceStr).trim());
                int dest = Integer.parseInt(StringUtil.nullToEmpty(destStr).trim());
                int type = Integer.parseInt(StringUtil.nullToEmpty(typeStr).trim());
                int timeSplit = Integer.parseInt(StringUtil.nullToEmpty(timeSplitStr).trim());
                timeLimitStr = StringUtil.nullToEmpty(timeLimitStr).trim();
                conditionStr = StringUtil.nullToEmpty(conditionStr).trim();
                autoChangeDefination.setSourceState(source);
                autoChangeDefination.setDestState(dest);
                autoChangeDefination.setType(type);
                autoChangeDefination.setTimeSplit(timeSplit);
                AutoChangeTimeLimit autoChangeTimeLimit = (AutoChangeTimeLimit) PiratesBeanFactoryAware.getBeanFactory().getBean(timeLimitStr);
                autoChangeDefination.setAutoChangeTimeLimit(autoChangeTimeLimit);
                if (StringUtils.isNotEmpty(conditionStr)) {
                    AutoChangeStateCondition autoChangeStateCondition = (AutoChangeStateCondition) PiratesBeanFactoryAware.getBeanFactory().getBean(conditionStr);
                    autoChangeDefination.setAutoChangeStateCondition(autoChangeStateCondition);
                }
                changeList.add(autoChangeDefination);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void autoChange() {

        for (AutoChangeDefination autoChangeDefination : changeList) {
            Date date = executeTimeMap.get(autoChangeDefination);
            boolean execute = date == null;
            if (!execute) {
                Date next = DateUtil.addTime(date, autoChangeDefination.getTimeSplit());
                if (new Date().after(next)) {
                    execute = true;
                }
            }
            if (execute) {
                switch (autoChangeDefination.getType()) {
                case 1:
                    // 订单
                    autoChange(autoChangeDefination.getAutoChangeStateCondition(), autoChangeDefination.getSourceState(), autoChangeDefination.getDestState(), autoChangeDefination.getAutoChangeTimeLimit().limit());
                    break;
                case 2:
                    // 退款单
                    autoChangeRefund(autoChangeDefination.getAutoChangeStateCondition(), autoChangeDefination.getDestState(), autoChangeDefination.getDestState());
                    break;
                case 3:
                    // 退货单
                    autoChangeReturn(autoChangeDefination.getAutoChangeStateCondition(), autoChangeDefination.getDestState(), autoChangeDefination.getDestState());
                    break;
                case 4:
                    // 换货单
                    autoChangeExchange(autoChangeDefination.getAutoChangeStateCondition(), autoChangeDefination.getDestState(), autoChangeDefination.getDestState());
                    break;
                default:
                    break;
                }
            }
        }
    }

    private boolean autoChangeExchange(AutoChangeStateCondition<AfterSaleType> autoChangeStateCondition, int source, int dest) {

        int start = 0;
        int size = 200;
        int number = -1;
        do {
            List<ExchangeOrder> list = exchangeOrderService.list4ExpireState(source, start, size);
            for (ExchangeOrder exchangeOrder : list) {
                autoChangeExchange(autoChangeStateCondition, exchangeOrder, dest);
            }
            number = list.size();
            start += size;
        } while (number == size);
        return true;
    }

    @Transactional
    private boolean autoChangeExchange(AutoChangeStateCondition<AfterSaleType> autoChangeStateCondition, ExchangeOrder refund, int target) {

        if (target < 0) {
            return false;
        }
        if (autoChangeStateCondition != null && !autoChangeStateCondition.canChange(refund.getId(), AfterSaleType.EXCHANGE)) {
            return false;
        }
        MerchantOrderEntity merchantOrderEntity = orderSelecterService.getMerchantOrder(refund.getSubOrderId(), refund.getOrderDate());
        ExchangeAfterSaleOrder exchangeAfterSaleOrder = afterSaleSelecterService.getExchangeOrder(merchantOrderEntity, refund.getId());
        return afterSaleStateService.exchangeExchangeOrderState(exchangeAfterSaleOrder, target);
    }

    private boolean autoChangeReturn(AutoChangeStateCondition<AfterSaleType> autoChangeStateCondition, int source, int dest) {

        int start = 0;
        int size = 200;
        int number = -1;
        do {
            List<ReturnOrder> list = returnOrderService.list4ExpireState(source, start, size);
            for (ReturnOrder returnOrder : list) {
                autoChangeReturn(autoChangeStateCondition, returnOrder, dest);
            }
            number = list.size();
        } while (number == size);
        return true;
    }

    @Transactional
    private boolean autoChangeReturn(AutoChangeStateCondition<AfterSaleType> autoChangeStateCondition, ReturnOrder refund, int target) {

        if (target < 0) {
            return false;
        }
        if (autoChangeStateCondition != null && !autoChangeStateCondition.canChange(refund.getId(), AfterSaleType.RETURN)) {
            return false;
        }
        MerchantOrderEntity merchantOrderEntity = orderSelecterService.getMerchantOrder(refund.getSubOrderId(), refund.getOrderDate());
        ReturnAfterSaleOrder returnAfterSaleOrder = afterSaleSelecterService.getReturnOrder(merchantOrderEntity, refund.getId());
        return afterSaleStateService.exchangeReturnOrderState(returnAfterSaleOrder, target);
    }

    private boolean autoChangeRefund(AutoChangeStateCondition<AfterSaleType> autoChangeStateCondition, int source, int dest) {

        int start = 0;
        int size = 200;
        int number = -1;
        do {
            List<RefundOrder> list = refundOrderService.list4ExpireState(source, start, size);
            for (RefundOrder refundOrder : list) {
                autoChangeRefund(autoChangeStateCondition, refundOrder, dest);
            }
            number = list.size();
        } while (number == size);
        return true;
    }

    @Transactional
    private boolean autoChangeRefund(AutoChangeStateCondition<AfterSaleType> autoChangeStateCondition, RefundOrder refund, int target) {

        if (target < 0) {
            return false;
        }
        if (autoChangeStateCondition != null && !autoChangeStateCondition.canChange(refund.getId(), AfterSaleType.REFUND)) {
            return false;
        }
        MerchantOrderEntity merchantOrderEntity = orderSelecterService.getMerchantOrder(refund.getSubOrderId(), refund.getOrderDate());
        RefundAfterSaleOrder refundAfterSaleOrder = afterSaleSelecterService.getRefundOrder(merchantOrderEntity, refund.getId());
        return afterSaleStateService.exchangeRefundOrderState(refundAfterSaleOrder, target);
    }

    private boolean autoChange(AutoChangeStateCondition<Date> autoChangeStateCondition, int source, int dest, int latelyMinutes) {

        Date[] dates = collectOrderService.getDatesByLatelyMinutes(latelyMinutes);
        for (Date date : dates) {
            int start = 0;
            int size = 200;
            int number = -1;
            do {
                List<CollectOrder> list = collectOrderService.list4ExpireState(date, source, start, size);
                for (CollectOrder collectOrder : list) {
                    autoChange(autoChangeStateCondition, collectOrder, dest);
                }
                number = list.size();
            } while (number == size);
        }
        return true;
    }

    @Transactional
    private boolean autoChange(AutoChangeStateCondition<Date> autoChangeStateCondition, CollectOrder collectOrder, int target) {

        if (target < 0) {
            return false;
        }
        if (autoChangeStateCondition != null && !autoChangeStateCondition.canChange(collectOrder.getId(), collectOrder.getTime())) {
            return false;
        }
        return orderStateService.exchangeOrderState(collectOrder.getId(), collectOrder.getTime(), target, -999L);
    }

    @Override
    public Date getStateValidTime(int type, int state, Date date) {

        for (AutoChangeDefination autoChangeDefination : changeList) {
            if (autoChangeDefination.getType() == type && state == autoChangeDefination.getSourceState()) {
                return DateUtil.addTime(date, autoChangeDefination.getAutoChangeTimeLimit().limit());
            }
        }
        return DateUtil.addDate(date, 365 * 3);
    }
}
