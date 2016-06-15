package com.qcloud.project.forest.web.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.orderform.web.vo.personal.OrderItemVO;
import com.qcloud.project.forest.model.GiftCoupon;

public class ForestOrderVO {

    private String                  explain;

    private List<CombinationItemVO> combinationItemList = new ArrayList<CombinationItemVO>();

    private List<OrderItemVO>       orderItemList       = new ArrayList<OrderItemVO>();

    private long                    orderId;

    private Date                    orderDate;

    private String                  orderNumber;

    private long                    merchantId;

    private long                    storeId;

    private GiftCoupon              giftCoupon;

    private CouponItems             couponItems;

    private String                  deliveryDateStr;

    private int                     deliveryMode;

    private String                  prove;

    private String                  payDateStr;

    private int                     invoiceType;

    private int                     paymentMode;

    private String                  merchantName;

    private String                  merchantImage;

    // 优惠券面额
    private double                  counpon;

    // 邮费
    private double                  postage;

    // 订单金额
    private double                  sum;

    // 现金
    private double                  cash;

    // 节省了多少
    private double                  preferential;

    // 收货人
    private String                  consignee;

    // 收货地址
    private String                  address;

    // 收货电话
    private String                  mobile;

    //
    private String                  stateStr;

    private int                     state;

    private String                  deliveryModeStr;

    private String                  invoiceTypeStr;

    private String                  paymentModeStr;

    private int                     merchandiseNumber;

    private String                  residualTime;

    public ForestOrderVO() {

    }

    public void setOrderId(long orderId) {

        this.orderId = orderId;
    }

    public long getOrderId() {

        return orderId;
    }

    public void setOrderDate(Date orderDate) {

        this.orderDate = orderDate;
    }

    public Date getOrderDate() {

        return orderDate;
    }

    public void setOrderNumber(String orderNumber) {

        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {

        return orderNumber;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setStoreId(long storeId) {

        this.storeId = storeId;
    }

    public long getStoreId() {

        return storeId;
    }

    public int getDeliveryMode() {

        return deliveryMode;
    }

    public void setDeliveryMode(int deliveryMode) {

        this.deliveryMode = deliveryMode;
    }

    public String getProve() {

        return prove;
    }

    public void setProve(String prove) {

        this.prove = prove;
    }

    public String getExplain() {

        return explain;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }

    public List<OrderItemVO> getOrderItemList() {

        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemVO> orderItemList) {

        this.orderItemList = orderItemList;
    }

    public List<CombinationItemVO> getCombinationItemList() {

        return combinationItemList;
    }

    public void setCombinationItemList(List<CombinationItemVO> combinationItemList) {

        this.combinationItemList = combinationItemList;
    }

    public GiftCoupon getGiftCoupon() {

        return giftCoupon;
    }

    public void setGiftCoupon(GiftCoupon giftCoupon) {

        this.giftCoupon = giftCoupon;
    }

    public CouponItems getCouponItems() {

        return couponItems;
    }

    public void setCouponItems(CouponItems couponItems) {

        this.couponItems = couponItems;
    }

    public String getDeliveryDateStr() {

        return deliveryDateStr;
    }

    public void setDeliveryDateStr(String deliveryDateStr) {

        this.deliveryDateStr = deliveryDateStr;
    }

    public String getPayDateStr() {

        return payDateStr;
    }

    public void setPayDateStr(String payDateStr) {

        this.payDateStr = payDateStr;
    }

    public int getInvoiceType() {

        return invoiceType;
    }

    public void setInvoiceType(int invoiceType) {

        this.invoiceType = invoiceType;
    }

    public int getPaymentMode() {

        return paymentMode;
    }

    public void setPaymentMode(int paymentMode) {

        this.paymentMode = paymentMode;
    }

    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
    }

    public String getMerchantImage() {

        return merchantImage;
    }

    public void setMerchantImage(String merchantImage) {

        this.merchantImage = merchantImage;
    }

    public double getCounpon() {

        return counpon;
    }

    public void setCounpon(double counpon) {

        this.counpon = counpon;
    }

    public double getPostage() {

        return postage;
    }

    public void setPostage(double postage) {

        this.postage = postage;
    }

    public double getSum() {

        return sum;
    }

    public void setSum(double sum) {

        this.sum = sum;
    }

    public double getCash() {

        return cash;
    }

    public void setCash(double cash) {

        this.cash = cash;
    }

    public double getPreferential() {

        return preferential;
    }

    public void setPreferential(double preferential) {

        this.preferential = preferential;
    }

    public String getConsignee() {

        return consignee;
    }

    public void setConsignee(String consignee) {

        this.consignee = consignee;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getMobile() {

        return mobile;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    public String getDeliveryModeStr() {

        return deliveryModeStr;
    }

    public void setDeliveryModeStr(String deliveryModeStr) {

        this.deliveryModeStr = deliveryModeStr;
    }

    public String getInvoiceTypeStr() {

        return invoiceTypeStr;
    }

    public void setInvoiceTypeStr(String invoiceTypeStr) {

        this.invoiceTypeStr = invoiceTypeStr;
    }

    public String getPaymentModeStr() {

        return paymentModeStr;
    }

    public void setPaymentModeStr(String paymentModeStr) {

        this.paymentModeStr = paymentModeStr;
    }

    public String getStateStr() {

        return stateStr;
    }

    public void setStateStr(String stateStr) {

        this.stateStr = stateStr;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getMerchandiseNumber() {

        return merchandiseNumber;
    }

    public void setMerchandiseNumber(int merchandiseNumber) {

        this.merchandiseNumber = merchandiseNumber;
    }

    public String getResidualTime() {

        return residualTime;
    }

    public void setResidualTime(String residualTime) {

        this.residualTime = residualTime;
    }
}
