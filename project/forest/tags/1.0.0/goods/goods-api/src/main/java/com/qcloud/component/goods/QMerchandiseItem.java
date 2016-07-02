package com.qcloud.component.goods;

public interface QMerchandiseItem {

    Long getId();

    // 不一定是QUnifiedMerchandise的ID
    Long getUnifiedMerchandiseId();

    long getMerchantId();

    long getMallClassifyId();

    long getMerchantClassifyId();

    long getMerchandiseId();

    String getName();

    String getCode();

    String getImage();

    long getSalesVolume();

    String getSpecifications();

    int getNumber();

    String getUnit();

    // 差评数量
    long getLowEvaluation();

    // 中评数量
    long getMiddleEvaluation();

    // 好评数量
    long getGoodEvaluation();
}
