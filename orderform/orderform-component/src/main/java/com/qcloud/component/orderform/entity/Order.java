package com.qcloud.component.orderform.entity;

import java.util.List;
import com.qcloud.component.my.DeliveryModeType;
import com.qcloud.component.my.InvoiceType;
import com.qcloud.component.my.NeedInvoiceType;
import com.qcloud.component.my.QMyConsignee;
import com.qcloud.component.orderform.PaymentModeType;
import com.qcloud.component.personalcenter.QUser;

//
public class Order {

    // 用户
    private QUser                  user;

    // 收货人
    private QMyConsignee             consignee;

    // 订单详情
    private List<OrderMerchandise> merchandiseList;

//    // 促销
//    private List<String>           promotionTokenList;

    // 订单说明
    private String                 explain;

    // 送货时间
    private String                 deliveryTimeStr;

    // 支付方式
    private PaymentModeType        paymentModeType;

    // 自提地址
    private String                 pickupAddressStr;

    // 送货方式
    private DeliveryModeType       deliveryModeType;

    // 是否开发票
    private NeedInvoiceType        needInvoiceType;

    // 发票类型
    private InvoiceType            invoiceType;

    // 发票抬头
    private String                 invoiceHead;

    // 发票内容
    private String                 invoiceContent;

    // 选择了门店,则不为-1
    private Long                   storeId = -1L;

    private Long                   myCouponId;

    public QUser getUser() {

        return user;
    }

    public void setUser(QUser user) {

        this.user = user;
    }

    public QMyConsignee getConsignee() {

        return consignee;
    }

    public void setConsignee(QMyConsignee consignee) {

        this.consignee = consignee;
    }

    public List<OrderMerchandise> getMerchandiseList() {

        return merchandiseList;
    }

    public void setMerchandiseList(List<OrderMerchandise> merchandiseList) {

        this.merchandiseList = merchandiseList;
    }

//    public List<String> getPromotionTokenList() {
//
//        return promotionTokenList;
//    }
//
//    public void setPromotionTokenList(List<String> promotionTokenList) {
//
//        this.promotionTokenList = promotionTokenList;
//    }

    public String getExplain() {

        return explain;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }

    public String getDeliveryTimeStr() {

        return deliveryTimeStr;
    }

    public void setDeliveryTimeStr(String deliveryTimeStr) {

        this.deliveryTimeStr = deliveryTimeStr;
    }

    public PaymentModeType getPaymentModeType() {

        return paymentModeType;
    }

    public void setPaymentModeType(PaymentModeType paymentModeType) {

        this.paymentModeType = paymentModeType;
    }

    public String getPickupAddressStr() {

        return pickupAddressStr;
    }

    public void setPickupAddressStr(String pickupAddressStr) {

        this.pickupAddressStr = pickupAddressStr;
    }

    public DeliveryModeType getDeliveryModeType() {

        return deliveryModeType;
    }

    public void setDeliveryModeType(DeliveryModeType deliveryModeType) {

        this.deliveryModeType = deliveryModeType;
    }

    public NeedInvoiceType getNeedInvoiceType() {

        return needInvoiceType;
    }

    public void setNeedInvoiceType(NeedInvoiceType needInvoiceType) {

        this.needInvoiceType = needInvoiceType;
    }

    public InvoiceType getInvoiceType() {

        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {

        this.invoiceType = invoiceType;
    }

    public String getInvoiceHead() {

        return invoiceHead;
    }

    public void setInvoiceHead(String invoiceHead) {

        this.invoiceHead = invoiceHead;
    }

    public String getInvoiceContent() {

        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {

        this.invoiceContent = invoiceContent;
    }

    public Long getStoreId() {

        return storeId;
    }

    public void setStoreId(Long storeId) {

        this.storeId = storeId;
    }

    public Long getMyCouponId() {

        return myCouponId;
    }

    public void setMyCouponId(Long myCouponId) {

        this.myCouponId = myCouponId;
    }
}
