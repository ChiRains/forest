package com.qcloud.component.orderform.web.form;

public class MerchandiseDetail {

    // 商品ID
    private Long    unifiedMerchandiseId;

    // 数量
    private Integer number;

    public MerchandiseDetail() {

        super();
    }

    public Long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(Long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public Integer getNumber() {

        return number == null || number < 1 ? 1 : number;
    }

    public void setNumber(Integer number) {

        this.number = number;
    }
}
