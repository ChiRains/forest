package com.qcloud.component.orderform.model;

import java.util.List;
import org.apache.commons.collections.CollectionUtils;

public class OrderStateDefination {

    private List<OrderState> stateList;

    private List<int[]>      chain;

    public List<OrderState> getStateList() {

        return stateList;
    }

    public void setStateList(List<OrderState> stateList) {

        this.stateList = stateList;
    }

    public List<int[]> getChain() {

        return chain;
    }

    public void setChain(List<int[]> chain) {

        this.chain = chain;
    }

    public OrderState get(int state) {

        if (CollectionUtils.isNotEmpty(stateList)) {
            for (OrderState orderState : stateList) {
                if (orderState.getOrderState() == state) {
                    return orderState;
                }
            }
        }
        return null;
    }

    public OrderState getInit() {

        if (CollectionUtils.isNotEmpty(stateList)) {
            for (OrderState orderState : stateList) {
                if (orderState.isInit()) {
                    return orderState;
                }
            }
        }
        return null;
    }
}
