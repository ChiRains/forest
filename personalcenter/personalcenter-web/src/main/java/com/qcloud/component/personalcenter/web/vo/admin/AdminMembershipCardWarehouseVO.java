package com.qcloud.component.personalcenter.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminMembershipCardWarehouseVO {

    // ID
    private long   id;

    // 卡号
    private String cardNumber;

    // 状态 1初始化 2已使用
    private int    state;

    private String stateStr;

    public AdminMembershipCardWarehouseVO() {

    }

    public AdminMembershipCardWarehouseVO(long id, String cardNumber, int state) {

        this.id = id;
        this.cardNumber = cardNumber;
        this.state = state;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
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

    public String getStateStr() {

        return stateStr;
    }

    public void setStateStr(String stateStr) {

        this.stateStr = stateStr;
    }
}
