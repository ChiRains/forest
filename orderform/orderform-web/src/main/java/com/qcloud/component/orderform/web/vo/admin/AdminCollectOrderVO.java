package com.qcloud.component.orderform.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminCollectOrderVO {
	
	//ID
	private long id;		
	
	//订单号
	private String orderNumber;		
	
	//用户ID
	private long userId;		
	
	//下单时间
	private Date time;		
	
	//订单金额
	private double sum;		
	
	//收货人
	private String consignee;		
	
	//收货地址
	private String address;		
	
	//收货电话
	private String mobile;		
	
	//收货人邮箱
	private String email;		
	
	//送货时间说明
	private String deliveryTimeStr;		
	
	//支付方式
	private int paymentMode;		
	
	//状态,待付款,已付款,待发货,发货中,已签收
	private int state;		
	
	//订单说明
	private String explain;
	
	//消费者名称
	private String customer;
	
	private String codeString;
	
	
	

	public AdminCollectOrderVO(){
	
	}
	
	public AdminCollectOrderVO(long id, String orderNumber, long userId, Date time, double sum, String consignee, String address, String mobile, String email, String deliveryTimeStr, int paymentMode, int state, String explain, String customer) {

        super();
        this.id = id;
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.time = time;
        this.sum = sum;
        this.consignee = consignee;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.deliveryTimeStr = deliveryTimeStr;
        this.paymentMode = paymentMode;
        this.state = state;
        this.explain = explain;
        this.customer = customer;
    }


    public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}	
		
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}	
		
	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}	
		
	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getSum() {
		return sum;
	}	
		
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsignee() {
		return consignee;
	}	
		
	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}	
		
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}	
		
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}	
		
	public void setDeliveryTimeStr(String deliveryTimeStr) {
		this.deliveryTimeStr = deliveryTimeStr;
	}

	public String getDeliveryTimeStr() {
		return deliveryTimeStr;
	}	
		
	public void setPaymentMode(int paymentMode) {
		this.paymentMode = paymentMode;
	}

	public int getPaymentMode() {
		return paymentMode;
	}	
		
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}	
		
	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

    
    public String getCustomer() {
    
        return customer;
    }

    
    public void setCustomer(String customer) {
    
        this.customer = customer;
    }

    
    public String getCodeString() {
    
        return codeString;
    }

    
    public void setCodeString(String codeString) {
    
        this.codeString = codeString;
    }	
		
}
