package com.qcloud.component.orderform.web.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import com.qcloud.component.orderform.entity.AfterSaleDetail;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.entity.OrderItemDetailEntity;
import com.qcloud.component.orderform.entity.OrderItemEntity;
import com.qcloud.component.orderform.web.form.AfterSaleForm;
import com.qcloud.component.orderform.web.form.AfterSaleFormItem;
import com.qcloud.pirates.util.AssertUtil;

public class AfterSaleDetailUtils {

    // private static List<AfterSaleDetail> orderToItemList(List<AfterSaleOrder> existList) {
    //
    // Map<Long, AfterSale> map = new HashMap<Long, AfterSale>();
    // for (AfterSaleOrder afterSaleOrder : existList) {
    // if (afterSaleOrder.getState() == ExchangeOrderStateType.EXCHANGE_REFUSE.getKey() || afterSaleOrder.getState() == ReturnOrderStateType.RETURN_REFUSE.getKey() || afterSaleOrder.getState() == RefundOrderStateType.REFUND_REFUSE.getKey()) {
    // continue;
    // }
    // Map<Long, AfterSale> oneMap = orderToItem(afterSaleOrder);
    // for (Map.Entry<Long, AfterSale> e : oneMap.entrySet()) {
    // AfterSale afterSale = map.get(e.getKey());
    // if (afterSale == null) {
    // map.put(e.getKey(), e.getValue());
    // } else {
    // afterSale.setNumber(e.getValue().getNumber() + afterSale.getNumber());
    // }
    // }
    // }
    // return new ArrayList<AfterSale>(map.values());
    // }
    //
    // private static Map<Long, AfterSaleDetail> orderToItem(AfterSaleOrder afterSaleOrder) {
    //
    // Map<Long, AfterSale> map = new HashMap<Long, AfterSale>();
    // for (QAfterSaleOrderItem afterSaleOrderItem : afterSaleOrder.listItem()) {
    // AfterSale afterSale = map.get(afterSaleOrderItem.getAfterSaleOrderItemId());
    // if (afterSale == null) {
    // afterSale = new AfterSale();
    // map.put(afterSaleOrderItem.getOrderItemDetail().getId(), afterSale);
    // afterSale.setOrderItemDetail((OrderItemDetailEntity) afterSaleOrderItem.getOrderItemDetail());
    // }
    // afterSale.setNumber(afterSaleOrderItem.getNumber() + afterSale.getNumber());
    // }
    // return map;
    // }
    public static List<AfterSaleDetail> toAfterSales(OrderEntity orderEntity, AfterSaleForm afterSaleForm, String explain, String reason) {

        AssertUtil.assertNotNull(afterSaleForm.getOrderId(), "订单ID不能为空.");
        AssertUtil.assertNotNull(afterSaleForm.getOrderDate(), "订单日期不能为空.");
        List<AfterSaleDetail> result = new ArrayList<AfterSaleDetail>();
        switch (afterSaleForm.getType()) {
        case 1:
            for (MerchantOrderEntity merchantOrder : orderEntity.getEntityList()) {
                for (OrderItemEntity orderItem : merchantOrder.getEntityList()) {
                    for (OrderItemDetailEntity orderItemDetail : orderItem.getEntityList()) {
                        AfterSaleDetail afterSale = new AfterSaleDetail();
                        afterSale.setOrderItemDetail(orderItemDetail);
                        afterSale.setExplain(explain);
                        afterSale.setReason(reason);
                        afterSale.setNumber(orderItemDetail.getNumber());
                        result.add(afterSale);
                    }
                }
            }
            break;
        case 2:
            AssertUtil.assertNotEmpty(afterSaleForm.getSubOrderList(), "子单列表不能为空.");
            List<Long> idList = afterSaleForm.getSubOrderList();
            for (Long id : idList) {
                if (id == null) {
                    continue;
                }
                MerchantOrderEntity merchantOrder = orderEntity.getMerchantOrder(id);
                if (merchantOrder == null) {
                    continue;
                }
                for (OrderItemEntity orderItem : merchantOrder.getEntityList()) {
                    for (OrderItemDetailEntity orderItemDetail : orderItem.getEntityList()) {
                        AfterSaleDetail afterSale = new AfterSaleDetail();
                        afterSale.setOrderItemDetail(orderItemDetail);
                        afterSale.setExplain(explain);
                        afterSale.setReason(reason);
                        afterSale.setNumber(orderItemDetail.getNumber());
                        result.add(afterSale);
                    }
                }
            }
            break;
        case 3:
            AssertUtil.assertNotEmpty(afterSaleForm.getItemList(), "物品列表不能为空.");
            List<AfterSaleFormItem> itemList = afterSaleForm.getItemList();
            for (AfterSaleFormItem afterSaleFormItem : itemList) {
                List<AfterSaleDetail> afterSaleList = toAfterSales(orderEntity, afterSaleFormItem, explain, reason);
                if (CollectionUtils.isNotEmpty(afterSaleList)) {
                    result.addAll(afterSaleList);
                }
            }
            break;
        default:
            break;
        }
        return result;
    }

    private static List<AfterSaleDetail> toAfterSales(OrderEntity orderEntity, AfterSaleFormItem afterSaleFormItem, String explain, String reason) {

        List<AfterSaleDetail> afterSaleList = new ArrayList<AfterSaleDetail>();
        if (afterSaleFormItem.getOrderItemId() != null || afterSaleFormItem.getOrderItemId() > 0) {
            for (MerchantOrderEntity merchantOrder : orderEntity.getEntityList()) {
                for (OrderItemEntity orderItem : merchantOrder.getEntityList()) {
                    if (orderItem.getId() == afterSaleFormItem.getOrderItemId()) {
                        for (OrderItemDetailEntity orderItemDetail : orderItem.getEntityList()) {
                            AfterSaleDetail afterSale = new AfterSaleDetail();
                            afterSale.setOrderItemDetail(orderItemDetail);
                            afterSale.setExplain(StringUtils.isEmpty(afterSaleFormItem.getExplain()) ? explain : afterSaleFormItem.getExplain());
                            afterSale.setReason(StringUtils.isEmpty(afterSaleFormItem.getReason()) ? reason : afterSaleFormItem.getReason());
                            afterSale.setNumber(orderItemDetail.getNumber() < afterSaleFormItem.getNumber() ? orderItemDetail.getNumber() : afterSaleFormItem.getNumber());
                            afterSale.setNumber(afterSale.getNumber() <= 0 ? orderItemDetail.getNumber() : afterSale.getNumber());
                            afterSaleList.add(afterSale);
                        }
                    }
                }
            }
        }
        // 暂不支持按订单项明细换
        // if (CollectionUtils.isEmpty(afterSaleList) && afterSaleItem.getOrderItemDetailId() != null || afterSaleItem.getOrderItemDetailId() > 0) {
        // for (MerchantOrderEntity merchantOrder : orderEntity.getEntityList()) {
        // for (OrderItemEntity orderItem : merchantOrder.getEntityList()) {
        // for (OrderItemDetailEntity orderItemDetail : orderItem.getEntityList()) {
        // if (orderItemDetail.getId() == afterSaleItem.getOrderItemDetailId()) {
        // AfterSale afterSale = new AfterSale();
        // afterSale.setOrderItemDetail(orderItemDetail);
        // afterSale.setExplain(StringUtils.isEmpty(afterSaleItem.getExplain()) ? explain : afterSaleItem.getExplain());
        // afterSale.setReason(StringUtils.isEmpty(afterSaleItem.getReason()) ? reason : afterSaleItem.getReason());
        // afterSale.setNumber(orderItemDetail.getNumber() < afterSaleItem.getNumber() ? orderItemDetail.getNumber() : afterSaleItem.getNumber());
        // afterSale.setNumber(afterSale.getNumber() <= 0 ? orderItemDetail.getNumber() : afterSale.getNumber());
        // afterSaleList.add(afterSale);
        // }
        // }
        // }
        // }
        // }
        return afterSaleList;
    }
}
