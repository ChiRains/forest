package com.qcloud.component.orderform.model;

import java.util.Date;
import java.math.BigDecimal;

public class SubOrder {
	
	//ID
	private long id;		
	
	//总单ID
	private long orderId;		
	
	//商家ID
	private long merchantId;		
	
	//门店ID
	private long storeId;		
	
	//子单号
	private String orderNumber;		
	
	//订单金额
	private double sum;		
	
	//现金
	private double cash;		
	
	//积分
	private int integral;		
	
	//优惠劵
	private double coupon;		
	
	//邮费
	private double postage;		
	
	//消费币
	private double consumption;		
	
	//优惠面额
	private double preferential;		
	
	//状态,待付款,已付款,待发货,已发货,已签收
	private int state;		
	
	private int prestate;		
	
	//订单说明
	private String explain;		
	
	//自提地址
	private String pickupAddressStr;		
	
	//送货时间说明
	private String deliveryTimeStr;		
	
	//送货方式
	private int deliveryMode;		
	
	//快递公司编码
	private String expressCode;		
	
	//物流公司
	private String expressName;		
	
	//物流查询号
	private String expressNumber;		
	
	//发货时间
	private Date deliveryTime;		

	public SubOrder(){
	
	}

	public SubOrder(long id,long orderId,long merchantId,long storeId,String orderNumber,double sum,double cash,int integral,double coupon,double postage,double consumption,double preferential,int state,int prestate,String explain,String pickupAddressStr,String deliveryTimeStr,int deliveryMode,String expressCode,String expressName,String expressNumber,Date deliveryTime){
		this.id = id;		
		this.orderId = orderId;		
		this.merchantId = merchantId;		
		this.storeId = storeId;		
		this.orderNumber = orderNumber;		
		this.sum = sum;		
		this.cash = cash;		
		this.integral = integral;		
		this.coupon = coupon;		
		this.postage = postage;		
		this.consumption = consumption;		
		this.preferential = preferential;		
		this.state = state;		
		this.prestate = prestate;		
		this.explain = explain;		
		this.pickupAddressStr = pickupAddressStr;		
		this.deliveryTimeStr = deliveryTimeStr;		
		this.deliveryMode = deliveryMode;		
		this.expressCode = expressCode;		
		this.expressName = expressName;		
		this.expressNumber = expressNumber;		
		this.deliveryTime = deliveryTime;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
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
		
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}	
		
	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getSum() {
		return sum;
	}	
		
	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getCash() {
		return cash;
	}	
		
	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public int getIntegral() {
		return integral;
	}	
		
	public void setCoupon(double coupon) {
		this.coupon = coupon;
	}

	public double getCoupon() {
		return coupon;
	}	
		
	public void setPostage(double postage) {
		this.postage = postage;
	}

	public double getPostage() {
		return postage;
	}	
		
	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public double getConsumption() {
		return consumption;
	}	
		
	public void setPreferential(double preferential) {
		this.preferential = preferential;
	}

	public double getPreferential() {
		return preferential;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
	public void setPrestate(int prestate) {
		this.prestate = prestate;
	}

	public int getPrestate() {
		return prestate;
	}	
		
	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}	
		
	public void setPickupAddressStr(String pickupAddressStr) {
		this.pickupAddressStr = pickupAddressStr;
	}

	public String getPickupAddressStr() {
		return pickupAddressStr;
	}	
		
	public void setDeliveryTimeStr(String deliveryTimeStr) {
		this.deliveryTimeStr = deliveryTimeStr;
	}

	public String getDeliveryTimeStr() {
		return deliveryTimeStr;
	}	
		
	public void setDeliveryMode(int deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public int getDeliveryMode() {
		return deliveryMode;
	}	
		
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}

	public String getExpressCode() {
		return expressCode;
	}	
		
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressName() {
		return expressName;
	}	
		
	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}

	public String getExpressNumber() {
		return expressNumber;
	}	
		
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}	
		
}
