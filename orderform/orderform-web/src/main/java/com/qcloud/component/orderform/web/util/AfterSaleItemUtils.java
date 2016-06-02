package com.qcloud.component.orderform.web.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import com.qcloud.component.orderform.entity.AfterSaleItem;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.entity.OrderItemEntity;
import com.qcloud.component.orderform.web.form.AfterSaleForm;
import com.qcloud.component.orderform.web.form.AfterSaleFormItem;
import com.qcloud.pirates.util.AssertUtil;

public class AfterSaleItemUtils {

    public static List<AfterSaleItem> toAfterSales(OrderEntity orderEntity, AfterSaleForm afterSaleForm, String explain, String reason) {

        AssertUtil.assertNotNull(afterSaleForm.getOrderId(), "订单ID不能为空.");
        AssertUtil.assertNotNull(afterSaleForm.getOrderDate(), "订单日期不能为空.");
        List<AfterSaleItem> result = new ArrayList<AfterSaleItem>();
        switch (afterSaleForm.getType()) {
        case 1:
            for (MerchantOrderEntity merchantOrder : orderEntity.getEntityList()) {
                for (OrderItemEntity orderItem : merchantOrder.getEntityList()) {
                    AfterSaleItem afterSale = new AfterSaleItem();
                    afterSale.setOrderItem(orderItem);
                    afterSale.setExplain(explain);
                    afterSale.setReason(reason);
                    afterSale.setNumber(orderItem.getNumber());
                    result.add(afterSale);
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
                    AfterSaleItem afterSale = new AfterSaleItem();
                    afterSale.setOrderItem(orderItem);
                    afterSale.setExplain(explain);
                    afterSale.setReason(reason);
                    afterSale.setNumber(orderItem.getNumber());
                    result.add(afterSale);
                }
            }
            break;
        case 3:
            AssertUtil.assertNotEmpty(afterSaleForm.getItemList(), "物品列表不能为空.");
            List<AfterSaleFormItem> itemList = afterSaleForm.getItemList();
            for (AfterSaleFormItem afterSaleFormItem : itemList) {
                List<AfterSaleItem> afterSaleList = toAfterSales(orderEntity, afterSaleFormItem, explain, reason);
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

    private static List<AfterSaleItem> toAfterSales(OrderEntity orderEntity, AfterSaleFormItem afterSaleFormItem, String explain, String reason) {

        List<AfterSaleItem> afterSaleList = new ArrayList<AfterSaleItem>();
        if (afterSaleFormItem.getOrderItemId() != null || afterSaleFormItem.getOrderItemId() > 0) {
            for (MerchantOrderEntity merchantOrder : orderEntity.getEntityList()) {
                for (OrderItemEntity orderItem : merchantOrder.getEntityList()) {
                    if (orderItem.getId() == afterSaleFormItem.getOrderItemId()) {
                        AfterSaleItem afterSale = new AfterSaleItem();
                        afterSale.setOrderItem(orderItem);
                        afterSale.setExplain(StringUtils.isEmpty(afterSaleFormItem.getExplain()) ? explain : afterSaleFormItem.getExplain());
                        afterSale.setReason(StringUtils.isEmpty(afterSaleFormItem.getReason()) ? reason : afterSaleFormItem.getReason());
                        afterSale.setNumber(orderItem.getNumber() < afterSaleFormItem.getNumber() ? orderItem.getNumber() : afterSaleFormItem.getNumber());
                        afterSale.setNumber(afterSale.getNumber() <= 0 ? orderItem.getNumber() : afterSale.getNumber());
                        afterSaleList.add(afterSale);
                    }
                }
            }
        }
        return afterSaleList;
    }
}
