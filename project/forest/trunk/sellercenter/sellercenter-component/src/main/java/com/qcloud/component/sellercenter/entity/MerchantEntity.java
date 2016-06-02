package com.qcloud.component.sellercenter.entity;

import com.qcloud.component.sellercenter.CommodityAuditingType;
import com.qcloud.component.sellercenter.DistributionType;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.model.Merchant;
import com.qcloud.component.sellercenter.model.key.TypeEnum.NotifyType;

public class MerchantEntity implements QMerchant {

    private Merchant merchant;

    public MerchantEntity(Merchant merchant) {

        super();
        this.merchant = merchant;
    }

    @Override
    public long getKey() {

        return merchant.getId();
    }

    @Override
    public String getValue() {

        return merchant.getName();
    }

    @Override
    public long getId() {

        return merchant.getId();
    }

    @Override
    public String getName() {

        return merchant.getName();
    }

    @Override
    public CommodityAuditingType getCommodityAuditing() {

        CommodityAuditingType commodityAuditing = CommodityAuditingType.NEED;
        if (merchant.getCommodityAuditing() == CommodityAuditingType.UNNEED.getKey()) {
            commodityAuditing = CommodityAuditingType.UNNEED;
        }
        return commodityAuditing;
    }

    @Override
    public DistributionType getDistribution() {

        DistributionType distribution = DistributionType.YES;
        if (merchant.getDistribution() == DistributionType.NO.getKey()) {
            distribution = DistributionType.NO;
        }
        return distribution;
    }

    @Override
    public Long getClassifyId() {

        return merchant.getClassifyId();
    }

    @Override
    public String getImage() {

        return merchant.getImage();
    }

    @Override
    public long getUserId() {

        return merchant.getUserId();
    }

    @Override
    public int getMerchantType() {

        return merchant.getMerchantType();
    }

    @Override
    public String getProvince() {

        return merchant.getProvince();
    }

    @Override
    public String getCity() {

        return merchant.getCity();
    }

    @Override
    public String getReceiveMobile() {

        return merchant.getReceiveMobile();
    }

    @Override
    public boolean isNotify() {

        return NotifyType.Yes.getKey() == merchant.getNotify();
    }

    @Override
    public int hashCode() {

        return new Long(merchant.getId()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof QMerchant) {
            return ((QMerchant) obj).getId() == getId();
        }
        return false;
    }

    @Override
    public int getIsCertified() {

        return merchant.getIsCertified();
    }

    @Override
    public int getIsSpecialService() {

        return merchant.getIsSpecialService();
    }

    @Override
    public int getIsNoReason() {

        return merchant.getIsNoReason();
    }

    @Override
    public int getIsExternalUrl() {

        return merchant.getIsExternalUrl();
    }

    @Override
    public String getCode() {

        return merchant.getCode();
    }

    @Override
    public String getMerchantAdmin() {

        return merchant.getAdmin();
    }

    @Override
    public int getIsIncludePost() {

        return merchant.getIsIncludePost();
    }
}
