package com.qcloud.project.forest.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.orderform.OrderContext;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.project.forest.dao.ForestOrderDao;
import com.qcloud.project.forest.model.ForestOrder;
import com.qcloud.project.forest.model.GiftCouponUser;
import com.qcloud.project.forest.model.key.TypeEnum.GifeCouponStateType;
import com.qcloud.project.forest.model.query.ForestOrderQuery;
import com.qcloud.project.forest.service.ForestOrderService;
import com.qcloud.project.forest.service.GiftCouponUserService;

@Service
public class ForestOrderServiceImpl implements ForestOrderService {

    @Autowired
    private ForestOrderDao        forestOrderDao;

    @Autowired
    private AutoIdGenerator       autoIdGenerator;

    @Autowired
    private OrderformClient       orderformClient;

    @Autowired
    private GiftCouponUserService couponUserService;

    private static final String   ID_KEY = "forest_forest_order";

    @Override
    public boolean add(ForestOrder forestOrder) {

        long id = autoIdGenerator.get(ID_KEY);
        forestOrder.setId(id);
        return forestOrderDao.add(forestOrder);
    }

    @Override
    public ForestOrder get(Long id) {

        return forestOrderDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return forestOrderDao.delete(id);
    }

    @Override
    public boolean update(ForestOrder forestOrder) {

        return forestOrderDao.update(forestOrder);
    }

    @Override
    public Page<ForestOrder> page(ForestOrderQuery query, int start, int count) {

        return forestOrderDao.page(query, start, count);
    }

    public List<ForestOrder> listAll() {

        return forestOrderDao.listAll();
    }

    @Transactional
    @Override
    public QOrder order(OrderContext context, Long giftCouponUser, QUser user, Date deliveryDate) {

        QOrder order = orderformClient.orderNormal(context);
        // 赠品券
        long giftCouponId = -1;
        if (giftCouponUser != -1) {
            GiftCouponUser coupon = couponUserService.get(giftCouponUser);
            AssertUtil.assertNotNull(coupon, "赠品券不存在.");
            AssertUtil.assertTrue(couponUserService.judgeCanUse(user.getId(), giftCouponUser), "该赠品券已经使用过了,不能重复使用.");
            giftCouponId = coupon.getGiftCouponId();
            coupon.setState(GifeCouponStateType.Used.getKey());
            coupon.setOrderDate(order.getOrderDate());
            coupon.setOrderId(order.getId());
            couponUserService.update(coupon);
        }
        //
        ForestOrder forestOrder = new ForestOrder();
        forestOrder.setMerchantId(order.getMerchantOrderList().get(0).getMerchantId());
        forestOrder.setOrderDate(order.getOrderDate());
        forestOrder.setOrderId(order.getId());
        forestOrder.setOrderNumber(order.getOrderNumber());
        forestOrder.setGiftCouponId(giftCouponId);
        forestOrder.setUserId(user.getId());
        forestOrder.setDeliveryDate(deliveryDate);
        forestOrder.setDeliveryMode(order.getMerchantOrderList().get(0).getDeliveryMode());
        forestOrder.setStoreId(order.getMerchantOrderList().get(0).getStoreId());
        forestOrder.setState(orderformClient.getNormalMerchantOrderState(order.getState()));
        add(forestOrder);
        return order;
    }
}
