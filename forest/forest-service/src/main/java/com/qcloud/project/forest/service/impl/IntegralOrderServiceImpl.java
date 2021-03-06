package com.qcloud.project.forest.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.autoid.UniqueCodeGenerator;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.service.PointMerchandiseService;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.my.QMyConsignee;
import com.qcloud.component.orderform.PaymentModeType;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QGrade;
import com.qcloud.component.personalcenter.QMyWealth;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.personalcenter.WealthType;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.project.forest.dao.IntegralOrderDao;
import com.qcloud.project.forest.model.IntegralOrder;
import com.qcloud.project.forest.model.RangeGrade;
import com.qcloud.project.forest.model.key.TypeEnum.IntegralOrderStateType;
import com.qcloud.project.forest.model.query.IntegralOrderQuery;
import com.qcloud.project.forest.service.IntegralOrderService;
import com.qcloud.project.forest.service.RangeGradeService;

@Service
public class IntegralOrderServiceImpl implements IntegralOrderService {

    @Autowired
    private IntegralOrderDao      integralOrderDao;

    @Autowired
    private AutoIdGenerator       autoIdGenerator;

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Autowired
    private MyClient              myClient;

    @Autowired
    private PersonalcenterClient  personalcenterClient;

    @Autowired
    private UniqueCodeGenerator   uniqueCodeGenerator;

    @Autowired
    private RangeGradeService     rangeGradeService;

    private static final String   INTEGRAL_ORDER_CODE = "forest-integral-order-code";

    private static final String   ID_KEY              = "forest_integral_order";

    @Override
    public boolean add(IntegralOrder integralOrder) {

        long id = autoIdGenerator.get(ID_KEY);
        integralOrder.setId(id);
        return integralOrderDao.add(integralOrder);
    }

    @Override
    public IntegralOrder get(Long id) {

        return integralOrderDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return integralOrderDao.delete(id);
    }

    @Override
    public boolean update(IntegralOrder integralOrder) {

        return integralOrderDao.update(integralOrder);
    }

    @Override
    public Page<IntegralOrder> page(IntegralOrderQuery query, int start, int count) {

        return integralOrderDao.page(query, start, count);
    }

    public List<IntegralOrder> listAll() {

        return integralOrderDao.listAll();
    }

    @Transactional
    @Override
    public IntegralOrder order(Long unifiedMerchandiseId, Long userId, Long consigneeId) {

        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(unifiedMerchandise, "积分商品不存在.");
        AssertUtil.assertTrue(unifiedMerchandise.getType().getKey() == PointMerchandiseService.unifiedMerchandise_type, "积分商品不存在.");
        QUser user = personalcenterClient.getUser(userId);
        RangeGrade rangeGrade = rangeGradeService.get(unifiedMerchandise.getActivityId());
        AssertUtil.assertNotNull(rangeGrade, "您的会员等级不足,暂时无法兑换哦");
        QGrade merchandiseGrade = personalcenterClient.getGrade(rangeGrade.getGradeId());
        QGrade grade = user.getGrade();
        AssertUtil.assertTrue(grade.getPoint() >= merchandiseGrade.getPoint(), "您的会员等级不足,暂时无法兑换哦");
        //
        QMyWealth myWealth = personalcenterClient.getMyWealth(user.getId());
        QMyConsignee consignee = myClient.getConsignee(consigneeId);
        AssertUtil.assertNotNull(consignee, "收货地址不存在.");
        // 拿到商品信息
        long integral = unifiedMerchandise.getIntegral();
        double cash = unifiedMerchandise.getDiscount();
        long myIntegral = myWealth.getIntegral();
        AssertUtil.assertTrue(myIntegral >= integral, "积分余额不足,无法购买商品.");
        //
        IntegralOrder integralOrder = new IntegralOrder();
        integralOrder.setAddress(consignee.getAddress());
        integralOrder.setConsignee(consignee.getName());
        integralOrder.setMobile(consignee.getMobile());
        integralOrder.setEmail(consignee.getEmail());
        integralOrder.setImage(unifiedMerchandise.getImage());
        integralOrder.setName(unifiedMerchandise.getName());
        integralOrder.setSpecifications(unifiedMerchandise.getSpecifications());
        integralOrder.setCash(cash);
        integralOrder.setIntegral(Long.valueOf(integral).intValue());
        integralOrder.setOrderNumber(uniqueCodeGenerator.generate(INTEGRAL_ORDER_CODE, new HashMap<String, String>()));
        integralOrder.setPaymentMode(PaymentModeType.WEIXIN_PAY.getKey());
        integralOrder.setRemind(0);
        integralOrder.setSum(0);
        integralOrder.setTime(new Date());
        integralOrder.setUnifiedMerchandiseId(unifiedMerchandiseId);
        integralOrder.setUserId(userId);
        if (cash > 0) {// 需要给现金
            integralOrder.setState(IntegralOrderStateType.TO_PAY.getKey());
            add(integralOrder);
        } else {// 只需要给积分
            integralOrder.setState(IntegralOrderStateType.TO_SHIP.getKey());
            personalcenterClient.calculateMyWealth(userId, WealthType.INTEGRAL, -integral, false, "购买商品：" + unifiedMerchandise.getName() + "使用积分:" + integral);
            add(integralOrder);
        }
        return integralOrder;
    }

    @Override
    public List<IntegralOrder> listByUser(long userId, int state, int start, int size) {

        return integralOrderDao.listByUser(userId, state, 1, start, size);
    }

    @Override
    public List<IntegralOrder> listByUserAndFront(long userId, int state, int start, int size) {

        return integralOrderDao.listByUser(userId, state, 0, start, size);
    }

    @Override
    public int countByUser(long userId, int state) {

        return integralOrderDao.countByUser(userId, state, 1);
    }

    @Override
    public int countByUserAndFront(long userId, int state) {

        return integralOrderDao.countByUser(userId, state, 0);
    }

    @Override
    public IntegralOrder getByOrder(long orderId) {

        return integralOrderDao.getByOrder(orderId);
    }
}
