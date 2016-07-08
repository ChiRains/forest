package com.qcloud.component.my.entity;

import java.util.Date;
import com.qcloud.component.my.QMyCoupon;
import com.qcloud.component.my.model.MyCoupon;

public class MyCouponEntity implements QMyCoupon {

    private MyCoupon myCoupon;

    public MyCouponEntity(MyCoupon myCoupon) {

        super();
        this.myCoupon = myCoupon;
    }

    public long getId() {

        return myCoupon.getId();
    }

    public long getCouponId() {

        return myCoupon.getCouponId();
    }

    public double getLimitPrice() {

        return myCoupon.getLimitPrice();
    }

    public long getCouponItemId() {

        return myCoupon.getCouponItemId();
    }

    public Date getValidDate() {

        return myCoupon.getValidDate();
    }

    public String getName() {

        return myCoupon.getName();
    }

    public double getPrice() {

        return myCoupon.getPrice();
    }

    public int getState() {

        return myCoupon.getState();
    }

    public Date getExtractDate() {

        return myCoupon.getExtractDate();
    }

    @Override
    public String getCode() {

        return myCoupon.getCode();
    }

    @Override
    public long getMerchantId() {

        return myCoupon.getMerchantId();
    }

    @Override
    public long getUserId() {

        return myCoupon.getUserId();
    }

    @Override
    public Date getInValidDate() {

        return myCoupon.getInvalidDate();
    }
}
