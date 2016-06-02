//package com.qcloud.component.orderform.service;
//
//import java.util.Date;
//import com.qcloud.component.orderform.entity.Order;
//
////
//public interface OrderService {
//
//    //
////    OrderResult order(Order order);
//
////    boolean exchangeOrderState(Long orderId, Date orderDate, int state, Long authentication);
////
////    boolean exchangeSubOrderState(Long subOrderId, Date orderDate, int state, Long authentication);
////
////    boolean exchangeOrderItemState(Long orderItemId, Date orderDate, int state, Long authentication);
////
////    boolean exchangeOrderItemDetailState(Long orderItemDetailId, Date orderDate, int state, Long authentication);
//
//    boolean invalid();
//
//    boolean sign();
//
//    boolean trade();
//    // 下单结果
//    interface OrderResult {
//
//        boolean isSuccess();
//
//        String getOrderNumber();
//
//        Long getOrderId();
//
//        double getSum();
//
//        Date getDate();
//    }
//}
