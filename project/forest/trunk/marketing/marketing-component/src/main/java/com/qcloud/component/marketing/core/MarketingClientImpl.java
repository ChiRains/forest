package com.qcloud.component.marketing.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.marketing.MarketingClient;
import com.qcloud.component.marketing.QCoupon;
import com.qcloud.component.marketing.QFullReduces;
import com.qcloud.component.marketing.entity.FullReducesEntity;
import com.qcloud.component.marketing.model.Coupon;
import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.model.FullReduces;
import com.qcloud.component.marketing.service.CouponItemsService;
import com.qcloud.component.marketing.service.CouponService;
import com.qcloud.component.marketing.service.FullReducesService;
import com.qcloud.pirates.util.DateUtil;

@Service
public class MarketingClientImpl implements MarketingClient {

    @Autowired
    CouponItemsService couponItemsService;

    @Autowired
    CouponService      couponService;

    @Autowired
    FullReducesService fullReducesService;

    @Override
    public boolean useCoupon(long couponItemId) {

        CouponItems couponItems = couponItemsService.get(couponItemId);
        couponItems.setUsedNum(couponItems.getUsedNum() + 1);
        return couponItemsService.update(couponItems);
    }

    @Override
    public boolean canExtract(long userId, long couponId) {

        Coupon coupon = couponService.get(couponId);
        if (coupon == null) {
            return false;
        }
        // 赠送的优惠劵,不在商城首页领.// 把结束时间加到明天
        coupon.setEndDate(DateUtil.addDate(new Date(), 1));
        return couponService.canExtract(userId, coupon);
    }

    @Override
    public Long extractCoupon(long userId, long couponId) {

        Coupon coupon = couponService.get(couponId);
        if (coupon == null) {
            return -1L;
        }
        // 赠送的优惠劵,不在商城首页领.// 把结束时间加到明天
        coupon.setEndDate(DateUtil.addDate(new Date(), 1));
        return couponService.extractCoupon(userId, coupon);
    }

    @Override
    public boolean existCoupon(long couponId) {

        return couponService.get(couponId) != null;
    }

    @Override
    public QCoupon getActivityCoupon(long merchantId) {

        return couponService.getActivityCoupon(merchantId);
    }

    @Override
    public List<QCoupon> listByPlatform() {

        return new ArrayList<QCoupon>(couponService.listByPlatform());
    }

    @Override
    public QCoupon getCoupon(long id) {

        Coupon coupon = couponService.get(id);
        return coupon;
    }

    @Override
    public List<QFullReduces> listCurrentReduces() {

        List<FullReduces> list = fullReducesService.listCurrentReduces();
        List<QFullReduces> qList = new ArrayList<QFullReduces>();
        for (FullReduces fullReduces : list) {
            FullReducesEntity entity = new FullReducesEntity(fullReduces);
            qList.add(entity);
        }
        return qList;
    }

    @Override
    public QFullReduces getCanUseReduces(double sum) {

        QFullReduces appro = null;
        List<QFullReduces> qList = listCurrentReduces();
        for (QFullReduces fullReduces : qList) {
            if (fullReduces.getLimitPrice() <= sum) {
                if (appro == null) {
                    appro = fullReduces;
                } else {
                    if (fullReduces.getLimitPrice() - appro.getLimitPrice() > 0) {
                        appro = fullReduces;
                    }
                }
            }
        }
        return appro;
    }
}