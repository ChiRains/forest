package com.qcloud.component.sellercenter;

import com.qcloud.component.publicdata.IntKeyValue;

public interface QMerchant extends IntKeyValue {

    public long getId();

    public String getName();

    public CommodityAuditingType getCommodityAuditing();

    public DistributionType getDistribution();

    public Long getClassifyId();

    public String getImage();

    public long getUserId();

    public int getMerchantType();

    public String getProvince();

    public String getCity();

    public String getReceiveMobile();

    public boolean isNotify();

    public int getIsCertified();

    public int getIsSpecialService();

    public int getIsNoReason();

    public int getIsExternalUrl();
    
    public int getIsIncludePost();
    
    public String getCode();
    
    public String getMerchantAdmin();
}
