package com.qcloud.component.orderform.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.my.AfterSaleType;
import com.qcloud.component.orderform.OrderContext;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.component.orderform.QAfterSaleOrder;
import com.qcloud.component.orderform.engine.AfterSaleSelecterService;
import com.qcloud.component.orderform.engine.OrderSelecterService;
import com.qcloud.component.orderform.engine.OrderService;
import com.qcloud.component.orderform.engine.OrderStateService;
import com.qcloud.component.orderform.entity.AfterSaleOrder;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.model.ExchangeOrder;
import com.qcloud.component.orderform.model.RefundOrder;
import com.qcloud.component.orderform.model.ReturnOrder;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.service.CollectOrderService;
import com.qcloud.component.orderform.service.ExchangeOrderService;
import com.qcloud.component.orderform.service.OrderConfigService;
import com.qcloud.component.orderform.service.OrderNumberService;
import com.qcloud.component.orderform.service.RefundOrderService;
import com.qcloud.component.orderform.service.ReturnOrderService;
import com.qcloud.component.orderform.service.SubOrderService;
import com.qcloud.component.orderform.util.OrderTableSplitUtil;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class OrderformClientImpl implements OrderformClient {

    @Autowired
    private OrderService             orderService;

    @Autowired
    private OrderNumberService       orderNumberService;

    @Autowired
    private OrderSelecterService     orderSelecterService;

    @Autowired
    private OrderStateService        orderStateService;

    @Autowired
    private AfterSaleSelecterService afterSaleSelecterService;

    @Autowired
    private ExchangeOrderService     exchangeOrderService;

    @Autowired
    private ReturnOrderService       returnOrderService;

    @Autowired
    private RefundOrderService       refundOrderService;

    @Autowired
    private CollectOrderService      collectOrderService;

    @Autowired
    private SubOrderService          subOrderService;

    @Autowired
    private OrderConfigService       orderConfigService;

    @Override
    public OrderEntity getOrder(Long orderId, Date orderDate) {

        return orderSelecterService.getOrder(orderId, orderDate);
    }

    @Override
    public MerchantOrderEntity getMerchantOrder(Long subOrderId, Date orderDate) {

        return orderSelecterService.getMerchantOrder(subOrderId, orderDate);
    }

    @Override
    public OrderEntity orderNormal(OrderContext context) {

        return orderService.orderNormal(context);
    }

    @Override
    public OrderEntity orderSeckill(QUser user, QUnifiedMerchandise merchandise) {

        return orderService.orderSeckill(user, merchandise);
    }

    @Override
    public String generateOrderNumber() {

        return orderNumberService.generate();
    }

    @Override
    public OrderEntity orderGroupbuys(OrderContext context, String orderNumber, Date orderDate, int paymentMode, double cash, int state) {

        return orderService.orderGroupbuys(context, orderNumber, orderDate, paymentMode, cash, state);
    }

    @Override
    public boolean exchangeOrderState(Long orderId, Date orderDate, int state, Long authentication) {

        return orderStateService.exchangeOrderState(orderId, orderDate, state, authentication);
    }

    @Override
    public boolean exchangeSubOrderState(Long subOrderId, Date orderDate, int state, Long authentication) {

        return orderStateService.exchangeSubOrderState(subOrderId, orderDate, state, authentication);
    }

    @Override
    public QAfterSaleOrder getAfterSaleOrder(long afterSaleId, AfterSaleType type) {

        if (AfterSaleType.REFUND.equals(type)) {
            RefundOrder refundOrder = refundOrderService.get(afterSaleId);
            AssertUtil.assertNotNull(refundOrder, "退款单不存在." + afterSaleId);
            MerchantOrderEntity merchantOrderEntity = orderSelecterService.getMerchantOrder(refundOrder.getSubOrderId(), refundOrder.getOrderDate());
            return afterSaleSelecterService.getRefundOrder(merchantOrderEntity, afterSaleId);
        } else if (AfterSaleType.RETURN.equals(type)) {
            ReturnOrder returnOrder = returnOrderService.get(afterSaleId);
            AssertUtil.assertNotNull(returnOrder, "退货单不存在." + afterSaleId);
            MerchantOrderEntity merchantOrderEntity = orderSelecterService.getMerchantOrder(returnOrder.getSubOrderId(), returnOrder.getOrderDate());
            return afterSaleSelecterService.getReturnOrder(merchantOrderEntity, afterSaleId);
        } else if (AfterSaleType.EXCHANGE.equals(type)) {
            ExchangeOrder exchangeOrder = exchangeOrderService.get(afterSaleId);
            AssertUtil.assertNotNull(exchangeOrder, "换货单不存在." + afterSaleId);
            MerchantOrderEntity merchantOrderEntity = orderSelecterService.getMerchantOrder(exchangeOrder.getSubOrderId(), exchangeOrder.getOrderDate());
            return afterSaleSelecterService.getExchangeOrder(merchantOrderEntity, afterSaleId);
        }
        return null;
    }

    @Override
    public OrderEntity getOrder(String orderNumber) {

        CollectOrder collectOrder = collectOrderService.get(orderNumber);
        AssertUtil.assertNotNull(collectOrder, "订单不存在." + collectOrder);
        return getOrder(collectOrder.getId(), collectOrder.getTime());
    }

    @Override
    public MerchantOrderEntity getMerchantOrder(String orderNumber) {

        SubOrder subOrder = subOrderService.get(orderNumber);
        AssertUtil.assertNotNull(subOrder, "分单不存在." + orderNumber);
        return getMerchantOrder(subOrder.getId(), OrderTableSplitUtil.getTableDate(orderNumber));
    }

    @Override
    public List<QAfterSaleOrder> listByOrder(Long orderId, Date orderDate) {

        OrderEntity orderEntity = getOrder(orderId, orderDate);
        if (orderEntity == null) {
            return new ArrayList<QAfterSaleOrder>();
        }
        List<AfterSaleOrder> list = afterSaleSelecterService.listAfterSaleOrder(orderEntity);
        List<QAfterSaleOrder> voList = new ArrayList<QAfterSaleOrder>();
        for (AfterSaleOrder afterSaleOrder : list) {
            voList.add(afterSaleOrder);
        }
        return voList;
    }

    @Override
    public List<QAfterSaleOrder> listByMerchantOrder(Long subOrderId, Date orderDate) {

        MerchantOrderEntity merchantOrderEntity = getMerchantOrder(subOrderId, orderDate);
        List<AfterSaleOrder> list = afterSaleSelecterService.listAfterSaleOrder(merchantOrderEntity);
        List<QAfterSaleOrder> voList = new ArrayList<QAfterSaleOrder>();
        for (AfterSaleOrder afterSaleOrder : list) {
            voList.add(afterSaleOrder);
        }
        return voList;
    }

    @Override
    public List<QAfterSaleOrder> listByOrder(String orderNumber) {

        OrderEntity orderEntity = getOrder(orderNumber);
        if (orderEntity == null) {
            return new ArrayList<QAfterSaleOrder>();
        }
        List<AfterSaleOrder> list = afterSaleSelecterService.listAfterSaleOrder(orderEntity);
        List<QAfterSaleOrder> voList = new ArrayList<QAfterSaleOrder>();
        for (AfterSaleOrder afterSaleOrder : list) {
            voList.add(afterSaleOrder);
        }
        return voList;
    }

    @Override
    public List<QAfterSaleOrder> listByMerchantOrder(String orderNumber) {

        MerchantOrderEntity merchantOrderEntity = getMerchantOrder(orderNumber);
        List<AfterSaleOrder> list = afterSaleSelecterService.listAfterSaleOrder(merchantOrderEntity);
        List<QAfterSaleOrder> voList = new ArrayList<QAfterSaleOrder>();
        for (AfterSaleOrder afterSaleOrder : list) {
            voList.add(afterSaleOrder);
        }
        return voList;
    }

    @Override
    public int getNormalMerchantOrderState(int state) {

        return orderConfigService.getNormalMerchantOrderState(state);
    }

    @Override
    public int getNormalPersonalOrderState(int state) {

        return orderConfigService.getNormalPersonalOrderState(state);
    }

    @Override
    public boolean modifyMerchantOrder(Long subOrderId, Date orderDate, Map<QUnifiedMerchandise, Integer> addMerchandiseMap, Map<QUnifiedMerchandise, Integer> deleteMerchandiseMap) {

        return orderService.modifyMerchantOrder(orderSelecterService.getMerchantOrder(subOrderId, orderDate), addMerchandiseMap, deleteMerchandiseMap);
    }

    @Override
    public String getNormalPersonalOrderStateDesc(int state) {

        return orderConfigService.getNormalPersonalOrderStateDesc(state);
    }
}