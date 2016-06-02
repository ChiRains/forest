package com.qcloud.component.orderform.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.orderform.dao.SubOrderDao;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.model.query.SubOrderQuery;
import com.qcloud.component.orderform.service.OrderConfigService;
import com.qcloud.component.orderform.service.OrderObserverService;
import com.qcloud.component.orderform.service.SubOrderService;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class SubOrderServiceImpl implements SubOrderService {

    @Autowired
    private SubOrderDao          subOrderDao;

    @Autowired
    private AutoIdGenerator      autoIdGenerator;

    // @Autowired
    // private RecordStateTimeService recordStateTimeService;
    private static final String  ID_KEY = "orderform_sub_order";

    // private static final String NOTYFI_TEMPLATE = "personalcenter-user-sms-pay-notifyTemplate";
    // @Autowired
    // private OutdatedSellercenterClient outdatedSellercenterClient;
    @Autowired
    private SellercenterClient   sellercenterClient;

    // @Autowired
    // private ParameterClient parameterClient;
    //
    // @Autowired
    // private PersonalcenterClient personalcenterClient;
    //
    // @Autowired
    // private MyClient myClient;
    //
    // @Autowired
    // private OrderDiscountService orderDiscountService;
    //
    // @Autowired
    // private CollectOrderService collectOrderService;
    @Autowired
    private OrderConfigService   orderConfigService;

    @Autowired
    private OrderObserverService orderObserverService;

    @Override
    public boolean add(MerchantOrderEntity merchantOrder, Date time) {

        SubOrder subOrder = merchantOrder.getSubOrder();
        long id = autoIdGenerator.get(ID_KEY);
        subOrder.setId(id);
        boolean result = subOrderDao.add(subOrder, time);
        AssertUtil.assertTrue(result, "下订单失败.");
        orderObserverService.doNotify(merchantOrder, merchantOrder.getState());
        // 添加卖家记录
        return result;
    }

    @Override
    public SubOrder get(Long id, Date time) {

        return subOrderDao.get(id, time);
    }

    public boolean update(SubOrder subOrder, Date time) {

        return subOrderDao.update(subOrder, time);
//        AssertUtil.assertTrue(result, "更新 子订单失败.");
//        // if (result) {
//        // result = sellercenterClient.updateOrderFormState(subOrder.getMerchantId(), subOrder.getId(), getMerchantOrderFormState(subOrder.getState()));
//        // CollectOrder collectOrder = collectOrderDao.get(subOrder.getOrderId(), time);
//        // myClient.updateMyOrderFormState(collectOrder.getUserId(), subOrder.getOrderId(), subOrder.getId(), collectOrderService.getUserOrderFormState(subOrder.getState()));
//        // }
//        // // 付款提醒
//        // if (subOrder.getState() == OrderStateType.NORMAL_PAID.getKey()) {
//        // CollectOrder collectOrder = collectOrderDao.get(subOrder.getOrderId(), time);
//        // String orderNumber = collectOrder.getOrderNumber();
//        // // 发短信
//        // String notifyTemplate = parameterClient.get(NOTYFI_TEMPLATE);
//        // if (StringUtils.isNotEmpty(notifyTemplate)) {
//        // notifyTemplate = notifyTemplate.replaceAll("\\{orderNumber\\}", orderNumber);
//        // outdatedSellercenterClient.sendSmsToStore(subOrder.getStoreId(), notifyTemplate);
//        // }
//        // // 发站内信
//        // sellercenterClient.sendMsgToMerchant(subOrder.getMerchantId(), SellerMessageType.PAY_NOTIFY, "买家付款通知", "您有一笔交易已经付款 ,订单号为:" + subOrder.getOrderNumber() + " , " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        // sellercenterClient.sendMsgToStore(subOrder.getStoreId(), SellerMessageType.PAY_NOTIFY, "买家付款通知", "您有一笔交易已经付款 ,订单号为:" + subOrder.getOrderNumber() + " , " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        // }
//        // if ((OrderStateType.NORMAL_CANCEL_ORDER.getKey() == subOrder.getState() || OrderStateType.NORMAL_INVALID.getKey() == subOrder.getState()) && subOrder.getCoupon() > 0) {
//        // CollectOrder collectOrder = collectOrderDao.get(subOrder.getOrderId(), time);
//        // List<OrderDiscount> orderDiscountList = orderDiscountService.listBySubOrder(subOrder.getOrderId());
//        // for (OrderDiscount orderDiscount : orderDiscountList) {
//        // if (DiscountType.COUPON.getKey() == orderDiscount.getType()) {
//        // myClient.cancleUseMyCoupon(collectOrder.getUserId(), orderDiscount.getDiscountId());
//        // }
//        // }
//        // }
//        return result;
    }

    @Override
    public boolean updateStore(Long id, Date time, Long storeId) {

        SubOrder subOrder = get(id, time);
        subOrder.setStoreId(storeId);
        sellercenterClient.updateOrderFormStore(subOrder.getMerchantId(), subOrder.getId(), storeId);
        boolean result = update(subOrder, time);
        AssertUtil.assertTrue(result, "修改发货门店失败." + id);
        return result;
    }

    @Override
    public boolean updateState(MerchantOrderEntity merchantOrder, int state) {

        SubOrder subOrder = get(merchantOrder.getId(), merchantOrder.getOrder().getOrderDate());
        if (subOrder.getState() != state) {
            orderObserverService.doNotify(merchantOrder, state);
        }
        subOrder.setState(state);
        return update(subOrder, merchantOrder.getOrder().getOrderDate());
    }

    @Override
    public boolean delete(Long id) {

        return subOrderDao.delete(id);
    }

    @Override
    public Page<SubOrder> page(SubOrderQuery query, int start, int count) {

        return subOrderDao.page(query, start, count);
    }

    @Override
    public List<SubOrder> listByCollectOrder(Long collectOrderId, Date time) {

        return subOrderDao.listByCollectOrder(collectOrderId, time);
    }

    public int getMerchantOrderFormState(int state) {

        return orderConfigService.getNormalMerchantOrderState(state);
        // for (int index = 0; index < merchantStateMapping.length; index++) {
        // if (merchantStateMapping[index][0] == state) {
        // return merchantStateMapping[index][1];
        // }
        // }
        // throw new OrderformException("子订单状态映射有误." + state);
    }

    // private int[][] merchantStateMapping = new int[][] {
    // //
    // { OrderStateType.NORMAL_TOPAY.getKey(), MerchantOrderStateType.TOPAY.getKey()},
    // //
    // { OrderStateType.NORMAL_INVALID.getKey(), MerchantOrderStateType.INVALID.getKey()},
    // //
    // { OrderStateType.NORMAL_CANCEL_ORDER.getKey(), MerchantOrderStateType.CANCEL_ORDER.getKey()},
    // //
    // { OrderStateType.NORMAL_PAID.getKey(), MerchantOrderStateType.CONFIRM.getKey()},
    // //
    // { OrderStateType.NORMAL_CANCEL_PAID.getKey(), MerchantOrderStateType.AFTER_SALE.getKey()},
    // //
    // { OrderStateType.NORMAL_CONFIRM_ORDER.getKey(), MerchantOrderStateType.SHIP.getKey()},
    // //
    // { OrderStateType.NORMAL_SHIPPED.getKey(), MerchantOrderStateType.SIGN.getKey()},
    // //
    // { OrderStateType.NORMAL_SIGN.getKey(), MerchantOrderStateType.SIGNED.getKey()},
    // //
    // { OrderStateType.NORMAL_TRADE_SUCCESS.getKey(), MerchantOrderStateType.SUCCESS.getKey()},
    // //
    // { OrderStateType.NORMAL_TRADE_FAIL.getKey(), MerchantOrderStateType.AFTER_SALE.getKey()}
    // //
    // };
    @Override
    public SubOrder get(String orderNumber) {

        return subOrderDao.get(orderNumber);
    }

    @Override
    public List<SubOrder> listByMerchantAndDay(Long merchantId, Date time) {

        return subOrderDao.listByMerchantAndDay(merchantId, time);
    }
}
