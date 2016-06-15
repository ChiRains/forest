package com.qcloud.component.commoditycenter;

import java.util.List;

public interface CommoditycenterClient {

    int SEARCH_TYPE = 1;

    //
    QUnifiedMerchandise getUnifiedMerchandise(long unifiedMerchandiseId);

    QMerchandiseEvaluation getMerchandiseEvaluation(long evaluationId, long merchandiseId);

    List<String> listMerchandiseImage(long unifiedMerchandiseId);

    // 更新线上库存,只能更新单一商品的库存,stock为差额
    public boolean updateOnlineStock(long unifiedMerchandiseId, int stock);

    // 锁定库存
    public boolean lockOnlineStock(long unifiedMerchandiseId, int stock);

    // 在买家付款时修改,在买家申请退款确认退款成功后修改
    boolean addSalesVolume(long unifiedMerchandiseId, int number);

    void incrementMerchandiseDealRecoed(Long userId, Long unifiedMerchandiseId, int number, String specifications, Long[] orderIds);

    List<QUnifiedMerchandise> randomUnifiedMerchandise(long merchantId, int number);

    boolean setVipDiscount(long userId, long unifiedMerchandiseId, double discount);

    Double getVipDiscount(long userId, long unifiedMerchandiseId);

    Double statMinVipDiscount(Long unifiedMerchandiseId);

    Double statMaxVipDiscount(Long unifiedMerchandiseId);

    boolean clearUserVipDiscount(long userId);
}
