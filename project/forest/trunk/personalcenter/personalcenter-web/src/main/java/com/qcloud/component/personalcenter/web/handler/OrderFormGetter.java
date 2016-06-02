//package com.qcloud.component.personalcenter.web.handler;
//
//import java.util.Date;
//import java.util.List;
//
//public interface OrderFormGetter {
//
//    OrderFormModel get(Long orderId, Date orderDate);
//    //
//    public static interface OrderFormModel {
//
//        String getOrderNumber();
//
//        double getSum();
//
//        double getCash();
//
//        Date getOrderDate();
//
//        Date getExpectedArrivalDate();
//
//        Date getArrivalDate();
//
//        String getFirstImage();
//
//        String getAfterSaleStateName();
//
//        List<MerchantOrderModel> getMerchantOrderList();
//
//        double calculateTotalPrice();
//        //
//        public static interface MerchantOrderModel {
//
//            long getMerchantId();
//
//            String getMerchantName();
//
//            int getNumber();
//
//            double getSum();
//
//            List<OrderItemModel> getOrderItemList();
//        }
//        //
//        public static interface OrderItemModel {
//
//            long getUnifiedMerchandiseId();
//
//            double getDiscount();
//
//            int getNumber();
//
//            String getFirstImage();
//
//            String getFirstName();
//
//            String getSpecifications();
//        }
//    }
//}
