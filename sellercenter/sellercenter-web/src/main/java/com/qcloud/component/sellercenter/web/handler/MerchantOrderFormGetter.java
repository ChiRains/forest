//package com.qcloud.component.sellercenter.web.handler;
//
//import java.util.Date;
//
//public interface MerchantOrderFormGetter {
//
//    MerchantOrderFormModel get(Long subOrderId, Date orderDate);
//    
//    long getOrderId(String orderNumber);
//    
//    
//    //
//    public static interface MerchantOrderFormModel {
//
//        String getOrderNumber();
//
//        String getSubOrderNumber();
//
//        long getUserId();
//
//        double getSum();
//
//        Date getOrderDate();
//
//        String getAddress();
//
//        String getMobile();
//
//        String getDeliveryTimeStr();
//
//        String getExplain();
//
//        String getConsignee();
//
//        long getStoreId();
//
//        String getPickupAddressStr();
//
//        // 是否开发票
//        int getNeedInvoiceType();
//
//        // 发票类型
//        int getInvoiceType();
//
//        double getCash();
//    }
//}
