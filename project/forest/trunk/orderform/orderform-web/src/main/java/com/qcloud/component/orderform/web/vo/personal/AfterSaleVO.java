package com.qcloud.component.orderform.web.vo.personal;

import java.util.ArrayList;
import java.util.List;

public class AfterSaleVO {

    private List<AfterSaleItemVO> itemList = new ArrayList<AfterSaleItemVO>();

    private Long                  id;

    public String                 merchantName;

    private int                   state;

    private String                timeStr;

    private String                afterSaleOrderNumber;

    private String                explain;

    private String                reason;

    private double                sum;

    private int                   type;

    // 卖家发货物流
    private String                logisticsCompany;

    // 卖家发货物流
    private String                logisticsNumber;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public String getTimeStr() {

        return timeStr;
    }

    public void setTimeStr(String timeStr) {

        this.timeStr = timeStr;
    }

    public String getAfterSaleOrderNumber() {

        return afterSaleOrderNumber;
    }

    public void setAfterSaleOrderNumber(String afterSaleOrderNumber) {

        this.afterSaleOrderNumber = afterSaleOrderNumber;
    }

    public String getExplain() {

        return explain;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }

    public String getReason() {

        return reason;
    }

    public void setReason(String reason) {

        this.reason = reason;
    }

    public double getSum() {

        return sum;
    }

    public void setSum(double sum) {

        this.sum = sum;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }

    public List<AfterSaleItemVO> getItemList() {

        return itemList;
    }

    public void setItemList(List<AfterSaleItemVO> itemList) {

        this.itemList = itemList;
    }

    public String getLogisticsCompany() {

        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {

        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsNumber() {

        return logisticsNumber;
    }

    public void setLogisticsNumber(String logisticsNumber) {

        this.logisticsNumber = logisticsNumber;
    }
}
