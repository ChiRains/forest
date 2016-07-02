package com.qcloud.component.sellercenter.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class DistributeMembershipCardVO {

    // ID
    private long   id;

    // 商家ID
    private long   merchantId;

    // 职员ID
    private long   memberId;

    // 卡号
    private String cardNumber;

    // 状态 1待发卡 2已发卡
    private int    state;

    // 发卡时间
    private Date   time;

    public DistributeMembershipCardVO() {

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

    public long getMemberId() {

        return memberId;
    }

    public void setMemberId(long memberId) {

        this.memberId = memberId;
    }
}
