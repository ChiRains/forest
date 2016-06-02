package com.qcloud.component.sellercenter.web.vo.admin;


public class AdminDistributeMembershipCardVO {

    // 商家ID
    private String merchantName;

    // 职员ID
    private String memberMobile;

    // 卡号
    private String cardNumber;

    // 状态 1待发卡 2已发卡
    private String stateStr;

    // 发卡时间
    private String timeStr;

    public AdminDistributeMembershipCardVO() {

    }

    public void setCardNumber(String cardNumber) {

        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {

        return cardNumber;
    }

    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
    }

    public String getStateStr() {

        return stateStr;
    }

    public void setStateStr(String stateStr) {

        this.stateStr = stateStr;
    }

    public String getTimeStr() {

        return timeStr;
    }

    public void setTimeStr(String timeStr) {

        this.timeStr = timeStr;
    }

    public String getMemberMobile() {

        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {

        this.memberMobile = memberMobile;
    }
}
