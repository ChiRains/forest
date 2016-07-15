package com.qcloud.component.goods;

public interface QMerchandise {

    public long getId();

    public long getMerchantClassifyId();

    public long getMallClassifyId();

    public long getSpecClassifyId();

    public long getMerchantId();

    public String getName();

    public String getSysCode();

    public String getImage();

    public String getKeywords();

    public int getWeight();

    public int getState();

    public String getUnit();

    public String getDetails();

    public String getDesc();

    public int getIsCertified();

    public int getIsSpecialService();

    public int getIsNoReason();

    public int getIsExternalUrl();

    public String getCertified();

    public String getSpecialService();

    public String getNoReason();

    public String getExternalUrl();

    public int getIsIncludePost();

    public long getBrandId();

    public String getLabel();

    /**
     * 大参林要看商品最低价格
     * @return
     */
    public double getLowPrice();

    public long getTotalSalesVolume();

    public String getHpRate();
}
