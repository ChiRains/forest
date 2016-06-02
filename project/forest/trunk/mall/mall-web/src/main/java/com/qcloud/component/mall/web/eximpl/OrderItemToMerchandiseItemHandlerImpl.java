//package com.qcloud.component.mall.web.eximpl;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.qcloud.component.commoditycenter.web.handler.OrderItemToMerchandiseItemHandler;
//import com.qcloud.component.orderform.OrderformClient;
//import com.qcloud.component.orderform.QMerchantOrder;
//import com.qcloud.component.orderform.QOrder;
//import com.qcloud.component.orderform.QOrderItem;
//import com.qcloud.component.orderform.QOrderItemDetail;
//
//@Component
//public class OrderItemToMerchandiseItemHandlerImpl implements OrderItemToMerchandiseItemHandler {
//
//    @Autowired
//    OrderformClient orderformClient;
//
//    @Override
//    public List<OrderItemInformation> itemIdToInformation(Long orderId, Date orderDate, Long orderItemId) {
//
//        QOrder order = orderformClient.getOrder(orderId, orderDate);
//        List<QMerchantOrder> list = order.getMerchantOrderList();
//        QOrderItem item = null;
//        for (QMerchantOrder qMerchantOrder : list) {
//            List<QOrderItem> itemList = qMerchantOrder.getOrderItemList();
//            for (QOrderItem qOrderItem : itemList) {
//                if (qOrderItem.getId() == orderItemId) {
//                    item = qOrderItem;
//                    break;
//                }
//            }
//            if (item != null) {
//                break;
//            }
//        }
//        List<OrderItemInformation> inList = new ArrayList<OrderItemInformation>();
//        if (item != null) {
//            for (QOrderItemDetail orderItemDetail : item.getOrderItemDetailList()) {
//                OrderItemInformation in = new OrderItemInformation();
//                in.setMerchandiseItemId(orderItemDetail.getMerchandiseItemId());
//                in.setOrderId(orderId);
//                in.setOrderItemDetailId(orderItemDetail.getId());
//                in.setSpecifications(orderItemDetail.getSpecifications());
//                inList.add(in);
//            }
//        }
//        return inList;
//    }
//}
