package com.qcloud.component.my.web.vo;

import java.util.ArrayList;
import java.util.List;

public class MyOrderFormMerchantVO extends MyOrderFormSimpleVO {

    private List<MyMerchantOrderItemVO> merchantOrderItemList = new ArrayList<MyMerchantOrderItemVO>();

    public List<MyMerchantOrderItemVO> getMerchantOrderItemList() {

        return merchantOrderItemList;
    }

    public void setMerchantOrderItemList(List<MyMerchantOrderItemVO> merchantOrderItemList) {

        this.merchantOrderItemList = merchantOrderItemList;
    }
}
