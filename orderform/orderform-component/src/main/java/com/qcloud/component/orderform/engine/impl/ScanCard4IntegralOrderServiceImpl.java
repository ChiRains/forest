package com.qcloud.component.orderform.engine.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.marketing.MarketingClient;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.my.QMyCoupon;
import com.qcloud.component.orderform.engine.ScanCard4IntegralOrderService;
import com.qcloud.component.orderform.model.MembershipCardOrder;
import com.qcloud.component.orderform.model.MembershipCardOrderCoupon;
import com.qcloud.component.orderform.service.MembershipCardOrderCouponService;
import com.qcloud.component.orderform.service.MembershipCardOrderService;
import com.qcloud.component.orderform.service.OrderNumberService;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.QStore;

@Component
public class ScanCard4IntegralOrderServiceImpl implements ScanCard4IntegralOrderService {

    @Autowired
    private MembershipCardOrderService       membershipCardOrderService;

    @Autowired
    private MembershipCardOrderCouponService membershipCardOrderCouponService;

    @Autowired
    private MarketingClient                  marketingClient;

    @Autowired
    private OrderNumberService               orderNumberService;

    @Autowired
    private MyClient                         myClient;

    @Transactional
    @Override
    public MembershipCardOrder order(QUser user, QMerchant merchant, QStore store, long memberId, Double sum, List<QMyCoupon> myCouponList) {

        double lessCash = sum;
        List<MembershipCardOrderCoupon> orderCouponList = new ArrayList<MembershipCardOrderCoupon>();
        double couponCash = 0.0;
        for (QMyCoupon qMyCoupon : myCouponList) {
            double limitPrice = qMyCoupon.getLimitPrice();
            // 优惠面值 :
            // TODO 优惠劵计算可能有问题
            double price = qMyCoupon.getPrice();
            if (limitPrice < lessCash && new Date().before(qMyCoupon.getValidDate())) {
                if (lessCash < price) {
                    price = lessCash;
                }
                lessCash = lessCash - limitPrice;
                // 优惠记录
                MembershipCardOrderCoupon membershipCardOrderCoupon = new MembershipCardOrderCoupon();
                membershipCardOrderCoupon.setCoupon(price);
                membershipCardOrderCoupon.setMyCouponId(qMyCoupon.getId());
                membershipCardOrderCoupon.setOrderId(-1L);
                orderCouponList.add(membershipCardOrderCoupon);
                couponCash += price;
            }
        }
        MembershipCardOrder membershipCardOrder = new MembershipCardOrder();
        membershipCardOrder.setTime(new Date());
        membershipCardOrder.setCash(sum - couponCash);
        membershipCardOrder.setMerchantId(merchant.getId());
        membershipCardOrder.setStoreId(store.getId());
        membershipCardOrder.setSum(sum);
        membershipCardOrder.setUserId(user.getId());
        membershipCardOrder.setStoreId(store.getId());
        membershipCardOrder.setMerchantId(merchant.getId());
        membershipCardOrder.setOrderNumber(orderNumberService.generate());
        membershipCardOrderService.add(membershipCardOrder);
        for (MembershipCardOrderCoupon membershipCardOrderCoupon : orderCouponList) {
            membershipCardOrderCoupon.setOrderId(membershipCardOrder.getId());
            membershipCardOrderCouponService.add(membershipCardOrderCoupon);
            QMyCoupon mc = null;
            for (QMyCoupon myCoupon : myCouponList) {
                if (myCoupon.getId() == membershipCardOrderCoupon.getMyCouponId()) {
                    mc = myCoupon;
                    break;
                }
            }
            // TODO 区分优惠劵是商城订单还是扫卡积分
            myClient.useMyCoupon(user.getId(), mc.getId(), membershipCardOrder.getId(), membershipCardOrder.getTime());
            marketingClient.useCoupon(mc.getCouponItemId());
        }
        return membershipCardOrder;
    }
}
