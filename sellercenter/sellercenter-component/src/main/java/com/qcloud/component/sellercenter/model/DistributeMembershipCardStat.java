package com.qcloud.component.sellercenter.model;

public class DistributeMembershipCardStat {

    private long   merchantId;

    private String merchantCode;

    private String merchantName;

    private int    sendNum;

    private int    sendedNum;

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
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

    public int getSendNum() {

        return sendNum;
    }

    public void setSendNum(int sendNum) {

        this.sendNum = sendNum;
    }

    public int getSendedNum() {

        return sendedNum;
    }

    public void setSendedNum(int sendedNum) {

        this.sendedNum = sendedNum;
    }
}
