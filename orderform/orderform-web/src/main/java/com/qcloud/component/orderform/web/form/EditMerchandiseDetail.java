package com.qcloud.component.orderform.web.form;

public class EditMerchandiseDetail {

    private Long    orderItemId;

    // 商品ID
    private Long    unifiedMerchandiseId;

    // 数量
    private Integer number;

    public EditMerchandiseDetail() {

        super();
    }

    public EditMerchandiseDetail(Long orderItemId, Long unifiedMerchandiseId, Integer number) {

        this.orderItemId = orderItemId;
        this.unifiedMerchandiseId = unifiedMerchandiseId;
        this.number = number;
    }

    public Long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(Long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public Integer getNumber() {

        return number == null || number < 1 ? 0 : number;
    }

    public void setNumber(Integer number) {

        this.number = number;
    }

    public Long getOrderItemId() {

        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {

        this.orderItemId = orderItemId;
    }
}
