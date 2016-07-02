package com.qcloud.component.orderform.web.vo.personal;

import java.util.ArrayList;
import java.util.List;

public class OrderVO extends AbstractOrderVO {

    private String            explain;

    private List<OrderItemVO> orderItemList = new ArrayList<OrderItemVO>();

    public List<OrderItemVO> getOrderItemList() {

        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemVO> orderItemList) {

        this.orderItemList = orderItemList;
    }

    public String getExplain() {

        return explain;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }
}
