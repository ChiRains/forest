package com.qcloud.component.orderform.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// subOrderIdList itemList 二选一
public class AfterSaleForm {

    private Long                    orderId;

    private Date                    orderDate;

    // 1 退/换总单 2退/换子单 3退/换订单项或退/换订单明细 默认总单
    private Integer                 type         = 1;

    //
    private List<Long>              subOrderList = new ArrayList<Long>();

    //
    private List<AfterSaleFormItem> itemList     = new ArrayList<AfterSaleFormItem>();

    //
    private String                  explain;

    //
    private String                  reason;

    private double                  afterSaleSum;

    private String                  afterSaleImage;

    // 1仅退款2退货退款
    private Integer                 returnType   = 1;

    public Long getOrderId() {

        return orderId;
    }

    public void setOrderId(Long orderId) {

        this.orderId = orderId;
    }

    public Date getOrderDate() {

        return orderDate;
    }

    public void setOrderDate(Date orderDate) {

        this.orderDate = orderDate;
    }

    public List<Long> getSubOrderList() {

        return subOrderList;
    }

    public void setSubOrderList(List<Long> subOrderList) {

        this.subOrderList = subOrderList;
    }

    public List<AfterSaleFormItem> getItemList() {

        return itemList;
    }

    public void setItemList(List<AfterSaleFormItem> itemList) {

        this.itemList = itemList;
    }

    public Integer getType() {

        return type;
    }

    public void setType(Integer type) {

        this.type = type;
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

    public double getAfterSaleSum() {

        return afterSaleSum;
    }

    public void setAfterSaleSum(double afterSaleSum) {

        this.afterSaleSum = afterSaleSum;
    }

    public String getAfterSaleImage() {

        return afterSaleImage;
    }

    public void setAfterSaleImage(String afterSaleImage) {

        this.afterSaleImage = afterSaleImage;
    }

    public Integer getReturnType() {

        return returnType;
    }

    public void setReturnType(Integer returnType) {

        this.returnType = returnType;
    }
}
