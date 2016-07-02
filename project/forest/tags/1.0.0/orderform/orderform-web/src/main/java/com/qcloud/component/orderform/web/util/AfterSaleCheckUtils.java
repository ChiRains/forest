package com.qcloud.component.orderform.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.my.AfterSaleType;
import com.qcloud.component.orderform.QAfterSaleDetail;
import com.qcloud.component.orderform.QAfterSaleItem;
import com.qcloud.component.orderform.QAfterSaleOrderItem;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.orderform.QOrderItemDetail;
import com.qcloud.component.orderform.entity.AfterSale;
import com.qcloud.component.orderform.entity.AfterSaleDetail;
import com.qcloud.component.orderform.entity.AfterSaleItem;
import com.qcloud.component.orderform.entity.AfterSaleOrder;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.entity.OrderItemDetailEntity;

public class AfterSaleCheckUtils {

    public static Long checkItem(AfterSaleOrder afterSaleOrder, List<AfterSaleOrder> existList) {

        List<AfterSaleDetail> list = new ArrayList<AfterSaleDetail>(orderToItem(afterSaleOrder).values());
        return checkDetail(list, existList);
    }

    public static Long checkItem(List<AfterSaleItem> list, List<AfterSaleOrder> existList) {

        List<AfterSaleDetail> itemList = orderToItemList(existList);
        for (AfterSaleItem out : list) {
            for (QOrderItemDetail orderItemDetail : out.getOrderItem().getOrderItemDetailList()) {
                for (AfterSaleDetail in : itemList) {
                    if (orderItemDetail.getId() == in.getOrderItemDetail().getId() && out.getNumber() + in.getNumber() > in.getOrderItemDetail().getNumber()) {
                        return orderItemDetail.getId();
                    }
                }
            }
        }
        return -1L;
    }

    public static Long checkItem(OrderEntity orderEntity, List<AfterSaleItem> list, AfterSaleType type) {

        Set<Long> idSet = new HashSet<Long>();
        for (AfterSaleItem afterSale : list) {
            idSet.add(afterSale.getOrderItem().getMerchantOrder().getId());
        }
        List<MerchantOrderEntity> merchantOrderList = orderEntity.getEntityList();
        for (MerchantOrderEntity merchantOrderEntity : merchantOrderList) {
            if (AfterSaleType.REFUND.equals(type)) {
                if (!merchantOrderEntity.canRefund()) {
                    return merchantOrderEntity.getId();
                }
            } else if (AfterSaleType.EXCHANGE.equals(type)) {
                if (!merchantOrderEntity.canExchange()) {
                    return merchantOrderEntity.getId();
                }
            } else if (AfterSaleType.RETURN.equals(type)) {
                if (!merchantOrderEntity.canReturn()) {
                    return merchantOrderEntity.getId();
                }
            }
        }
        return -1L;
    }

    public static Long checkDetail(AfterSaleOrder afterSaleOrder, List<AfterSaleOrder> existList) {

        List<AfterSaleDetail> list = new ArrayList<AfterSaleDetail>(orderToItem(afterSaleOrder).values());
        return checkDetail(list, existList);
    }

    public static Long checkDetail(List<AfterSaleDetail> list, List<AfterSaleOrder> existList) {

        List<AfterSaleDetail> itemList = orderToItemList(existList);
        for (AfterSaleDetail out : list) {
            for (AfterSaleDetail in : itemList) {
                if (out.getOrderItemDetail().getId() == in.getOrderItemDetail().getId() && out.getNumber() + in.getNumber() > in.getOrderItemDetail().getNumber()) {
                    return out.getOrderItemDetail().getId();
                }
            }
        }
        return -1L;
    }

    public static Long checkDetail(OrderEntity orderEntity, List<AfterSaleDetail> list, AfterSaleType type) {

        Set<Long> idSet = new HashSet<Long>();
        for (AfterSaleDetail afterSale : list) {
            idSet.add(afterSale.getOrderItemDetail().getMerchantOrder().getId());
        }
        List<MerchantOrderEntity> merchantOrderList = orderEntity.getEntityList();
        for (MerchantOrderEntity merchantOrderEntity : merchantOrderList) {
            if (AfterSaleType.REFUND.equals(type)) {
                if (!merchantOrderEntity.canRefund()) {
                    return merchantOrderEntity.getId();
                }
            } else if (AfterSaleType.EXCHANGE.equals(type)) {
                if (!merchantOrderEntity.canExchange()) {
                    return merchantOrderEntity.getId();
                }
            } else if (AfterSaleType.RETURN.equals(type)) {
                if (!merchantOrderEntity.canReturn()) {
                    return merchantOrderEntity.getId();
                }
            }
        }
        return -1L;
    }

    private static List<AfterSaleDetail> orderToItemList(List<AfterSaleOrder> existList) {

        Map<Long, AfterSaleDetail> map = new HashMap<Long, AfterSaleDetail>();
        for (AfterSaleOrder afterSaleOrder : existList) {
            if (afterSaleOrder.getState() == ExchangeOrderStateType.EXCHANGE_REFUSE.getKey() || afterSaleOrder.getState() == ReturnOrderStateType.RETURN_REFUSE.getKey() || afterSaleOrder.getState() == RefundOrderStateType.REFUND_REFUSE.getKey()) {
                continue;
            }
            Map<Long, AfterSaleDetail> oneMap = orderToItem(afterSaleOrder);
            for (Map.Entry<Long, AfterSaleDetail> e : oneMap.entrySet()) {
                AfterSale afterSale = map.get(e.getKey());
                if (afterSale == null) {
                    map.put(e.getKey(), e.getValue());
                } else {
                    afterSale.setNumber(e.getValue().getNumber() + afterSale.getNumber());
                }
            }
        }
        return new ArrayList<AfterSaleDetail>(map.values());
    }

    private static Map<Long, AfterSaleDetail> orderToItem(AfterSaleOrder afterSaleOrder) {

        Map<Long, AfterSaleDetail> map = new HashMap<Long, AfterSaleDetail>();
        for (QAfterSaleOrderItem afterSaleOrderItem : afterSaleOrder.listItem()) {
            if (afterSaleOrderItem instanceof QAfterSaleItem) {
                QOrderItem orderItem = ((QAfterSaleItem) afterSaleOrderItem).getOrderItem();
                List<QOrderItemDetail> detailList = orderItem.getOrderItemDetailList();
                for (QOrderItemDetail qOrderItemDetail : detailList) {
                    AfterSaleDetail afterSale = map.get(qOrderItemDetail.getId());
                    if (afterSale == null) {
                        afterSale = new AfterSaleDetail();
                        map.put(qOrderItemDetail.getId(), afterSale);
                        afterSale.setOrderItemDetail((OrderItemDetailEntity) qOrderItemDetail);
                    }
                    afterSale.setNumber(afterSaleOrderItem.getNumber() + afterSale.getNumber());
                }
            } else if (afterSaleOrderItem instanceof QAfterSaleDetail) {
                QOrderItemDetail qOrderItemDetail = ((QAfterSaleDetail) afterSaleOrderItem).getOrderItemDetail();
                AfterSaleDetail afterSale = map.get(qOrderItemDetail.getId());
                if (afterSale == null) {
                    afterSale = new AfterSaleDetail();
                    map.put(qOrderItemDetail.getId(), afterSale);
                    afterSale.setOrderItemDetail((OrderItemDetailEntity) qOrderItemDetail);
                }
                afterSale.setNumber(afterSaleOrderItem.getNumber() + afterSale.getNumber());
            }
        }
        return map;
    }
}
