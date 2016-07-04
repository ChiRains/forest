package com.qcloud.component.sellercenter;

import com.qcloud.component.publicdata.IntKeyValue;

public interface QMerchant extends IntKeyValue {

    public long getId();

    public String getName();

    public String getCode();

    public String getImage();

    public String getProvince();

    public String getCity();

    public String getReceiveMobile();

    public int getIsIncludePost();

    public int getIsCertified();

    public int getIsSpecialService();

    public int getIsNoReason();
}
