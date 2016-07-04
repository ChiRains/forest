//package com.qcloud.component.sellercenter.service.impl;
//
//import java.util.Date;
//import java.util.List;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import com.qcloud.component.autoid.AutoIdGenerator;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.util.AssertUtil;
//import com.qcloud.component.personalcenter.WealthType;
//import com.qcloud.component.sellercenter.dao.MerchantWealthDao;
//import com.qcloud.component.sellercenter.exception.SellerCenterException;
//import com.qcloud.component.sellercenter.model.MerchantWealth;
//import com.qcloud.component.sellercenter.model.MerchantWealthDetail;
//import com.qcloud.component.sellercenter.service.MerchantWealthDetailService;
//import com.qcloud.component.sellercenter.service.MerchantWealthService;
//import com.qcloud.component.sellercenter.model.query.MerchantWealthQuery;
//
//@Service
//public class MerchantWealthServiceImpl implements MerchantWealthService {
//
//    @Autowired
//    private MerchantWealthDao           merchantWealthDao;
//
//    @Autowired
//    private AutoIdGenerator             autoIdGenerator;
//
//    private static final String         ID_KEY = "sellercenter_merchant_wealth";
//
//    @Autowired
//    private MerchantWealthDetailService merchantWealthDetailService;
//
//    private Log                         logger = LogFactory.getLog(getClass());
//
//    @Override
//    public boolean add(MerchantWealth merchantWealth) {
//
//        long id = autoIdGenerator.get(ID_KEY);
//        merchantWealth.setId(id);
//        return merchantWealthDao.add(merchantWealth);
//    }
//
//    @Override
//    public MerchantWealth get(Long id) {
//
//        return merchantWealthDao.get(id);
//    }
//
//    @Override
//    public boolean delete(Long id) {
//
//        return merchantWealthDao.delete(id);
//    }
//
//    @Override
//    public boolean update(MerchantWealth merchantWealth) {
//
//        return merchantWealthDao.update(merchantWealth);
//    }
//
//    @Override
//    public Page<MerchantWealth> page(MerchantWealthQuery query, int start, int count) {
//
//        return merchantWealthDao.page(query, start, count);
//    }
//
//    public List<MerchantWealth> listAll() {
//
//        return merchantWealthDao.listAll();
//    }
//
//    @Transactional
//    @Override
//    public boolean calculateWealth(long merchantId, double wealth, int type, String desc) {
//
//        logger.info("开始计算用户财富:" + merchantId + " " + wealth + " " + type + " " + desc);
//        MerchantWealth myWealth = getByMerchant(merchantId);
//        AssertUtil.assertNotNull(myWealth, "商家财富记录不存在");
//        double remainder = 0.0;
//        if (WealthType.INTEGRAL.getKey() == type) {
//            myWealth.setIntegral(myWealth.getIntegral() + new Double(wealth).longValue());
//            if (myWealth.getIntegral() < 0) {
//                throw new SellerCenterException("积分不能为." + myWealth.getIntegral());
//            }
//            remainder = myWealth.getIntegral();
//            if (new Double(wealth * 100).longValue() > 0) {
//                myWealth.setIntegralSummation(myWealth.getIntegralSummation() + new Double(wealth).longValue());
//            }
//        } else if (WealthType.COMMISSION.getKey() == type) {
//            myWealth.setCommission(myWealth.getCommission() + wealth);
//            if (myWealth.getCommission() < 0) {
//                throw new SellerCenterException("佣金不能为." + myWealth.getCommission());
//            }
//            remainder = myWealth.getCommission();
//            if (new Double(wealth * 100).longValue() > 0) {
//                myWealth.setCommissionSummation(myWealth.getCommissionSummation() + wealth);
//            }
//        } else if (WealthType.COMSUMPTION_CURRENCY.getKey() == type) {
//            myWealth.setConsumptionCurrency(myWealth.getConsumptionCurrency() + wealth);
//            if (myWealth.getConsumptionCurrency() < 0) {
//                throw new SellerCenterException("消费币不能为." + myWealth.getConsumptionCurrency());
//            }
//            remainder = myWealth.getConsumptionCurrency();
//            if (new Double(wealth * 100).longValue() > 0) {
//                myWealth.setConsumptionCurrencySummation(myWealth.getConsumptionCurrencySummation() + wealth);
//            }
//        } else {
//            throw new SellerCenterException("不支持的用户财富类型:" + type);
//        }
//        Date now = new Date();
//        myWealth.setTime(now);
//        boolean result = merchantWealthDao.updateLock(myWealth);
//        if (result) {
//            MerchantWealthDetail detail = new MerchantWealthDetail();
//            detail.setWealthId(myWealth.getId());
//            detail.setMerchantId(merchantId);
//            detail.setType(type);
//            detail.setTime(now);
//            detail.setPoint(wealth);
//            detail.setRemainder(remainder);
//            detail.setDesc(desc);
//            result = merchantWealthDetailService.add(detail);
//        } else {
//            throw new SellerCenterException("计算用户财富出错,财富正在被计算:" + merchantId + " " + wealth + " " + type + " " + desc);
//        }
//        if (!result) {
//            throw new SellerCenterException("计算用户财富出错,添加明细失败:" + merchantId + " " + wealth + " " + type + " " + desc);
//        } else {
//            logger.info("完成计算用户财富:" + merchantId);
//            MerchantWealth mw = getByMerchant(merchantId);
//            logger.info("EE用户财富:" + merchantId + " " + mw.getIntegral() + " " + mw.getIntegralSummation() + " " + mw.getCommission() + " " + mw.getCommissionSummation() + " " + mw.getConsumptionCurrency() + " " + mw.getConsumptionCurrencySummation());
//        }
//        return true;
//    }
//
//    @Override
//    public MerchantWealth getByMerchant(long merchantId) {
//
//        return merchantWealthDao.getByMerchant(merchantId);
//    }
//}
