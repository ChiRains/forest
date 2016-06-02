package com.qcloud.component.warehouse.web.vo;

public class MerchandiseStockStateVO {

    // ID
    private long          id;

    // 商品库存出入货id
    private long          stockStateId;

    // 唯一商品id
    private long          unifiedMerchandiseId;

    // 数量
    private int           number;

    // 状态: 1-申请 2-确定 3-签收
    private int           state;

    public MerchandiseStockStateVO() {

    }

    public MerchandiseStockStateVO(long id, long stockStateId, long unifiedMerchandiseId, int number, int state) {

        this.id = id;
        this.stockStateId = stockStateId;
        this.unifiedMerchandiseId = unifiedMerchandiseId;
        this.number = number;
        this.state = state;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setStockStateId(long stockStateId) {

        this.stockStateId = stockStateId;
    }

    public long getStockStateId() {

        return stockStateId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public int getNumber() {

        return number;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }
}
