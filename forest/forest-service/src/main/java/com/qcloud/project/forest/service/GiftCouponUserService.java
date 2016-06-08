package com.qcloud.project.forest.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.GiftCouponUser;
import com.qcloud.project.forest.model.query.GiftCouponUserQuery;

public interface GiftCouponUserService {

    public boolean add(GiftCouponUser giftCouponUser);

    public GiftCouponUser get(Long id);

    public boolean delete(Long id);

    public boolean update(GiftCouponUser giftCouponUser);

    public List<GiftCouponUser> listByUser(GiftCouponUserQuery query, int start, int size) ;

    public Page<GiftCouponUser> page(GiftCouponUserQuery query, int start, int count);

    public List<GiftCouponUser> listAll();

    public List<GiftCouponUser> listCanUse(long userId);

    public boolean judgeCanUse(long userId, long id);
}
