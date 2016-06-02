package com.qcloud.component.my.web.vo;

import java.util.ArrayList;
import java.util.List;

public class MyOrderFormComplexVO extends MyOrderFormSimpleVO {

    private List<MyOrderItemVO> orderItemList = new ArrayList<MyOrderItemVO>();

    public List<MyOrderItemVO> getOrderItemList() {

        return orderItemList;
    }

    public void setOrderItemList(List<MyOrderItemVO> orderItemList) {

        this.orderItemList = orderItemList;
    }
}
