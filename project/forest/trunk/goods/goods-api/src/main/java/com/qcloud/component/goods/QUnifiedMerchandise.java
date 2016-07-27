package com.qcloud.component.goods;

import java.util.List;

public interface QUnifiedMerchandise {

    // 统一商品ID
    Long getId();

    // 商品名称
    String getName();

    // 商城商品分类
    long getMallClassifyId();

    // 商家商品分类
    long getMerchantClassifyId();

    // 商品档案
    long getMerchandiseId();

    // 商品编码
    String getCode();

    // 系统编码
    String getSysCode();

    // 销量
    long getSalesVolume();

    int getNumber();

    // 差评数量
    long getLowEvaluation();

    // 中评数量
    long getMiddleEvaluation();

    // 好评数量
    long getGoodEvaluation();

    // 缩略图
    String getImage();

    // 原价
    double getPrice();

    // 市场价,折扣价
    double getDiscount();

    // 进货价
    double getPurchase();

    // 库存
    int getStock();

    // 商家
    long getMerchantId();

    // 重量
    double getWeight();

    // 规格描述
    String getSpecifications();

    // 单位
    String getUnit();

    // 关联依赖商品
    List<QUnifiedMerchandise> getList();

    // 商品类型
    UnifiedMerchandiseType getType();

    int getCombinationType();

    // 是否包邮
    boolean getIsIncludePost();

    String getLabel();

    long getIntegral();
}
