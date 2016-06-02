package com.qcloud.component.orderform.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.my.QMyConsignee;
import com.qcloud.component.orderform.PaymentModeType;
import com.qcloud.component.orderform.dao.CollectOrderDao;
import com.qcloud.component.orderform.engine.AutoChangeService;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.model.query.CollectOrderQuery;
import com.qcloud.component.orderform.service.CollectOrderService;
import com.qcloud.component.orderform.service.OrderConfigService;
import com.qcloud.component.orderform.service.OrderObserverService;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;

@Service
public class CollectOrderServiceImpl implements CollectOrderService {

    @Autowired
    private CollectOrderDao      collectOrderDao;

    @Autowired
    private AutoIdGenerator      autoIdGenerator;

    @Autowired
    private OrderConfigService   orderConfigService;

    @Autowired
    private OrderObserverService orderObserverService;

    @Autowired
    private AutoChangeService    autoChangeService;

    private static final String  ID_KEY = "orderform_collect_order";

    @Override
    public boolean add(OrderEntity order) {

        CollectOrder collectOrder = order.getCollectOrder();
        long id = autoIdGenerator.get(ID_KEY);
        collectOrder.setId(id);
        collectOrder.setLastUpdateTime(collectOrder.getTime());
        collectOrder.setStateValidTime(autoChangeService.getStateValidTime(1, collectOrder.getState(), collectOrder.getLastUpdateTime()));
        boolean result = collectOrderDao.add(collectOrder);
        AssertUtil.assertTrue(result, "下订单失败.");
        orderObserverService.doNotify(order, collectOrder.getState());
        return result;
    }

    @Override
    public CollectOrder get(Long id, Date time) {

        return collectOrderDao.get(id, time);
    }

    public boolean update(CollectOrder collectOrder) {

        return collectOrderDao.update(collectOrder);
    }

    @Override
    public boolean updatePaymentMode(Long orderId, Date time, PaymentModeType mode) {

        CollectOrder collectOrder = get(orderId, time);
        collectOrder.setPaymentMode(mode.getKey());
        return collectOrderDao.update(collectOrder);
    }

    @Override
    public boolean updateState(OrderEntity order, int state) {

        CollectOrder collectOrder = get(order.getId(), order.getOrderDate());
        if (collectOrder.getState() != state) {
            orderObserverService.doNotify(order, state);
        }
        // 正式线上按交易完成处理
        // if (collectOrder.getState() != state && OrderStateType.NORMAL_TRADE_SUCCESS.getKey() == state) {
        // if (collectOrder.getState() != state && OrderStateType.NORMAL_SIGN.getKey() == state) {
        // // 交易成功,给佣金,给积分,送礼
        // personalcenterClient.calculateMyWealth(collectOrder.getUserId(), WealthType.INTEGRAL, collectOrder.getCash(), true, "订单：" + collectOrder.getOrderNumber() + ",现金消费：" + collectOrder.getCash());
        // personalcenterClient.calculateMyWealth(collectOrder.getUserId(), WealthType.COMSUMPTION_CURRENCY, collectOrder.getCash(), true, "订单：" + collectOrder.getOrderNumber() + ",现金消费：" + collectOrder.getCash());
        // }
        collectOrder.setState(state);
        collectOrder.setLastUpdateTime(new Date());
        collectOrder.setStateValidTime(autoChangeService.getStateValidTime(1, collectOrder.getState(), collectOrder.getLastUpdateTime()));
        return update(collectOrder);
    }

    @Override
    public boolean delete(Long id) {

        return collectOrderDao.delete(id);
    }

    @Override
    public Page<CollectOrder> page(CollectOrderQuery query, int start, int count) {

        return collectOrderDao.page(query, start, count);
    }

    public int getUserOrderFormState(int state) {

        return orderConfigService.getNormalPersonalOrderState(state);
    }

    // private int[][] userStateMapping = new int[][] {
    // //
    // { OrderStateType.NORMAL_TOPAY.getKey(), MyOrderStateType.TOPAY.getKey()},
    // //
    // { OrderStateType.NORMAL_INVALID.getKey(), MyOrderStateType.INVALID.getKey()},
    // //
    // { OrderStateType.NORMAL_CANCEL_ORDER.getKey(), MyOrderStateType.CANCEL_ORDER.getKey()},
    // //
    // { OrderStateType.NORMAL_PAID.getKey(), MyOrderStateType.SHIP.getKey()},
    // // //
    // // { OrderStateType.NORMAL_CANCEL_PAID.getKey(), UserOrderStateType.CANCEL_ORDER.getKey()},
    // // //
    // // { OrderStateType.NORMAL_REFUND_PAID.getKey(), UserOrderStateType.CANCEL_ORDER.getKey()},
    // // //
    // // { OrderStateType.NORMAL_CONFIRM_REFUND_PAID.getKey(), UserOrderStateType.CANCEL_ORDER.getKey()},
    // //
    // { OrderStateType.NORMAL_CONFIRM_ORDER.getKey(), MyOrderStateType.SHIP.getKey()},
    // //
    // { OrderStateType.NORMAL_SHIPPED.getKey(), MyOrderStateType.SIGN.getKey()},
    // //
    // { OrderStateType.NORMAL_SIGN.getKey(), MyOrderStateType.EVALUATION.getKey()},
    // //
    // { OrderStateType.NORMAL_TRADE_FAIL.getKey(), MyOrderStateType.AFTERSALES.getKey()},
    // //
    // { OrderStateType.NORMAL_TRADE_SUCCESS.getKey(), MyOrderStateType.SUCCESS.getKey()},
    // //
    // { OrderStateType.NORMAL_CANCEL_PAID.getKey(), MyOrderStateType.AFTERSALES.getKey()},
    // //
    // };
    @Override
    public Date[] getDatesByLatelyMinutes(int latelyMinutes) {

        return collectOrderDao.getDatesByLatelyMinutes(latelyMinutes);
    }

    @Override
    public CollectOrder get(String orderNumber) {

        return collectOrderDao.get(orderNumber);
    }

    @Override
    public boolean updateConsignee(Long orderId, Date time, QMyConsignee consignee) {

        CollectOrder collectOrder = get(orderId, time);
        collectOrder.setConsignee(consignee.getName());
        collectOrder.setMobile(consignee.getMobile());
        collectOrder.setAddress(StringUtil.nullToEmpty(consignee.getProvince()) + StringUtil.nullToEmpty(consignee.getCity()) + StringUtil.nullToEmpty(consignee.getDistrict()) + consignee.getAddress());
        return collectOrderDao.update(collectOrder);
    }

    @Override
    public List<CollectOrder> list4ExpireState(Date tableDate, int state, int start, int size) {

        return collectOrderDao.list4ExpireState(tableDate, state, start, size);
    }
}
