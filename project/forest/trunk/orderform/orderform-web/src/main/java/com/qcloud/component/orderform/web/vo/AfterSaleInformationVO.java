package com.qcloud.component.orderform.web.vo;

public class AfterSaleInformationVO {

    private String  timeStr;

    private String  merchantName;

    private String  title;

    private String  content;

    private boolean result;

    private boolean user;

    public String getTimeStr() {

        return timeStr;
    }

    public void setTimeStr(String timeStr) {

        this.timeStr = timeStr;
    }

    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public boolean isResult() {

        return result;
    }

    public void setResult(boolean result) {

        this.result = result;
    }

    public boolean isUser() {

        return user;
    }

    public void setUser(boolean user) {

        this.user = user;
    }
}
