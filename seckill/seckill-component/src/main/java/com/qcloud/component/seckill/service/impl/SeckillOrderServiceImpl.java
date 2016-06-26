package com.qcloud.component.seckill.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.seckill.dao.SeckillOrderDao;
import com.qcloud.component.seckill.model.MerchandiseSeckill;
import com.qcloud.component.seckill.model.Screenings;
import com.qcloud.component.seckill.model.SeckillOrder;
import com.qcloud.component.seckill.model.query.SeckillOrderQuery;
import com.qcloud.component.seckill.service.MerchandiseSeckillService;
import com.qcloud.component.seckill.service.ScreeningsService;
import com.qcloud.component.seckill.service.SeckillOrderService;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {

    @Autowired
    private SeckillOrderDao           seckillOrderDao;

    @Autowired
    private AutoIdGenerator           autoIdGenerator;

    @Autowired
    private MerchandiseSeckillService merchandiseSeckillService;

    @Autowired
    private CommoditycenterClient     commoditycenterClient;

    @Autowired
    private ScreeningsService         screeningsService;

    @Autowired
    private OrderformClient           orderformClient;

    private static final String       ID_KEY = "seckill_seckill_order";

    @Override
    public boolean add(SeckillOrder seckillOrder) {

        long id = autoIdGenerator.get(ID_KEY);
        seckillOrder.setId(id);
        return seckillOrderDao.add(seckillOrder);
    }

    @Override
    public SeckillOrder get(Long id) {

        return seckillOrderDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return seckillOrderDao.delete(id);
    }

    @Override
    public boolean update(SeckillOrder seckillOrder) {

        return seckillOrderDao.update(seckillOrder);
    }

    @Override
    public Page<SeckillOrder> page(SeckillOrderQuery query, int start, int count) {

        return seckillOrderDao.page(query, start, count);
    }

    public List<SeckillOrder> listAll() {

        return seckillOrderDao.listAll();
    }

    @Transactional
    @Override
    public QOrder orderSeckill(QUser user, Long seckillMerchandiseId) {

        MerchandiseSeckill merchandiseSeckill = merchandiseSeckillService.get(seckillMerchandiseId);
        AssertUtil.assertNotNull(merchandiseSeckill, "秒杀记录不存在." + seckillMerchandiseId);
        Screenings screenings = screeningsService.get(merchandiseSeckill.getScreeningsId());
        AssertUtil.assertNotNull(screenings, "秒杀场次记录不存在." + merchandiseSeckill.getScreeningsId());
        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(merchandiseSeckill.getUnifiedMerchandiseId());
        AssertUtil.assertNotNull(unifiedMerchandise, "秒杀商品记录不存在." + merchandiseSeckill.getUnifiedMerchandiseId());
        QOrder order = orderformClient.orderSeckill(user, unifiedMerchandise);
        merchandiseSeckill.setSalesVolume(merchandiseSeckill.getSalesVolume() + 1);
        merchandiseSeckillService.update(merchandiseSeckill);
        SeckillOrder seckillOrder = new SeckillOrder();
        QMerchantOrder merchantOrder = order.getMerchantOrderList().get(0);
        QOrderItem orderItem = merchantOrder.getOrderItemList().get(0);
        seckillOrder.setCash(order.getCash());
        seckillOrder.setDiscount(merchandiseSeckill.getDiscountPrice());
        seckillOrder.setImage(orderItem.getImage());
        seckillOrder.setMerchantId(merchantOrder.getMerchantId());
        seckillOrder.setName(orderItem.getName());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setOrderNumber(order.getOrderNumber());
        seckillOrder.setPrice(orderItem.getPrice());
        seckillOrder.setSeckillMerchandiseId(seckillMerchandiseId);
        seckillOrder.setSeckillPrice(order.getCash());
        seckillOrder.setSum(order.getSum());
        seckillOrder.setTime(order.getOrderDate());
        seckillOrder.setUnifiedMerchandiseId(orderItem.getUnifiedMerchandiseId());
        seckillOrder.setUserId(order.getUserId());
        add(seckillOrder);
        return order;
    }

    @Override
    public boolean canKill(Long seckillMerchandiseId) {

        MerchandiseSeckill merchandiseSeckill = merchandiseSeckillService.get(seckillMerchandiseId);
        AssertUtil.assertNotNull(merchandiseSeckill, "秒杀记录不存在." + seckillMerchandiseId);
        Screenings screenings = screeningsService.get(merchandiseSeckill.getScreeningsId());
        AssertUtil.assertNotNull(screenings, "秒杀场次记录不存在." + merchandiseSeckill.getScreeningsId());
        AssertUtil.assertTrue(screenings.getBeginTime().before(new Date()), "秒杀场次尚未开始");
        AssertUtil.assertTrue(screenings.getEndTime().after(new Date()), "秒杀场次已经结束");
        AssertUtil.assertTrue(merchandiseSeckill.getOriginalStock() > merchandiseSeckill.getSalesVolume(), "已经秒完");
        return true;
    }
}
