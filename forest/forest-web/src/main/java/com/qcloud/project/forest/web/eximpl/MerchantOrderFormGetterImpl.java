//package com.qcloud.project.forest.web.eximpl;
//
//import java.util.Date;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.qcloud.component.orderform.OrderformClient;
//import com.qcloud.component.orderform.QMerchantOrder;
//import com.qcloud.component.sellercenter.web.handler.MerchantOrderFormGetter;
//import com.qcloud.pirates.util.AssertUtil;
//
//@Component
//public class MerchantOrderFormGetterImpl implements MerchantOrderFormGetter {
//
//    @Autowired
//    private OrderformClient orderformClient;
//
//    @Override
//    public MerchantOrderFormModel get(Long subOrderId, Date orderDate) {
//
//        final QMerchantOrder merchantOrder = orderformClient.getMerchantOrder(subOrderId, orderDate);
//        AssertUtil.assertNotNull(merchantOrder, "子订单不存在." + subOrderId);
//        return new MerchantOrderFormModel() {
//
//            @Override
//            public String getOrderNumber() {
//
//                return merchantOrder.getOrder().getOrderNumber();
//            }
//
//            @Override
//            public String getSubOrderNumber() {
//
//                return merchantOrder.getOrderNumber();
//            }
//
//            @Override
//            public long getUserId() {
//
//                return merchantOrder.getOrder().getUserId();
//            }
//
//            @Override
//            public double getSum() {
//
//                return merchantOrder.getSum();
//            }
//
//            @Override
//            public Date getOrderDate() {
//
//                return merchantOrder.getOrder().getOrderDate();
//            }
//
//            @Override
//            public String getAddress() {
//
//                return merchantOrder.getOrder().getAddress();
//            }
//
//            @Override
//            public String getMobile() {
//
//                return merchantOrder.getOrder().getMobile();
//            }
//
//            @Override
//            public String getDeliveryTimeStr() {
//
//                return merchantOrder.getDeliveryTimeStr();
//            }
//
//            @Override
//            public String getExplain() {
//
//                return merchantOrder.getExplain();
//            }
//
//            @Override
//            public String getConsignee() {
//
//                return merchantOrder.getOrder().getConsignee();
//            }
//
//            @Override
//            public long getStoreId() {
//
//                return merchantOrder.getStoreId();
//            }
//
//            @Override
//            public String getPickupAddressStr() {
//
//                return merchantOrder.getPickupAddressStr();
//            }
//
//            @Override
//            public int getNeedInvoiceType() {
//
//                return merchantOrder.getOrder().getNeedInvoiceType();
//            }
//
//            @Override
//            public int getInvoiceType() {
//
//                return merchantOrder.getOrder().getInvoiceType();
//            }
//
//            @Override
//            public double getCash() {
//
//                return merchantOrder.getOrder().getCash();
//            }
//        };
//    }
//
//    @Override
//    public long getOrderId(String orderNumber) {
//
//        // TODO Auto-generated method stub
//        return 0;
//    }
//}
