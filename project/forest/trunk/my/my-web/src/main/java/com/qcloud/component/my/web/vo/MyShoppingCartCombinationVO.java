package com.qcloud.component.my.web.vo;

import java.util.ArrayList;
import java.util.List;

public class MyShoppingCartCombinationVO {

    // 商家ID
    private long                    merchantId;

    private String                  name;

    private String                  image;

    private List<CombinationListVO> combinationList = new ArrayList<CombinationListVO>();

    private List<MyShoppingCartVO>  merchandiseList = new ArrayList<MyShoppingCartVO>();

    private boolean                 coupon;

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public List<MyShoppingCartVO> getMerchandiseList() {

        return merchandiseList;
    }

    public void setMerchandiseList(List<MyShoppingCartVO> merchandiseList) {

        this.merchandiseList = merchandiseList;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public boolean isCoupon() {

        return coupon;
    }

    public void setCoupon(boolean coupon) {

        this.coupon = coupon;
    }

    public List<CombinationListVO> getCombinationList() {

        return combinationList;
    }

    public void setCombinationList(List<CombinationListVO> combinationList) {

        this.combinationList = combinationList;
    }
}
