package com.qcloud.component.marketing.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.marketing.dao.CouponDao;
import com.qcloud.component.marketing.exception.MarketingException;
import com.qcloud.component.marketing.model.Coupon;
import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.model.key.TypeEnum.CouponType;
import com.qcloud.component.marketing.model.query.CouponQuery;
import com.qcloud.component.marketing.service.CouponItemsService;
import com.qcloud.component.marketing.service.CouponService;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.my.QMyCoupon;
import com.qcloud.component.parameter.model.key.TypeEnum.ParamEnableType;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.DateUtil;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponDao           couponDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    @Autowired
    private FileSDKClient       fileSDKClient;

    private static final String ID_KEY = "marketing_coupon";

    @Autowired
    private CouponItemsService  couponItemsService;

    @Autowired
    private MyClient            myClient;

    @Override
    public boolean add(Coupon coupon) {

        long id = autoIdGenerator.get(ID_KEY);
        coupon.setId(id);
        coupon.setImage(fileSDKClient.uidToUrl(coupon.getImage()));
        return couponDao.add(coupon);
    }

    @Override
    public Coupon getActivityCoupon(long merchantId) {

        List<Coupon> list = couponDao.listActivityCoupon(merchantId);
        for (Coupon coupon : list) {
            List<CouponItems> itemList = couponItemsService.list4Extract(coupon.getId());
            if (itemList.size() > 0) {
                return coupon;
            }
        }
        return null;
    }

    @Override
    public Long extractCoupon(Long userId, Coupon coupon) {

        if (canExtract(userId, coupon)) {
            return couponItemsService.extractCoupon(userId, coupon);
        }
        return -1L;
    }

    @Override
    public Long extractCouponItem(Long userId, CouponItems couponItems) {

        Coupon coupon = get(couponItems.getCouponID());
        if (canExtract(userId, coupon)) {
            return couponItemsService.extractCouponItem(userId, coupon, couponItems);
        }
        return -1L;
    }

    @Override
    public Coupon get(Long id) {

        return couponDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return couponDao.delete(id);
    }

    @Override
    public boolean update(Coupon coupon) {

        return couponDao.update(coupon);
    }

    @Override
    public Page<Coupon> page(CouponQuery query, int start, int count) {

        return couponDao.page(query, start, count);
    }

    public List<Coupon> listAll() {

        return couponDao.listAll();
    }

    @Override
    public boolean canExtract(Long userId, Coupon coupon) {

        if (coupon == null) {
            throw new MarketingException("优惠劵不存在.");
        }
        if (coupon.getEndDate().before(new Date())) {
            throw new MarketingException("优惠券活动已过期.");
        }
        if (coupon.getEnable() == ParamEnableType.DISABLE.getKey()) {
            throw new MarketingException("优惠券活动已经停止.");
        }
        List<QMyCoupon> list = myClient.listExtractCouponByUser(userId, coupon.getId());
        double sum = 0.0;
        Date date = coupon.getStartDate();
        for (QMyCoupon qMyCoupon : list) {
            sum += qMyCoupon.getPrice();
            if (date.before(qMyCoupon.getExtractDate())) {
                date = qMyCoupon.getExtractDate();
            }
        }
        if (sum >= coupon.getPriceOfPerson()) {
            return false;
        }
        if (list.size() >= coupon.getTotalOfPerson()) {
            return false;
        }
        if (list.size() > 0 && coupon.getTotalOfPerson() > 1 && DateUtil.addDate(date, coupon.getInterval()).after(new Date())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean existActivityCoupon(long merchantId) {

        return couponDao.countActivityCoupon(merchantId) > 0;
    }

    @Override
    public List<Coupon> listActivityCoupon(long merchantId) {

        List<Coupon> list = couponDao.listActivityCoupon(merchantId);
        List<Coupon> result = new ArrayList<Coupon>();
        for (Coupon coupon : list) {
            List<CouponItems> itemList = couponItemsService.list4Extract(coupon.getId());
            if (itemList.size() > 0) {
                result.add(coupon);
            }
        }
        return result;
    }

    @Override
    public List<Coupon> listByPlatform() {

        return couponDao.listByPlatform();
    }

    @Override
    public boolean canIntegralExtract(Long userId, Coupon coupon) {

        if (coupon == null) {
            throw new MarketingException("优惠劵不存在.");
        }
        if (coupon.getType() == CouponType.Normal.getKey()) {
            throw new MarketingException("优惠劵不存在.");
        }
        if (coupon.getEndDate().before(new Date())) {
            throw new MarketingException("优惠券活动已过期.");
        }
        if (coupon.getEnable() == ParamEnableType.DISABLE.getKey()) {
            throw new MarketingException("优惠券活动已经停止.");
        }
        // 积分优惠券无下列限制
        // List<QMyCoupon> list = myClient.listExtractCouponByUser(userId, coupon.getId());
        // double sum = 0.0;
        // Date date = coupon.getStartDate();
        // for (QMyCoupon qMyCoupon : list) {
        // sum += qMyCoupon.getPrice();
        // if (date.before(qMyCoupon.getExtractDate())) {
        // date = qMyCoupon.getExtractDate();
        // }
        // }
        // if (sum >= coupon.getPriceOfPerson()) {
        // return false;
        // }
        // if (list.size() >= coupon.getTotalOfPerson()) {
        // return false;
        // }
        // if (list.size() > 0 && coupon.getTotalOfPerson() > 1 && DateUtil.addDate(date, coupon.getInterval()).after(new Date())) {
        // return false;
        // }
        return true;
    }

    @Transactional
    @Override
    public Long extractIntegralCoupon(Long userId, Coupon coupon) {

        if (canIntegralExtract(userId, coupon)) {
            return couponItemsService.extractCoupon(userId, coupon);
        }
        return -1L;
    }
}
