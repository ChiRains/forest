package com.qcloud.component.orderform.engine.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.orderform.engine.AfterSaleSelecterService;
import com.qcloud.component.orderform.entity.AfterSaleOrder;
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
import com.qcloud.component.orderform.service.RefundOrderItemService;
import com.qcloud.component.orderform.service.RefundOrderService;
import com.qcloud.component.orderform.service.ReturnOrderItemService;
import com.qcloud.component.orderform.service.ReturnOrderService;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class AfterSaleSelecterServiceImpl implements AfterSaleSelecterService {

    @Autowired
    ExchangeOrderService           exchangeOrderService;

    @Autowired
    ExchangeOrderItemDetailService exchangeOrderItemDetailService;

    @Autowired
    ReturnOrderService             returnOrderService;

    @Autowired
    ReturnOrderItemService         returnOrderItemService;

    @Autowired
    RefundOrderService             refundOrderService;

    @Autowired
    RefundOrderItemService         refundOrderItemService;

    @Autowired
    OrderConfigService             orderConfigService;

    @Override
    public ExchangeAfterSaleOrder getExchangeOrder(MerchantOrderEntity merchantOrderEntity, Long exchangeId) {

        ExchangeOrder exchangeOrder = exchangeOrderService.get(exchangeId);
        return modelToEntity(merchantOrderEntity, exchangeOrder);
    }

    @Override
    public List<AfterSaleOrder> listExchangeOrder(MerchantOrderEntity merchantOrder) {

        List<ExchangeOrder> modelList = exchangeOrderService.listBySubOrder(merchantOrder.getId());
        List<AfterSaleOrder> entityList = new ArrayList<AfterSaleOrder>();
        for (ExchangeOrder exchangeOrder : modelList) {
            ExchangeAfterSaleOrder afterSaleOrder = modelToEntity(merchantOrder, exchangeOrder);
            entityList.add(afterSaleOrder);
        }
        return entityList;
    }

    @Override
    public ReturnAfterSaleOrder getReturnOrder(MerchantOrderEntity merchantOrderEntity, Long returnId) {

        ReturnOrder returnOrder = returnOrderService.get(returnId);
        return modelToEntity(merchantOrderEntity, returnOrder);
    }

    @Override
    public List<AfterSaleOrder> listReturnOrder(MerchantOrderEntity merchantOrder) {

        List<ReturnOrder> list = returnOrderService.listBySubOrder(merchantOrder.getId());
        List<AfterSaleOrder> entityList = new ArrayList<AfterSaleOrder>();
        for (ReturnOrder returnOrder : list) {
            ReturnAfterSaleOrder afterSaleOrder = modelToEntity(merchantOrder, returnOrder);
            entityList.add(afterSaleOrder);
        }
        return entityList;
    }

    @Override
    public RefundAfterSaleOrder getRefundOrder(MerchantOrderEntity merchantOrderEntity, Long refundId) {

        RefundOrder refundOrder = refundOrderService.get(refundId);
        return modelToEntity(merchantOrderEntity, refundOrder);
    }

    @Override
    public List<AfterSaleOrder> listRefundOrder(MerchantOrderEntity merchantOrder) {

        List<RefundOrder> list = refundOrderService.listBySubOrder(merchantOrder.getId());
        List<AfterSaleOrder> entityList = new ArrayList<AfterSaleOrder>();
        for (RefundOrder refundOrder : list) {
            RefundAfterSaleOrder afterSaleOrder = modelToEntity(merchantOrder, refundOrder);
            entityList.add(afterSaleOrder);
        }
        return entityList;
    }

    private ExchangeAfterSaleOrder modelToEntity(MerchantOrderEntity merchantOrderEntity, ExchangeOrder exchangeOrder) {

        ExchangeAfterSaleOrder afterSaleOrder = new ExchangeAfterSaleOrder(merchantOrderEntity, exchangeOrder);
        afterSaleOrder.setFinish(orderConfigService.isExchangeFinish(exchangeOrder.getState()));
        List<ExchangeOrderItemDetail> modelList = exchangeOrderItemDetailService.listByExchange(exchangeOrder.getId());
        List<AfterSaleOrderItem> entityList = new ArrayList<AfterSaleOrderItem>();
        for (ExchangeOrderItemDetail exchangeOrderItemDetail : modelList) {
            OrderItemDetailEntity orderItemDetailEntity = getDetailInSub(merchantOrderEntity, exchangeOrderItemDetail.getOrderItemDetailId());
            AssertUtil.assertNotNull(orderItemDetailEntity, "订单明细不存在." + exchangeOrderItemDetail.getOrderItemDetailId());
            ExchangeAfterSaleOrderItem exchangeAfterSaleOrderItem = modelToEntity(orderItemDetailEntity, afterSaleOrder, exchangeOrderItemDetail);
            entityList.add(exchangeAfterSaleOrderItem);
        }
        afterSaleOrder.setList(entityList);
        int state = orderConfigService.getExchangePersonalOrderState(exchangeOrder.getState());
        String userStateStr = orderConfigService.getExchangePersonalOrderStateDesc(exchangeOrder.getState());
        afterSaleOrder.setUserState(state);
        afterSaleOrder.setUserStateStr(userStateStr);
        return afterSaleOrder;
    }

    private RefundAfterSaleOrder modelToEntity(MerchantOrderEntity merchantOrderEntity, RefundOrder refundOrder) {

        RefundAfterSaleOrder afterSaleOrder = new RefundAfterSaleOrder(merchantOrderEntity, refundOrder);
        afterSaleOrder.setFinish(orderConfigService.isRefundFinish(refundOrder.getState()));
        List<RefundOrderItem> modelList = refundOrderItemService.listByRefund(refundOrder.getId());
        List<AfterSaleOrderItem> entityList = new ArrayList<AfterSaleOrderItem>();
        for (RefundOrderItem refundOrderItem : modelList) {
            OrderItemEntity orderItemEntity = getItemInSub(merchantOrderEntity, refundOrderItem.getOrderItemId());
            AssertUtil.assertNotNull(orderItemEntity, "订单项不存在." + refundOrderItem.getOrderItemId());
            RefundAfterSaleOrderItem refundAfterSaleOrderItem = modelToEntity(orderItemEntity, afterSaleOrder, refundOrderItem);
            entityList.add(refundAfterSaleOrderItem);
        }
        afterSaleOrder.setList(entityList);
        int state = orderConfigService.getRefundPersonalOrderState(refundOrder.getState());
        String userStateStr = orderConfigService.getRefundPersonalOrderStateDesc(refundOrder.getState());
        afterSaleOrder.setUserState(state);
        afterSaleOrder.setUserStateStr(userStateStr);
        return afterSaleOrder;
    }

    private ReturnAfterSaleOrder modelToEntity(MerchantOrderEntity merchantOrderEntity, ReturnOrder returnOrder) {

        ReturnAfterSaleOrder afterSaleOrder = new ReturnAfterSaleOrder(merchantOrderEntity, returnOrder);
        afterSaleOrder.setFinish(orderConfigService.isReturnFinish(returnOrder.getState()));
        List<ReturnOrderItem> modelList = returnOrderItemService.listByReturn(returnOrder.getId());
        List<AfterSaleOrderItem> entityList = new ArrayList<AfterSaleOrderItem>();
        for (ReturnOrderItem returnOrderItem : modelList) {
            OrderItemEntity orderItemEntity = getItemInSub(merchantOrderEntity, returnOrderItem.getOrderItemId());
            AssertUtil.assertNotNull(orderItemEntity, "订单项不存在." + returnOrderItem.getOrderItemId());
            ReturnAfterSaleOrderItem returnAfterSaleOrderItem = modelToEntity(orderItemEntity, afterSaleOrder, returnOrderItem);
            entityList.add(returnAfterSaleOrderItem);
        }
        afterSaleOrder.setList(entityList);
        int state = orderConfigService.getReturnPersonalOrderState(returnOrder.getState());
        String userStateStr = orderConfigService.getReturnPersonalOrderStateDesc(returnOrder.getState());
        afterSaleOrder.setUserState(state);
        afterSaleOrder.setUserStateStr(userStateStr);
        return afterSaleOrder;
    }

    private ExchangeAfterSaleOrderItem modelToEntity(OrderItemDetailEntity orderItemDetailEntity, ExchangeAfterSaleOrder exchangeAfterSaleOrder, ExchangeOrderItemDetail exchangeOrderItem) {

        ExchangeAfterSaleOrderItem afterSaleOrderItem = new ExchangeAfterSaleOrderItem(orderItemDetailEntity, exchangeAfterSaleOrder, exchangeOrderItem);
        return afterSaleOrderItem;
    }

    private ReturnAfterSaleOrderItem modelToEntity(OrderItemEntity orderItemEntity, ReturnAfterSaleOrder returnAfterSaleOrder, ReturnOrderItem returnOrderItem) {

        ReturnAfterSaleOrderItem afterSaleOrderItem = new ReturnAfterSaleOrderItem(returnAfterSaleOrder, orderItemEntity, returnOrderItem);
        return afterSaleOrderItem;
    }

    private RefundAfterSaleOrderItem modelToEntity(OrderItemEntity orderItemEntity, RefundAfterSaleOrder refundAfterSaleOrder, RefundOrderItem refundOrderItem) {

        RefundAfterSaleOrderItem afterSaleOrderItem = new RefundAfterSaleOrderItem(refundAfterSaleOrder, orderItemEntity, refundOrderItem);
        return afterSaleOrderItem;
    }

    private OrderItemEntity getItemInSub(MerchantOrderEntity merchantOrderEntity, long detailId) {

        for (OrderItemEntity orderItemEntity : merchantOrderEntity.getEntityList()) {
            if (orderItemEntity.getId() == detailId) {
                return orderItemEntity;
            }
        }
        return null;
    }

    private OrderItemDetailEntity getDetailInSub(MerchantOrderEntity merchantOrderEntity, long detailId) {

        for (OrderItemEntity orderItemEntity : merchantOrderEntity.getEntityList()) {
            for (OrderItemDetailEntity orderItemDetailEntity : orderItemEntity.getEntityList()) {
                if (orderItemDetailEntity.getId() == detailId) {
                    return orderItemDetailEntity;
                }
            }
        }
        return null;
    }

    @Override
    public List<AfterSaleOrder> listAfterSaleOrder(OrderEntity orderEntity) {

        List<AfterSaleOrder> list = new ArrayList<AfterSaleOrder>();
        for (MerchantOrderEntity merchantOrder : orderEntity.getEntityList()) {
            List<AfterSaleOrder> asoList = listAfterSaleOrder(merchantOrder);
            if (CollectionUtils.isNotEmpty(asoList)) {
                list.addAll(asoList);
            }
        }
        return list;
    }

    @Override
    public List<AfterSaleOrder> listAfterSaleOrder(MerchantOrderEntity merchantOrder) {

        List<AfterSaleOrder> list = new ArrayList<AfterSaleOrder>();
        List<AfterSaleOrder> exchangeList = listExchangeOrder(merchantOrder);
        if (CollectionUtils.isNotEmpty(exchangeList)) {
            list.addAll(exchangeList);
        }
        List<AfterSaleOrder> returnList = listReturnOrder(merchantOrder);
        if (CollectionUtils.isNotEmpty(returnList)) {
            list.addAll(returnList);
        }
        List<AfterSaleOrder> refundList = listRefundOrder(merchantOrder);
        if (CollectionUtils.isNotEmpty(refundList)) {
            list.addAll(refundList);
        }
        return list;
    }
}
