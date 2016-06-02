package com.qcloud.component.warehouse.web.vo.admin;

public class AdminStockStateVO {

    // ID
    private long id;

    // 出货门店id
    private long formStoreId;

    // 要货门店id
    private long toStoreId;

    // 状态: 1-申请 2-确认 3-签收
    private int  state;

    public AdminStockStateVO() {

    }

    public AdminStockStateVO(long id, long formStoreId, long toStoreId, int state) {

        this.id = id;
        this.formStoreId = formStoreId;
        this.toStoreId = toStoreId;
        this.state = state;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setFormStoreId(long formStoreId) {

        this.formStoreId = formStoreId;
    }

    public long getFormStoreId() {

        return formStoreId;
    }

    public void setToStoreId(long toStoreId) {

        this.toStoreId = toStoreId;
    }

    public long getToStoreId() {

        return toStoreId;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }
}
