package com.qcloud.project.forest.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.GiftCouponUser;
import com.qcloud.project.forest.model.query.GiftCouponUserQuery;

public interface GiftCouponUserDao extends ISimpleDao<GiftCouponUser, Long> {

    public boolean add(GiftCouponUser giftCouponUser);

    public GiftCouponUser get(Long id);

    public boolean delete(Long id);

    public boolean update(GiftCouponUser giftCouponUser);

    public List<GiftCouponUser> list(List<Long> idList);

    public Map<Long, GiftCouponUser> map(Set<Long> idSet);

    public Page<GiftCouponUser> page(GiftCouponUserQuery query, int start, int size);

    public List<GiftCouponUser> listAll();

    public List<GiftCouponUser> listByUser(GiftCouponUserQuery query, int start, int size);

    public List<GiftCouponUser> listCanUse(long userId);

    public boolean judgeCanUse(long userId, long id);
}
