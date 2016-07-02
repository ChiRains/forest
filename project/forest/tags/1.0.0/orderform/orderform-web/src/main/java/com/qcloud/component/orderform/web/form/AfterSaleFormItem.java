package com.qcloud.component.orderform.web.form;

public class AfterSaleFormItem {

    private Long   orderItemId;
//
////    private Long   orderItemDetailId;

    private int    number;

    private String reason;

    private String explain;

    public Long getOrderItemId() {

        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {

        this.orderItemId = orderItemId;
    }

    // public Long getOrderItemDetailId() {
    //
    // return orderItemDetailId;
    // }
    //
    // public void setOrderItemDetailId(Long orderItemDetailId) {
    //
    // this.orderItemDetailId = orderItemDetailId;
    // }

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

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }
}
