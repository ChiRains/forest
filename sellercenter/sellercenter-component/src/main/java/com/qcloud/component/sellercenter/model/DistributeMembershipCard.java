package com.qcloud.component.sellercenter.model;

import java.util.Date;

public class DistributeMembershipCard {

    // ID
    private long   id;

    // 商家ID
    private long   merchantId;

    private String merchantCode;

    private String merchantName;

    // 职员ID
    private long   memberId;

    // 卡号
    private String cardNumber;

    // 状态 1待发卡 2已发卡
    private int    state;

    // 发卡时间
    private Date   time;

    public DistributeMembershipCard() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public long getMerchantId() {

        return merchantId;
    }

    public void setCardNumber(String cardNumber) {

        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {

        return cardNumber;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }

    public void setTime(Date time) {

        this.time = time;
    }

    public Date getTime() {

        return time;
    }

    public String getMerchantCode() {

        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {

        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
    }

    public long getMemberId() {

        return memberId;
    }

    public void setMemberId(long memberId) {

        this.memberId = memberId;
    }
}
