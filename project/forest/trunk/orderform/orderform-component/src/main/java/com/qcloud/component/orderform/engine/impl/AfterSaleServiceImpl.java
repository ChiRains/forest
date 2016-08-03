package com.qcloud.component.orderform.engine.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.orderform.engine.AfterSaleSelecterService;
import com.qcloud.component.orderform.engine.AfterSaleService;
import com.qcloud.component.orderform.entity.AfterSaleDetail;
import com.qcloud.component.orderform.entity.AfterSaleItem;
import com.qcloud.component.orderform.entity.AfterSaleOrderItem;
import com.qcloud.component.orderform.entity.ExchangeAfterSaleOrder;
import com.qcloud.component.orderform.entity.ExchangeAfterSaleOrderItem;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.entity.OrderItemDetailEntity;
import com.qcloud.component.orderform.entity.OrderItemEntity;
import com.qcloud.component.orderform.entity.RefundAfterSaleOrder;
import com.qcloud.component.orderform.entity.RefundAfterSaleOrderItem;
import com.qcloud.component.orderform.entity.ReturnAfterSaleOrder;
import com.qcloud.component.orderform.entity.ReturnAfterSaleOrderItem;
import com.qcloud.component.orderform.model.ExchangeOrder;
import com.qcloud.component.orderform.model.ExchangeOrderItemDetail;
import com.qcloud.component.orderform.model.RefundOrder;
import com.qcloud.component.orderform.model.RefundOrderItem;
import com.qcloud.component.orderform.model.ReturnOrder;
import com.qcloud.component.orderform.model.ReturnOrderItem;
import com.qcloud.component.orderform.service.ExchangeOrderItemDetailService;
import com.qcloud.component.orderform.service.ExchangeOrderService;
import com.qcloud.component.orderform.service.OrderConfigService;
import com.qcloud.component.orderform.service.OrderNumberService;
import com.qcloud.component.orderform.service.OrderObserverService;
import com.qcloud.component.orderform.service.RefundOrderItemService;
import com.qcloud.component.orderform.service.RefundOrderService;
import com.qcloud.component.orderform.service.ReturnOrderItemService;
import com.qcloud.component.orderform.service.ReturnOrderService;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class AfterSaleServiceImpl implements AfterSaleService {

    @Autowired
    private ExchangeOrderService           exchangeOrderService;

    @Autowired
    private ExchangeOrderItemDetailService exchangeOrderItemDetailService;

    @Autowired
    private ReturnOrderService             returnOrderService;

    @Autowired
    private ReturnOrderItemService         returnOrderItemService;

    @Autowired
    private RefundOrderService             refundOrderService;

    @Autowired
    private RefundOrderItemService         refundOrderItemService;

    @Autowired
    private OrderNumberService             orderNumberService;

    @Autowired
    private SellercenterClient             sellercenterClient;

    @Autowired
    private OrderConfigService             orderConfigService;

    @Autowired
    private OrderObserverService           orderObserverService;

    @Autowired
    private AfterSaleSelecterService       afterSaleSelecterService;

    @Transactional
    public boolean reApplyReturnOrder(ReturnAfterSaleOrder returnAfterSaleOrder) {

        ReturnOrder returnOrder = returnAfterSaleOrder.getReturnOrder();
        returnOrder.setState(orderConfigService.getReturnInitOrderState());
        returnOrder.setReturnNumber(orderNumberService.generate());
        returnOrder.setSum(0.0);
        returnOrder.setLogisticsCompany("");
        returnOrder.setLogisticsNumber("");
        returnOrderService.add(returnOrder);
        List<AfterSaleOrderItem> list = returnAfterSaleOrder.getList();
        for (AfterSaleOrderItem afterSaleOrderItem : list) {
            ReturnAfterSaleOrderItem returnAfterSaleOrderItem = (ReturnAfterSaleOrderItem) afterSaleOrderItem;
            ReturnOrderItem returnOrderItem = returnAfterSaleOrderItem.getReturnOrderItem();
            returnOrderItem.setState(orderConfigService.getReturnInitOrderState());
            returnOrderItem.setReturnId(returnOrder.getId());
            returnOrderItemService.add(returnOrderItem);
        }
        ReturnAfterSaleOrder afterSaleOrder = afterSaleSelecterService.getReturnOrder(returnAfterSaleOrder.getMerchantOrder(), returnOrder.getId());
        orderObserverService.doNotify(afterSaleOrder, returnOrder.getState());
        // sellercenterClient.updateOrderFormState(returnOrder.getMerchantId(), returnOrder.getSubOrderId(), MerchantOrderStateType.RETURN.getKey());
        // myClient.updateMyOrderFormState(returnOrder.getUserId(), returnOrder.getOrderId(), MyOrderStateType.AFTERSALES.getKey());
        return true;
    }

    @Transactional
    public boolean reApplyRefundOrder(RefundAfterSaleOrder refundAfterSaleOrder) {

        RefundOrder refundOrder = refundAfterSaleOrder.getRefundOrder();
        refundOrder.setState(orderConfigService.getRefundInitOrderState());
        refundOrder.setRefundNumber(orderNumberService.generate());
        refundOrder.setSum(0.0);
        refundOrderService.add(refundOrder);
        List<AfterSaleOrderItem> list = refundAfterSaleOrder.getList();
        for (AfterSaleOrderItem afterSaleOrderItem : list) {
            RefundAfterSaleOrderItem refundAfterSaleOrderItem = (RefundAfterSaleOrderItem) afterSaleOrderItem;
            RefundOrderItem refundOrderItem = refundAfterSaleOrderItem.getRefundOrderItem();
            refundOrderItem.setState(orderConfigService.getRefundInitOrderState());
            refundOrderItem.setRefundId(refundOrder.getId());
            refundOrderItemService.add(refundOrderItem);
        }
        RefundAfterSaleOrder afterSaleOrder = afterSaleSelecterService.getRefundOrder(refundAfterSaleOrder.getMerchantOrder(), refundOrder.getId());
        orderObserverService.doNotify(afterSaleOrder, refundOrder.getState());
        // sellercenterClient.updateOrderFormState(refundOrder.getMerchantId(), refundOrder.getSubOrderId(), MerchantOrderStateType.REFUND.getKey());
        // myClient.updateMyOrderFormState(refundOrder.getUserId(), refundOrder.getOrderId(), MyOrderStateType.AFTERSALES.getKey());
        return true;
    }

    @Transactional
    public boolean reApplyExchangeOrder(ExchangeAfterSaleOrder exchangeAfterSaleOrder) {

        ExchangeOrder exchangeOrder = exchangeAfterSaleOrder.getExchangeOrder();
        exchangeOrder.setState(orderConfigService.getExchangeInitOrderState());
        exchangeOrder.setExchangeNumber(orderNumberService.generate());
        exchangeOrder.setUserLogisticsCompany("");
        exchangeOrder.setUserLogisticsNumber("");
        exchangeOrder.setMerchantLogisticsCompany("");
        exchangeOrder.setMerchantLogisticsNumber("");
        exchangeOrderService.add(exchangeOrder);
        List<AfterSaleOrderItem> list = exchangeAfterSaleOrder.getList();
        for (AfterSaleOrderItem afterSaleOrderItem : list) {
            ExchangeAfterSaleOrderItem exchangeAfterSaleOrderItem = (ExchangeAfterSaleOrderItem) afterSaleOrderItem;
            ExchangeOrderItemDetail exchangeOrderItemDetail = exchangeAfterSaleOrderItem.getExchangeOrderItemDetail();
            exchangeOrderItemDetail.setState(orderConfigService.getExchangeInitOrderState());
            exchangeOrderItemDetail.setExchangeId(exchangeOrder.getId());
            exchangeOrderItemDetailService.add(exchangeOrderItemDetail);
        }
        ExchangeAfterSaleOrder afterSaleOrder = afterSaleSelecterService.getExchangeOrder(exchangeAfterSaleOrder.getMerchantOrder(), exchangeOrder.getId());
        orderObserverService.doNotify(afterSaleOrder, exchangeOrder.getState());
        // sellercenterClient.updateOrderFormState(exchangeOrder.getMerchantId(), exchangeOrder.getSubOrderId(), MerchantOrderStateType.EXCHANGE.getKey());
        // myClient.updateMyOrderFormState(exchangeOrder.getUserId(), exchangeOrder.getOrderId(), MyOrderStateType.AFTERSALES.getKey());
        return true;
    }

    @Transactional
    public boolean applyExchange(OrderEntity orderEntity, List<OrderItemDetailEntity> list, String explain, String reason) {

        AssertUtil.assertNotEmpty(list, "换货商品列表不能为空.");
        List<List<OrderItemDetailEntity>> spitList = spitOrderItemDetail(list);
        for (List<OrderItemDetailEntity> itemList : spitList) {
            MerchantOrderEntity merchantOrderEntity = itemList.get(0).getMerchantOrder();
            Date now = new Date();
            Long exchangeOrderId = applyExchangeOrder(merchantOrderEntity, now, explain, reason);
            //
            for (OrderItemDetailEntity orderItemDetailEntity : itemList) {
                AfterSaleDetail afterSale = new AfterSaleDetail();
                afterSale.setOrderItemDetail(orderItemDetailEntity);
                afterSale.setExplain(explain);
                afterSale.setReason(reason);
                afterSale.setNumber(orderItemDetailEntity.getNumber());
                applyExchangeOrderItemDetail(afterSale, now, exchangeOrderId);
            }
            ExchangeAfterSaleOrder afterSaleOrder = afterSaleSelecterService.getExchangeOrder(merchantOrderEntity, exchangeOrderId);
            orderObserverService.doNotify(afterSaleOrder, afterSaleOrder.getState());
            // sellercenterClient.updateOrderFormState(merchantOrderEntity.getMerchantId(), merchantOrderEntity.getId(), MerchantOrderStateType.EXCHANGE.getKey());
        }
        // myClient.updateMyOrderFormState(orderEntity.getUserId(), orderEntity.getId(), MyOrderStateType.AFTERSALES.getKey());
        return true;
    }

    @Transactional
    public boolean applyReturn(OrderEntity orderEntity, List<OrderItemEntity> list, String explain, String reason) {

        AssertUtil.assertNotEmpty(list, "退货商品列表不能为空.");
        List<List<OrderItemEntity>> spitList = spitOrderItem(list);
        for (List<OrderItemEntity> itemList : spitList) {
            MerchantOrderEntity merchantOrderEntity = itemList.get(0).getMerchantOrder();
            Date now = new Date();
            Long returnOrderId = applyReturnOrder(merchantOrderEntity, now, explain, reason);
            for (OrderItemEntity orderItem : itemList) {
                AfterSaleItem afterSale = new AfterSaleItem();
                afterSale.setOrderItem(orderItem);
                afterSale.setExplain(explain);
                afterSale.setReason(reason);
                afterSale.setNumber(orderItem.getNumber());
                applyReturnOrderItem(afterSale, now, returnOrderId);
            }
            ReturnAfterSaleOrder afterSaleOrder = afterSaleSelecterService.getReturnOrder(merchantOrderEntity, returnOrderId);
            orderObserverService.doNotify(afterSaleOrder, afterSaleOrder.getState());
            // sellercenterClient.updateOrderFormState(merchantOrderEntity.getMerchantId(), merchantOrderEntity.getId(), MerchantOrderStateType.RETURN.getKey());
        }
        // myClient.updateMyOrderFormState(orderEntity.getUserId(), orderEntity.getId(), MyOrderStateType.AFTERSALES.getKey());
        return true;
    }

    @Transactional
    public List<Long> applyRefund(OrderEntity orderEntity, List<OrderItemEntity> list, String explain, String reason, Double afterSaleSum) {

        AssertUtil.assertNotEmpty(list, "退款商品列表不能为空.");
        List<List<OrderItemEntity>> spitList = spitOrderItem(list);
        List<Long> refundList = new ArrayList<Long>();
        for (List<OrderItemEntity> itemList : spitList) {
            MerchantOrderEntity merchantOrderEntity = itemList.get(0).getMerchantOrder();
            Date now = new Date();
            Long refundOrderId = applyRefundOrder(merchantOrderEntity, now, explain, reason, afterSaleSum);
            for (OrderItemEntity orderItem : itemList) {
                AfterSaleItem afterSale = new AfterSaleItem();
                afterSale.setOrderItem(orderItem);
                afterSale.setExplain(explain);
                afterSale.setReason(reason);
                afterSale.setNumber(orderItem.getNumber());
                applyRefundOrderItemDetail(afterSale, now, refundOrderId);
            }
            RefundAfterSaleOrder refundAfterSaleOrder = afterSaleSelecterService.getRefundOrder(merchantOrderEntity, refundOrderId);
            orderObserverService.doNotify(refundAfterSaleOrder, refundAfterSaleOrder.getState());
            refundList.add(refundOrderId);
            // sellercenterClient.updateOrderFormState(merchantOrderEntity.getMerchantId(), merchantOrderEntity.getId(), MerchantOrderStateType.REFUND.getKey());
        }
        // myClient.updateMyOrderFormState(orderEntity.getUserId(), orderEntity.getId(), MyOrderStateType.AFTERSALES.getKey());
        return refundList;
    }

    @Transactional
    public boolean applyExchange(OrderEntity orderEntity, List<AfterSaleDetail> list) {

        AssertUtil.assertNotEmpty(list, "换货商品列表不能为空.");
        List<List<AfterSaleDetail>> spitList = spitAfterSaleDetail(list);
        for (List<AfterSaleDetail> itemList : spitList) {
            MerchantOrderEntity merchantOrderEntity = itemList.get(0).getOrderItemDetail().getMerchantOrder();
            Date now = new Date();
            //
            Long exchangeOrderId = applyExchangeOrder(merchantOrderEntity, now, itemList.get(0).getExplain(), itemList.get(0).getReason());
            for (AfterSaleDetail afterSale : itemList) {
                applyExchangeOrderItemDetail(afterSale, now, exchangeOrderId);
            }
            ExchangeAfterSaleOrder afterSaleOrder = afterSaleSelecterService.getExchangeOrder(merchantOrderEntity, exchangeOrderId);
            orderObserverService.doNotify(afterSaleOrder, afterSaleOrder.getState());
            // sellercenterClient.updateOrderFormState(merchantOrderEntity.getMerchantId(), merchantOrderEntity.getId(), MerchantOrderStateType.EXCHANGE.getKey());
        }
        // myClient.updateMyOrderFormState(orderEntity.getUserId(), orderEntity.getId(), MyOrderStateType.AFTERSALES.getKey());
        return true;
    }

    @Transactional
    public boolean applyReturn(OrderEntity orderEntity, List<AfterSaleItem> list) {

        AssertUtil.assertNotEmpty(list, "退货商品列表不能为空.");
        List<List<AfterSaleItem>> spitList = spitAfterSaleItem(list);
        for (List<AfterSaleItem> itemList : spitList) {
            MerchantOrderEntity merchantOrderEntity = itemList.get(0).getOrderItem().getMerchantOrder();
            Date now = new Date();
            Long returnOrderId = applyReturnOrder(merchantOrderEntity, now, itemList.get(0).getExplain(), itemList.get(0).getReason());
            for (AfterSaleItem afterSale : itemList) {
                applyReturnOrderItem(afterSale, now, returnOrderId);
            }
            ReturnAfterSaleOrder afterSaleOrder = afterSaleSelecterService.getReturnOrder(merchantOrderEntity, returnOrderId);
            orderObserverService.doNotify(afterSaleOrder, afterSaleOrder.getState());
            // sellercenterClient.updateOrderFormState(merchantOrderEntity.getMerchantId(), merchantOrderEntity.getId(), MerchantOrderStateType.RETURN.getKey());
        }
        // myClient.updateMyOrderFormState(orderEntity.getUserId(), orderEntity.getId(), MyOrderStateType.AFTERSALES.getKey());
        return true;
    }

    @Transactional
    public boolean applyRefund(OrderEntity orderEntity, List<AfterSaleItem> list, Double afterSaleSum) {

        AssertUtil.assertNotEmpty(list, "退款商品列表不能为空.");
        List<List<AfterSaleItem>> spitList = spitAfterSaleItem(list);
        for (List<AfterSaleItem> itemList : spitList) {
            MerchantOrderEntity merchantOrderEntity = itemList.get(0).getOrderItem().getMerchantOrder();
            Date now = new Date();
            Long returnOrderId = applyRefundOrder(merchantOrderEntity, now, "", "", afterSaleSum);
            for (AfterSaleItem afterSale : itemList) {
                applyRefundOrderItemDetail(afterSale, now, returnOrderId);
            }
            RefundAfterSaleOrder refundAfterSaleOrder = afterSaleSelecterService.getRefundOrder(merchantOrderEntity, returnOrderId);
            orderObserverService.doNotify(refundAfterSaleOrder, refundAfterSaleOrder.getState());
            // sellercenterClient.updateOrderFormState(merchantOrderEntity.getMerchantId(), merchantOrderEntity.getId(), MerchantOrderStateType.REFUND.getKey());
        }
        // myClient.updateMyOrderFormState(orderEntity.getUserId(), orderEntity.getId(), MyOrderStateType.AFTERSALES.getKey());
        return true;
    }

    private List<List<AfterSaleDetail>> spitAfterSaleDetail(List<AfterSaleDetail> list) {

        Map<Long, List<AfterSaleDetail>> map = new HashMap<Long, List<AfterSaleDetail>>();
        for (AfterSaleDetail afterSale : list) {
            Long subOrderId = afterSale.getOrderItemDetail().getMerchantOrder().getId();
            List<AfterSaleDetail> itemList = map.get(subOrderId);
            if (itemList == null) {
                itemList = new ArrayList<AfterSaleDetail>();
                map.put(subOrderId, itemList);
            }
            itemList.add(afterSale);
        }
        return new ArrayList<List<AfterSaleDetail>>(map.values());
    }

    private List<List<AfterSaleItem>> spitAfterSaleItem(List<AfterSaleItem> list) {

        Map<Long, List<AfterSaleItem>> map = new HashMap<Long, List<AfterSaleItem>>();
        for (AfterSaleItem afterSale : list) {
            Long subOrderId = afterSale.getOrderItem().getMerchantOrder().getId();
            List<AfterSaleItem> itemList = map.get(subOrderId);
            if (itemList == null) {
                itemList = new ArrayList<AfterSaleItem>();
                map.put(subOrderId, itemList);
            }
            itemList.add(afterSale);
        }
        return new ArrayList<List<AfterSaleItem>>(map.values());
    }

    private List<List<OrderItemDetailEntity>> spitOrderItemDetail(List<OrderItemDetailEntity> list) {

        Map<Long, List<OrderItemDetailEntity>> map = new HashMap<Long, List<OrderItemDetailEntity>>();
        for (OrderItemDetailEntity orderItemDetailEntity : list) {
            Long subOrderId = orderItemDetailEntity.getMerchantOrder().getId();
            List<OrderItemDetailEntity> itemList = map.get(subOrderId);
            if (itemList == null) {
                itemList = new ArrayList<OrderItemDetailEntity>();
                map.put(subOrderId, itemList);
            }
            itemList.add(orderItemDetailEntity);
        }
        return new ArrayList<List<OrderItemDetailEntity>>(map.values());
    }

    private List<List<OrderItemEntity>> spitOrderItem(List<OrderItemEntity> list) {

        Map<Long, List<OrderItemEntity>> map = new HashMap<Long, List<OrderItemEntity>>();
        for (OrderItemEntity orderItemEntity : list) {
            Long subOrderId = orderItemEntity.getMerchantOrder().getId();
            List<OrderItemEntity> itemList = map.get(subOrderId);
            if (itemList == null) {
                itemList = new ArrayList<OrderItemEntity>();
                map.put(subOrderId, itemList);
            }
            itemList.add(orderItemEntity);
        }
        return new ArrayList<List<OrderItemEntity>>(map.values());
    }

    private Long applyExchangeOrder(MerchantOrderEntity merchantOrderEntity, Date date, String explain, String reason) {

        OrderEntity orderEntity = merchantOrderEntity.getOrder();
        ExchangeOrder exchangeOrder = new ExchangeOrder();
        exchangeOrder.setOrderId(orderEntity.getId());
        exchangeOrder.setOrderDate(orderEntity.getOrderDate());
        exchangeOrder.setOrderNumber(orderEntity.getOrderNumber());
        exchangeOrder.setSubOrderId(merchantOrderEntity.getId());
        exchangeOrder.setExplain(explain);
        exchangeOrder.setReason(reason);
        exchangeOrder.setMerchantId(merchantOrderEntity.getMerchantId());
        exchangeOrder.setStoreId(merchantOrderEntity.getStoreId());
        exchangeOrder.setState(orderConfigService.getExchangeInitOrderState());
        exchangeOrder.setTime(date);
        exchangeOrder.setUserId(orderEntity.getUserId());
        exchangeOrder.setExchangeNumber(orderNumberService.generate());
        exchangeOrderService.add(exchangeOrder);
        // 买家发短信给门店
        String exchangeNumber = exchangeOrder.getExchangeNumber();
        Map<String, String> map = new HashMap<String, String>();
        map.put("${orderNumber}", exchangeNumber);
        sellercenterClient.sendSmsToStore(merchantOrderEntity.getStoreId(), "personalcenter-user-sms-exchange-notifyTemplate", map);
        return exchangeOrder.getId();
    }

    private boolean applyExchangeOrderItemDetail(AfterSaleDetail afterSale, Date date, Long exchangeOrderId) {

        OrderItemDetailEntity orderItemDetailEntity = afterSale.getOrderItemDetail();
        MerchantOrderEntity merchantOrderEntity = orderItemDetailEntity.getMerchantOrder();
        OrderItemEntity orderItemEntity = orderItemDetailEntity.getOrderItem();
        OrderEntity orderEntity = orderItemDetailEntity.getOrder();
        //
        ExchangeOrderItemDetail exchangeOrderItemDetail = new ExchangeOrderItemDetail();
        exchangeOrderItemDetail.setExchangeId(exchangeOrderId);
        //
        exchangeOrderItemDetail.setOrderId(orderEntity.getId());
        exchangeOrderItemDetail.setSubOrderId(merchantOrderEntity.getId());
        exchangeOrderItemDetail.setOrderItemId(orderItemEntity.getId());
        exchangeOrderItemDetail.setOrderItemDetailId(orderItemDetailEntity.getId());
        exchangeOrderItemDetail.setExplain(afterSale.getExplain());
        exchangeOrderItemDetail.setReason(afterSale.getReason());
        exchangeOrderItemDetail.setState(orderConfigService.getExchangeInitOrderState());
        exchangeOrderItemDetail.setTime(date);
        exchangeOrderItemDetail.setNumber(afterSale.getNumber());
        return exchangeOrderItemDetailService.add(exchangeOrderItemDetail);
    }

    private Long applyRefundOrder(MerchantOrderEntity merchantOrderEntity, Date date, String explain, String reason, Double afterSaleSum) {

        OrderEntity orderEntity = merchantOrderEntity.getOrder();
        RefundOrder refundOrder = new RefundOrder();
        refundOrder.setOrderId(orderEntity.getId());
        refundOrder.setOrderDate(orderEntity.getOrderDate());
        refundOrder.setOrderNumber(orderEntity.getOrderNumber());
        refundOrder.setSubOrderId(merchantOrderEntity.getId());
        refundOrder.setMerchantId(merchantOrderEntity.getMerchantId());
        refundOrder.setStoreId(merchantOrderEntity.getStoreId());
        refundOrder.setExplain(explain);
        refundOrder.setReason(reason);
        refundOrder.setSum(0.0);
        refundOrder.setTime(date);
        refundOrder.setUserId(orderEntity.getUserId());
        refundOrder.setState(orderConfigService.getRefundInitOrderState());
        refundOrder.setRefundNumber(orderNumberService.generate());
        refundOrder.setAfterSaleSum(afterSaleSum);
        refundOrderService.add(refundOrder);
        // 发短信给门店
        String refundNumber = refundOrder.getRefundNumber();
        Map<String, String> map = new HashMap<String, String>();
        map.put("${orderNumber}", refundNumber);
        sellercenterClient.sendSmsToStore(merchantOrderEntity.getStoreId(), "personalcenter-user-sms-refund-notifyTemplate", map);
        return refundOrder.getId();
    }

    private boolean applyRefundOrderItemDetail(AfterSaleItem afterSale, Date date, Long refundOrderId) {

        OrderItemEntity orderItemEntity = afterSale.getOrderItem();
        MerchantOrderEntity merchantOrderEntity = orderItemEntity.getMerchantOrder();
        OrderEntity orderEntity = orderItemEntity.getOrder();
        //
        RefundOrderItem refundOrderItem = new RefundOrderItem();
        refundOrderItem.setRefundId(refundOrderId);
        //
        refundOrderItem.setOrderId(orderEntity.getId());
        refundOrderItem.setSubOrderId(merchantOrderEntity.getId());
        refundOrderItem.setOrderItemId(orderItemEntity.getId());
        //
        refundOrderItem.setSum(0.0);
        refundOrderItem.setExplain(afterSale.getExplain());
        refundOrderItem.setReason(afterSale.getReason());
        refundOrderItem.setNumber(afterSale.getNumber());
        refundOrderItem.setState(orderConfigService.getRefundInitOrderState());
        refundOrderItem.setTime(date);
        return refundOrderItemService.add(refundOrderItem);
    }

    // 退货单
    private Long applyReturnOrder(MerchantOrderEntity merchantOrderEntity, Date date, String explain, String reason) {

        OrderEntity orderEntity = merchantOrderEntity.getOrder();
        ReturnOrder returnOrder = new ReturnOrder();
        returnOrder.setOrderId(orderEntity.getId());
        returnOrder.setOrderDate(orderEntity.getOrderDate());
        returnOrder.setOrderNumber(orderEntity.getOrderNumber());
        returnOrder.setSubOrderId(merchantOrderEntity.getId());
        returnOrder.setMerchantId(merchantOrderEntity.getMerchantId());
        returnOrder.setStoreId(merchantOrderEntity.getStoreId());
        returnOrder.setExplain(explain);
        returnOrder.setReason(reason);
        returnOrder.setSum(0.0);
        returnOrder.setTime(date);
        returnOrder.setUserId(orderEntity.getUserId());
        returnOrder.setState(orderConfigService.getReturnInitOrderState());
        returnOrder.setReturnNumber(orderNumberService.generate());
        returnOrderService.add(returnOrder);
        // 发短信
        String returnNumber = returnOrder.getReturnNumber();
        Map<String, String> map = new HashMap<String, String>();
        map.put("${orderNumber}", returnNumber);
        sellercenterClient.sendSmsToStore(merchantOrderEntity.getStoreId(), "personalcenter-user-sms-return-notifyTemplate", map);
        return returnOrder.getId();
    }

    // 退货单项
    private boolean applyReturnOrderItem(AfterSaleItem afterSale, Date date, Long returnOrderId) {

        OrderItemEntity orderItemEntity = afterSale.getOrderItem();
        MerchantOrderEntity merchantOrderEntity = orderItemEntity.getMerchantOrder();
        OrderEntity orderEntity = orderItemEntity.getOrder();
        //
        ReturnOrderItem returnOrderItem = new ReturnOrderItem();
        returnOrderItem.setReturnId(returnOrderId);
        //
        returnOrderItem.setOrderId(orderEntity.getId());
        returnOrderItem.setSubOrderId(merchantOrderEntity.getId());
        returnOrderItem.setOrderItemId(orderItemEntity.getId());
        //
        returnOrderItem.setSum(0.0);
        returnOrderItem.setExplain(afterSale.getExplain());
        returnOrderItem.setReason(afterSale.getReason());
        returnOrderItem.setNumber(afterSale.getNumber());
        returnOrderItem.setState(orderConfigService.getReturnInitOrderState());
        returnOrderItem.setTime(date);
        return returnOrderItemService.add(returnOrderItem);
    }

    // 退货单项详细
    private boolean applyReturnOrderItemDetail(AfterSaleDetail afterSale, Date date, Long returnOrderId) {

        OrderItemDetailEntity orderItemEntity = afterSale.getOrderItemDetail();
        MerchantOrderEntity merchantOrderEntity = orderItemEntity.getMerchantOrder();
        OrderEntity orderEntity = orderItemEntity.getOrder();
        //
        ReturnOrderItem returnOrderItem = new ReturnOrderItem();
        returnOrderItem.setReturnId(returnOrderId);
        //
        returnOrderItem.setOrderId(orderEntity.getId());
        returnOrderItem.setSubOrderId(merchantOrderEntity.getId());
        returnOrderItem.setOrderItemId(orderItemEntity.getId());
        //
        returnOrderItem.setSum(0.0);
        returnOrderItem.setExplain(afterSale.getExplain());
        returnOrderItem.setReason(afterSale.getReason());
        returnOrderItem.setNumber(afterSale.getNumber());
        returnOrderItem.setState(orderConfigService.getReturnInitOrderState());
        returnOrderItem.setTime(date);
        return returnOrderItemService.add(returnOrderItem);
    }

    @Transactional
    @Override
    public boolean applyReturnDetail(OrderEntity orderEntity, List<AfterSaleDetail> list) {

        AssertUtil.assertNotEmpty(list, "退货商品列表不能为空.");
        Map<OrderItemEntity, List<AfterSaleDetail>> spitMap = new HashMap<OrderItemEntity, List<AfterSaleDetail>>();
        Set<OrderItemEntity> orderItemSet = new HashSet<OrderItemEntity>();
        for (AfterSaleDetail afterSaleDetail : list) {
            OrderItemEntity item = afterSaleDetail.getOrderItemDetail().getOrderItem();
            List<AfterSaleDetail> detailList = spitMap.get(item);
            if (detailList == null) {
                detailList = new ArrayList<AfterSaleDetail>();
            }
            detailList.add(afterSaleDetail);
            orderItemSet.add(item);
            spitMap.put(item, detailList);
        }
        for (OrderItemEntity orderItemEntity : orderItemSet) {
            MerchantOrderEntity merchantOrderEntity = orderItemEntity.getMerchantOrder();
            Date now = new Date();
            AfterSaleItem afterSaleItem=new AfterSaleItem();
            Long returnOrderId = applyReturnOrder(merchantOrderEntity, now, "", "");
//            Long returnOrderId = applyReturnOrder(merchantOrderEntity, now, itemList.get(0).getExplain(), itemList.get(0).getReason());
//            applyReturnOrderItem(afterSale, date, returnOrderId);
            for (AfterSaleDetail afterSale : spitMap.get(orderItemEntity)) {
                
            }
        }
        //
        // List<List<AfterSaleDetail>> spitList = spitAfterSaleDetail(list);
        // for (List<AfterSaleDetail> itemList : spitList) {
        // MerchantOrderEntity merchantOrderEntity = itemList.get(0).getOrderItemDetail().getMerchantOrder();
        // Date now = new Date();
        // Long returnOrderId = applyReturnOrder(merchantOrderEntity, now, itemList.get(0).getExplain(), itemList.get(0).getReason());
        // for (AfterSaleDetail afterSale : itemList) {
        // applyReturnOrderItemDetail(afterSale, now, returnOrderId);
        // }
        // ReturnAfterSaleOrder afterSaleOrder = afterSaleSelecterService.getReturnOrder(merchantOrderEntity, returnOrderId);
        // orderObserverService.doNotify(afterSaleOrder, afterSaleOrder.getState());
        // }
        return false;
    }
}
