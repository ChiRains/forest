package com.qcloud.component.my.web.vo;

import java.util.ArrayList;
import java.util.List;

public class MyOrderFormMerchandiseVO extends MyOrderFormSimpleVO {

    private Long                merchantId    = -1L;

    private String              merchantName;

    private String              merchantImage = "";

    private int                 number;

    private List<MyOrderItemVO> orderItemList = new ArrayList<MyOrderItemVO>();

    public List<MyOrderItemVO> getOrderItemList() {

        return orderItemList;
    }

    public void setOrderItemList(List<MyOrderItemVO> orderItemList) {

        this.orderItemList = orderItemList;
    }

    public Long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
    }

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
    }

    public String getMerchantImage() {

        return merchantImage;
    }

    public void setMerchantImage(String merchantImage) {

        this.merchantImage = merchantImage;
    }
}
