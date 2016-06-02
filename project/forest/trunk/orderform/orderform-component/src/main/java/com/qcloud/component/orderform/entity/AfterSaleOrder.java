package com.qcloud.component.orderform.entity;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.component.orderform.QAfterSaleOrder;

public abstract class AfterSaleOrder implements QAfterSaleOrder {

    protected List<AfterSaleOrderItem> list = new ArrayList<AfterSaleOrderItem>();

    public abstract MerchantOrderEntity getMerchantOrder();

    public abstract void setState(int state);

    private int     userState;

    private String  userStateStr;

    private boolean finish;

    public List<AfterSaleOrderItem> getList() {

        return list;
    }

    public void setList(List<AfterSaleOrderItem> list) {

        this.list = list;
    }

    public int getUserState() {

        return userState;
    }

    public void setUserState(int userState) {

        this.userState = userState;
    }

    public String getUserStateStr() {

        return userStateStr;
    }

    public void setUserStateStr(String userStateStr) {

        this.userStateStr = userStateStr;
    }

    public final boolean isFinish() {

        return finish;
    }

    public void setFinish(boolean finish) {

        this.finish = finish;
    }
}
