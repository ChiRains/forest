package com.qcloud.component.orderform.web.vo;

import java.util.ArrayList;
import java.util.List;

public class AfterSaleInfoDetailsVO {

    private int                              afterSaleType;

    private long                             afterSaleId;

    private String                           reason;

    private String                           explain;

    private double                           afterSaleSum;

    private double                           number;

    private String                           image;

    private List<AfterSaleInfoMerchandiseVO> list        = new ArrayList<AfterSaleInfoMerchandiseVO>();

    private int                              state;

    private String                           stateStr;

    private List<AfterSaleInfoMessage>       messageList = new ArrayList<AfterSaleInfoMessage>();

    public int getAfterSaleType() {

        return afterSaleType;
    }

    public void setAfterSaleType(int afterSaleType) {

        this.afterSaleType = afterSaleType;
    }

    public String getReason() {

        return reason;
    }

    public void setReason(String reason) {

        this.reason = reason;
    }

    public String getExplain() {

        return explain;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }

    public double getAfterSaleSum() {

        return afterSaleSum;
    }

    public void setAfterSaleSum(double afterSaleSum) {

        this.afterSaleSum = afterSaleSum;
    }

    public double getNumber() {

        return number;
    }

    public void setNumber(double number) {

        this.number = number;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public List<AfterSaleInfoMerchandiseVO> getList() {

        return list;
    }

    public void setList(List<AfterSaleInfoMerchandiseVO> list) {

        this.list = list;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public String getStateStr() {

        return stateStr;
    }

    public void setStateStr(String stateStr) {

        this.stateStr = stateStr;
    }

    public List<AfterSaleInfoMessage> getMessageList() {

        return messageList;
    }

    public void setMessageList(List<AfterSaleInfoMessage> messageList) {

        this.messageList = messageList;
    }

    public long getAfterSaleId() {

        return afterSaleId;
    }

    public void setAfterSaleId(long afterSaleId) {

        this.afterSaleId = afterSaleId;
    }
}
