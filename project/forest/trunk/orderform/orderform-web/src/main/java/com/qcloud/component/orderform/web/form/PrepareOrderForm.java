package com.qcloud.component.orderform.web.form;

import java.util.List;

public class PrepareOrderForm {

    private String                  expressCode;

    // 购买商品列表
    private List<MerchandiseDetail> merchandiseList;

    public List<MerchandiseDetail> getMerchandiseList() {

        return merchandiseList;
    }

    public void setMerchandiseList(List<MerchandiseDetail> merchandiseList) {

        this.merchandiseList = merchandiseList;
    }

    public String getExpressCode() {

        return expressCode;
    }

    public void setExpressCode(String expressCode) {

        this.expressCode = expressCode;
    }
}
