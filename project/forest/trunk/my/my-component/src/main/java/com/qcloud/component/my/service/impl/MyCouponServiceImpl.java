package com.qcloud.component.my.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.my.dao.MyCouponDao;
import com.qcloud.component.my.model.MyCoupon;
import com.qcloud.component.my.model.query.MyCouponQuery;
import com.qcloud.component.my.service.MyCouponService;
import com.qcloud.pirates.data.Page;

@Service
public class MyCouponServiceImpl implements MyCouponService {

    @Autowired
    private MyCouponDao         myCouponDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "personalcenter_my_coupon";

    @Override
    public boolean add(MyCoupon myCoupon) {

        long id = autoIdGenerator.get(ID_KEY);
        myCoupon.setId(id);
        myCoupon.setExtractDate(new Date());
        return myCouponDao.add(myCoupon);
    }

    @Override
    public MyCoupon get(Long id) {

        return myCouponDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return myCouponDao.delete(id);
    }

    @Override
    public boolean update(MyCoupon myCoupon) {

        return myCouponDao.update(myCoupon);
    }

    @Override
    public Page<MyCoupon> page(MyCouponQuery query, int start, int count) {

        return myCouponDao.page(query, start, count);
    }

    public List<MyCoupon> listAll() {

        return myCouponDao.listAll();
    }

    @Override
    public List<MyCoupon> listByUser(long userId, Integer type, int start, int count) {

        return myCouponDao.listByUser(userId, type, start, count);
    }

    @Override
    public List<MyCoupon> listCanUseByUser(long userId, Long merchantId, double sum, int start, int count) {

        return myCouponDao.listCanUseByUser(userId, merchantId, sum, start, count);
    }

    @Override
    public List<MyCoupon> listByUserAndCoupon(long userId, long couponId) {

        return myCouponDao.listByUserAndCoupon(userId, couponId);
    }

    @Override
    public MyCoupon getByCode(String code) {

        return myCouponDao.getByCode(code);
    }

    @Override
    public int countByUser(long userId, Integer type) {

        return myCouponDao.countByUser(userId, type);
    }
}
