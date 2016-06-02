package com.qcloud.component.warehouse.model.query;

public class StockStateQuery {

    private String name;

    private Long   formStoreId;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Long getFormStoreId() {

        return formStoreId;
    }

    public void setFormStoreId(Long formStoreId) {

        this.formStoreId = formStoreId;
    }
}
