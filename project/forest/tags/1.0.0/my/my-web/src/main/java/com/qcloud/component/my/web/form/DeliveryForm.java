package com.qcloud.component.my.web.form;

public class DeliveryForm {

    // 类型 1物流 2门店自提 3送货上门
    private int    type;

    // 描述
    private String desc;

    // 门店
    private long   storeId;

    // 时间 门店自提时间或送货上门时间
    private String time;

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public long getStoreId() {

        return storeId;
    }

    public void setStoreId(long storeId) {

        this.storeId = storeId;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {

        this.time = time;
    }
}
