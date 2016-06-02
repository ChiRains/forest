package com.qcloud.component.marketing.service.impl;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.marketing.dao.CouponItemsDao;
import com.qcloud.component.marketing.model.Coupon;
import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.model.query.CouponItemsQuery;
import com.qcloud.component.marketing.service.CouponItemsService;
import com.qcloud.component.my.MyClient;
import com.qcloud.pirates.data.Page;

@Service
public class CouponItemsServiceImpl implements CouponItemsService {

    @Autowired
    private CouponItemsDao      couponItemsDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "marketing_coupon_items";

    @Autowired
    private MyClient            myClient;

    @Override
    public boolean add(CouponItems couponItems) {

        long id = autoIdGenerator.get(ID_KEY);
        couponItems.setId(id);
        return couponItemsDao.add(couponItems);
    }

    @Override
    public CouponItems get(Long id) {

        return couponItemsDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return couponItemsDao.delete(id);
    }

    @Override
    public boolean update(CouponItems couponItems) {

        return couponItemsDao.update(couponItems);
    }

    @Override
    public Page<CouponItems> page(CouponItemsQuery query, int start, int count) {

        return couponItemsDao.page(query, start, count);
    }

    public List<CouponItems> listAll() {

        return couponItemsDao.listAll();
    }

    @Override
    public List<CouponItems> list4Counpon(Long couponId) {

        return couponItemsDao.list4Counpon(couponId);
    }

    @Override
    public List<CouponItems> list4Extract(Long couponId) {

        return couponItemsDao.list4Extract(couponId);
    }

    @Override
    public Long extractCoupon(Long userId, Coupon coupon) {

        synchronized (new Long(coupon.getId())) {
            // 获取剩余优惠券列表
            List<CouponItems> list = list4Extract(coupon.getId());
            if (list.size() == 0) {
                return -1L;
            }
            CouponItems items = null;
            if (list.size() == 1) {
                items = list.get(0);
            } else {
                int index = new Random().nextInt(list.size());
                items = list.get(index);
                // 判断是否超出每人最大领取数量 /每人最大领取金额
            }
            return extractCoupon(userId, coupon, items);
        }
    }

    @Override
    public Long extractCouponItem(Long userId, Coupon coupon, CouponItems items) {

        synchronized (new Long(coupon.getId())) {
            List<CouponItems> list = list4Extract(coupon.getId());
            boolean exist = false;
            for (CouponItems couponItems : list) {
                if (couponItems.getId() == items.getId()) {
                    exist = true;
                    break;
                }
            }
            if (exist) {
                return extractCoupon(userId, coupon, items);
            } else {
                return -1L;
            }
        }
    }

    private Long extractCoupon(Long userId, Coupon coupon, CouponItems items) {

        Long myCouponId = myClient.extractCoupon(userId, items.getCouponID(), items.getId(), coupon.getValidDate(), items.getLimitPrice(), items.getName(), items.getPrice(), coupon.getMerchantId());
        items.setSendNum(items.getSendNum() + 1);
        update(items);
        return myCouponId;
    }

    @Override
    public List<CouponItems> listByCouponId(Long couponId) {

        return couponItemsDao.listByCouponId(couponId);
    }
}
