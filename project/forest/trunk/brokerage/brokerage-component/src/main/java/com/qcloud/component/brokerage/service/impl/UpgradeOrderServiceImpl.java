package com.qcloud.component.brokerage.service.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.brokerage.BrokerageClient;
import com.qcloud.component.brokerage.BrokerageDataSourceElement;
import com.qcloud.component.brokerage.dao.UpgradeOrderDao;
import com.qcloud.component.brokerage.entity.UpgradeGiftEntity;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.model.UpgradeOrder;
import com.qcloud.component.brokerage.model.query.UpgradeOrderQuery;
import com.qcloud.component.brokerage.service.DistributionGradeService;
import com.qcloud.component.brokerage.service.UpgradeGiftService;
import com.qcloud.component.brokerage.service.UpgradeOrderService;
import com.qcloud.component.brokerage.service.UserDistributionGradeService;
import com.qcloud.component.marketing.MarketingClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.pirates.data.Page;

@Service
public class UpgradeOrderServiceImpl implements UpgradeOrderService {

    @Autowired
    private UpgradeOrderDao              upgradeOrderDao;

    @Autowired
    private AutoIdGenerator              autoIdGenerator;

    private static final String          ID_KEY = "brokerage_upgrade_order";

    @Autowired
    private BrokerageClient              brokerageClient;

    @Autowired
    private UserDistributionGradeService userDistributionGradeService;

    @Autowired
    private DistributionGradeService     distributionGradeService;

    @Autowired
    private PersonalcenterClient         personalcenterClient;

    @Autowired
    private UpgradeGiftService           upgradeGiftService;

    @Autowired
    private MarketingClient              marketingClient;

    private Log                          logger = LogFactory.getLog(getClass());

    @Override
    public boolean add(UpgradeOrder upgradeOrder) {

        long id = autoIdGenerator.get(ID_KEY);
        upgradeOrder.setId(id);
        return upgradeOrderDao.add(upgradeOrder);
    }

    @Override
    public UpgradeOrder get(Long id) {

        return upgradeOrderDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return upgradeOrderDao.delete(id);
    }

    @Override
    public boolean update(UpgradeOrder upgradeOrder) {

        return upgradeOrderDao.update(upgradeOrder);
    }

    @Override
    public Page<UpgradeOrder> page(UpgradeOrderQuery query, int start, int count) {

        return upgradeOrderDao.page(query, start, count);
    }

    public List<UpgradeOrder> listAll() {

        return upgradeOrderDao.listAll();
    }

    @Transactional
    @Override
    public boolean pay(long orderId, int paymentMode) {

        // 修改状态
        UpgradeOrder upgradeOrder = get(orderId);
        upgradeOrder.setState(EnableType.ENABLE.getKey());
        upgradeOrder.setPaymentMode(paymentMode);
        update(upgradeOrder);
        // 修改用户级别
        userDistributionGradeService.changeUserGrade(upgradeOrder.getUserId(), upgradeOrder.getUpgradeGradeId());
        // 添加分佣
        QUser user = personalcenterClient.getUser(upgradeOrder.getUserId());
        Long userId = upgradeOrder.getUserId();
        synchronized (userId) {
            List<Long> list = distributionGradeService.gradeFormulaList();
            DistributionGrade originalGrade = distributionGradeService.get(upgradeOrder.getOriginalGradeId());
            DistributionGrade upgradeGrade = distributionGradeService.get(upgradeOrder.getUpgradeGradeId());
            if (CollectionUtils.isNotEmpty(list)) {
                for (Long formula : list) {
                    BrokerageDataSourceElement ds = new BrokerageDataSourceElement();
                    ds.setCash(upgradeOrder.getCash());
                    ds.setDateType(52);
                    ds.setDiscount(upgradeOrder.getCash());
                    ds.setPurchase(0);
                    ds.setFormulaId(formula);
                    ds.setImage("/file/get.do?uid=49237A13D7824F958587A7D6D376F517.png");
                    ds.setMerchantId(-1L);
                    ds.setOrderTime(new Date());
                    ds.setNumber(1);
                    ds.setOrderTime(new Date());
                    ds.setSourceDataId(upgradeOrder.getId());
                    ds.setTitle(user.getNickname() + " 会员升级:" + originalGrade.getName() + " 升到 " + upgradeGrade.getName());
                    ds.setUserId(upgradeOrder.getUserId());
                    brokerageClient.addSourceDataPool(ds);
                }
            }
            // 送优惠券
            List<UpgradeGiftEntity> giftList = upgradeGiftService.listCanGift(upgradeOrder.getUpgradeGradeId());
            if (CollectionUtils.isNotEmpty(list)) {
                for (UpgradeGiftEntity upgradeGiftEntity : giftList) {
                    long couponId = upgradeGiftEntity.getCouponId();
                    int number = upgradeGiftEntity.getNumber();
                    for (int index = 0; index < number; index++) {
                        try {
                            boolean sended = marketingClient.canExtract(userId, couponId);
                            if (sended) {
                                Long myCouponId = marketingClient.extractCoupon(userId, couponId);
                                if (myCouponId > 0) {
                                    logger.info("升级会员送优惠劵成功." + index + " " + userId);
                                } else {
                                    logger.info("升级会员送优惠劵失败." + index + " " + userId);
                                    break;
                                }
                            } else {
                                logger.info("升级会员送优惠劵失败." + index + " " + userId);
                                break;
                            }
                        } catch (Exception e) {
                            // 失败不用处理
                            logger.info("升级会员送优惠劵失败." + index + " " + userId);
                        }
                    }
                }
            }
            // 送优惠券
            return true;
        }
    }
}
