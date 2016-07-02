package com.qcloud.component.goods;

import java.util.List;

public interface CommoditycenterClient {

    int SEARCH_TYPE = 1;

    //
    QUnifiedMerchandise getUnifiedMerchandise(long unifiedMerchandiseId);

    QMerchandiseEvaluation getMerchandiseEvaluation(long evaluationId, long merchandiseId);

    // 更新线上库存,只能更新单一商品的库存,stock为差额
    public boolean updateOnlineStock(long unifiedMerchandiseId, int stock);

    // 锁定库存
    public boolean lockOnlineStock(long unifiedMerchandiseId, int stock);

    // 在买家付款时修改,在买家申请退款确认退款成功后修改
    boolean addSalesVolume(long unifiedMerchandiseId, int number);

    void incrementMerchandiseDealRecoed(long userId, long unifiedMerchandiseId, int number, String specifications, long[] orderIds);

    List<QUnifiedMerchandise> randomUnifiedMerchandise(long merchantId, int number);

    boolean setVipDiscount(long userId, long unifiedMerchandiseId, double discount);

    double getVipDiscount(long userId, long unifiedMerchandiseId);

    double statMinVipDiscount(long unifiedMerchandiseId);

    double statMaxVipDiscount(long unifiedMerchandiseId);

    boolean clearUserVipDiscount(long userId);
}
