package com.qcloud.component.my.web.timer;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.my.model.MyCoupon;
import com.qcloud.component.my.service.MyCouponService;
import com.qcloud.pirates.core.timer.AbstractTimer;
import com.qcloud.pirates.core.timer.MinutePeriod;
import com.qcloud.pirates.core.timer.Period;

@Component
public class CouponInvalidTimer extends AbstractTimer {

    @Autowired
    private MyCouponService myCouponService;

    @Override
    public Period getPeriod() {

        return new MinutePeriod(100000);
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    @Override
    public void start() {

        List<MyCoupon> couponList = myCouponService.listAll();
        for (MyCoupon myCoupon : couponList) {
            if (myCoupon.getState() == 1) {
                if (myCoupon.getInvalidDate().before(new Date())) {
                    myCoupon.setState(3);
                    myCouponService.update(myCoupon);
                }
            }
        }
    }
}
