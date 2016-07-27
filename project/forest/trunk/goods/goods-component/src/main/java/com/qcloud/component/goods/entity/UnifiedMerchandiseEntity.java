package com.qcloud.component.goods.entity;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.UnifiedMerchandiseType;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum.MerchandiseIsIncludePost;

public class UnifiedMerchandiseEntity implements QUnifiedMerchandise {

    private UnifiedMerchandise        unifiedMerchandise;

    private Merchandise               merchandise;

    private int                       number = 1;

    private UnifiedMerchandiseType    type   = null;

    private String                    specifications;

    private List<QUnifiedMerchandise> list   = new ArrayList<QUnifiedMerchandise>();

    private double                    discount;

    private int                       combinationType;

    public UnifiedMerchandiseEntity(UnifiedMerchandise unifiedMerchandise, Merchandise merchandise) {

        super();
        this.unifiedMerchandise = unifiedMerchandise;
        this.merchandise = merchandise;
        this.discount = unifiedMerchandise.getDiscount();
    }

    @Override
    public Long getId() {

        return unifiedMerchandise.getId();
    }

    @Override
    public String getName() {

        return unifiedMerchandise.getName();
    }

    @Override
    public long getMallClassifyId() {

        return unifiedMerchandise.getMallClassifyId();
    }

    @Override
    public long getMerchantClassifyId() {

        return unifiedMerchandise.getMerchantClassifyId();
    }

    @Override
    public long getMerchandiseId() {

        return unifiedMerchandise.getMerchandiseId();
    }

    @Override
    public String getCode() {

        return unifiedMerchandise.getCode();
    }

    @Override
    public long getSalesVolume() {

        return unifiedMerchandise.getSalesVolume() + unifiedMerchandise.getVirtualSalesVolume();
    }

    public void setNumber(int number) {

        this.number = number;
    }

    @Override
    public int getNumber() {

        return number;
    }

    @Override
    public long getLowEvaluation() {

        return unifiedMerchandise.getLowEvaluation();
    }

    @Override
    public long getMiddleEvaluation() {

        return unifiedMerchandise.getMiddleEvaluation();
    }

    @Override
    public long getGoodEvaluation() {

        return unifiedMerchandise.getGoodEvaluation();
    }

    @Override
    public String getImage() {

        return unifiedMerchandise.getImage();
    }

    @Override
    public double getPrice() {

        return unifiedMerchandise.getPrice();
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    @Override
    public double getDiscount() {

        return discount;
    }

    @Override
    public double getPurchase() {

        return unifiedMerchandise.getPurchase();
    }

    @Override
    public int getStock() {

        return unifiedMerchandise.getStock();
    }

    @Override
    public long getMerchantId() {

        return unifiedMerchandise.getMerchantId();
    }

    @Override
    public double getWeight() {

        if (merchandise == null) {
            return 0;
        }
        return merchandise.getWeight();
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    @Override
    public String getSpecifications() {

        return specifications;
    }

    @Override
    public String getUnit() {

        return merchandise == null ? "" : merchandise.getUnit();
    }

    public void setList(List<QUnifiedMerchandise> list) {

        this.list = list;
    }

    @Override
    public List<QUnifiedMerchandise> getList() {

        return list;
    }

    public void setType(UnifiedMerchandiseType type) {

        this.type = type;
    }

    @Override
    public UnifiedMerchandiseType getType() {

        return type;
    }

    @Override
    public boolean getIsIncludePost() {

        return merchandise == null ? true : merchandise.getIsIncludePost() == MerchandiseIsIncludePost.YES.getKey();
    }

    @Override
    public String getSysCode() {

        return merchandise.getSysCode();
    }

    @Override
    public String getLabel() {

        return merchandise.getLabel();
    }

    @Override
    public long getIntegral() {

        return unifiedMerchandise.getIntegral();
    }

    @Override
    public int getCombinationType() {

        return combinationType;
    }

    public void setCombinationType(int combinationType) {

        this.combinationType = combinationType;
    }
}
