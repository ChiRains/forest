package com.qcloud.project.forest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.GiftCouponUserDao;
import com.qcloud.project.forest.model.GiftCouponUser;
import com.qcloud.project.forest.service.GiftCouponUserService;
import com.qcloud.project.forest.model.query.GiftCouponUserQuery;

@Service
public class GiftCouponUserServiceImpl implements GiftCouponUserService {

    @Autowired
    private GiftCouponUserDao   giftCouponUserDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "forest_gift_coupon_user";

    @Override
    public boolean add(GiftCouponUser giftCouponUser) {

        long id = autoIdGenerator.get(ID_KEY);
        giftCouponUser.setId(id);
        giftCouponUser.setOrderId(-1);
        return giftCouponUserDao.add(giftCouponUser);
    }

    @Override
    public GiftCouponUser get(Long id) {

        return giftCouponUserDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return giftCouponUserDao.delete(id);
    }

    @Override
    public boolean update(GiftCouponUser giftCouponUser) {

        return giftCouponUserDao.update(giftCouponUser);
    }

    public List<GiftCouponUser> listByUserId(Long userId) {

        return giftCouponUserDao.listByUserId(userId);
    }

    @Override
    public Page<GiftCouponUser> page(GiftCouponUserQuery query, int start, int count) {

        return giftCouponUserDao.page(query, start, count);
    }

    public List<GiftCouponUser> listAll() {

        return giftCouponUserDao.listAll();
    }

    @Override
    public List<GiftCouponUser> listCanUse(long userId) {

        return giftCouponUserDao.listCanUse(userId);
    }
}
