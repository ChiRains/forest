package com.qcloud.component.personalcenter.service.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.personalcenter.WealthType;
import com.qcloud.component.personalcenter.dao.MyWealthDao;
import com.qcloud.component.personalcenter.exception.PersonalCenterException;
import com.qcloud.component.personalcenter.model.MyBankCard;
import com.qcloud.component.personalcenter.model.MyCommissionWithdrawCash;
import com.qcloud.component.personalcenter.model.MyWealth;
import com.qcloud.component.personalcenter.model.MyWealthDetail;
import com.qcloud.component.personalcenter.model.key.TypeEnum.WithdrawCashStateType;
import com.qcloud.component.personalcenter.model.query.MyWealthQuery;
import com.qcloud.component.personalcenter.service.MyBankCardService;
import com.qcloud.component.personalcenter.service.MyCommissionWithdrawCashService;
import com.qcloud.component.personalcenter.service.MyWealthDetailService;
import com.qcloud.component.personalcenter.service.MyWealthService;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class MyWealthServiceImpl implements MyWealthService {

    @Autowired
    private MyWealthDao                     myWealthDao;

    @Autowired
    private AutoIdGenerator                 autoIdGenerator;

    @Autowired
    private MyWealthDetailService           myWealthDetailService;

    @Autowired
    private MyBankCardService               myBankCardService;

    @Autowired
    private MyCommissionWithdrawCashService myCommissionWithdrawCashService;

    private static final String             ID_KEY = "personalcenter_my_wealth";

    private Log                             logger = LogFactory.getLog(getClass());

    @Override
    public boolean add(MyWealth myWealth) {

        long id = autoIdGenerator.get(ID_KEY);
        myWealth.setId(id);
        return myWealthDao.add(myWealth);
    }

    @Override
    public MyWealth get(Long id) {

        return myWealthDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return myWealthDao.delete(id);
    }

    @Override
    public boolean update(MyWealth myWealth) {

        return myWealthDao.update(myWealth);
    }

    @Override
    public Page<MyWealth> page(MyWealthQuery query, int start, int count) {

        return myWealthDao.page(query, start, count);
    }

    public List<MyWealth> listAll() {

        return myWealthDao.listAll();
    }

    @Override
    public MyWealth getByUserId(long userId) {

        return myWealthDao.getByUserId(userId);
    }

    @Transactional
    @Override
    public boolean calculateWealth(long userId, double wealth, int type, String desc) {

        logger.info("开始计算用户财富:" + userId + " " + wealth + " " + type + " " + desc);
        MyWealth myWealth = getByUserId(userId);
        AssertUtil.assertNotNull(myWealth, "用户财富记录不存在");
        double remainder = 0.0;
        if (WealthType.INTEGRAL.getKey() == type) {
            myWealth.setIntegral(myWealth.getIntegral() + new Double(wealth).longValue());
            if (myWealth.getIntegral() < 0) {
                throw new PersonalCenterException("积分不能为." + myWealth.getIntegral());
            }
            remainder = myWealth.getIntegral();
            if (new Double(wealth * 100).longValue() > 0) {
                myWealth.setIntegralSummation(myWealth.getIntegralSummation() + new Double(wealth).longValue());
            }
        } else if (WealthType.COMMISSION.getKey() == type) {
            myWealth.setCommission(myWealth.getCommission() + wealth);
            if (myWealth.getCommission() < 0) {
                throw new PersonalCenterException("佣金不能为." + myWealth.getCommission());
            }
            remainder = myWealth.getCommission();
            if (new Double(wealth * 100).longValue() > 0) {
                myWealth.setCommissionSummation(myWealth.getCommissionSummation() + wealth);
            }
        } else if (WealthType.COMSUMPTION_CURRENCY.getKey() == type) {
            myWealth.setConsumptionCurrency(myWealth.getConsumptionCurrency() + wealth);
            if (myWealth.getConsumptionCurrency() < 0) {
                throw new PersonalCenterException("消费币不能为." + myWealth.getConsumptionCurrency());
            }
            remainder = myWealth.getConsumptionCurrency();
            if (new Double(wealth * 100).longValue() > 0) {
                myWealth.setConsumptionCurrencySummation(myWealth.getConsumptionCurrencySummation() + wealth);
            }
        } else {
            throw new PersonalCenterException("不支持的用户财富类型:" + type);
        }
        Date now = new Date();
        myWealth.setTime(now);
        boolean result = myWealthDao.updateLock(myWealth);
        if (result) {
            MyWealthDetail detail = new MyWealthDetail();
            detail.setWealthId(myWealth.getId());
            detail.setUserId(userId);
            detail.setType(type);
            detail.setTime(now);
            detail.setPoint(wealth);
            detail.setRemainder(remainder);
            detail.setDesc(desc);
            result = myWealthDetailService.add(detail);
        } else {
            throw new PersonalCenterException("计算用户财富出错,财富正在被计算:" + userId + " " + wealth + " " + type + " " + desc);
        }
        if (!result) {
            throw new PersonalCenterException("计算用户财富出错,添加明细失败:" + userId + " " + wealth + " " + type + " " + desc);
        } else {
            logger.info("完成计算用户财富:" + userId);
            MyWealth mw = getByUserId(userId);
            logger.info("EE用户财富:" + userId + " " + mw.getIntegral() + " " + mw.getIntegralSummation() + " " + mw.getCommission() + " " + mw.getCommissionSummation() + " " + mw.getConsumptionCurrency() + " " + mw.getConsumptionCurrencySummation());
        }
        return true;
    }

    @Transactional
    public boolean withdrawCommission(long userId, Long bankCardId, double cash) {

        MyWealth myWealth = getByUserId(userId);
        MyBankCard myBankCard = myBankCardService.get(bankCardId);
        AssertUtil.assertTrue(myWealth.getCommission() - cash >= 0, "提现不允许超过账户余额.");
        // 申请
        myWealth.setCommission(myWealth.getCommission() - cash);
        boolean result = myWealthDao.updateLock(myWealth);
        if (result) {
            MyWealthDetail detail = new MyWealthDetail();
            detail.setWealthId(myWealth.getId());
            detail.setUserId(userId);
            detail.setType(WealthType.COMMISSION.getKey());
            detail.setTime(new Date());
            detail.setPoint(0 - cash);
            detail.setRemainder(myWealth.getCommission());
            detail.setDesc("申请提现,等待平台客户处理.");
            result = myWealthDetailService.add(detail);
            MyCommissionWithdrawCash myCommissionWithdrawCash = new MyCommissionWithdrawCash();
            myCommissionWithdrawCash.setBank(myBankCard.getBank());
            myCommissionWithdrawCash.setCard(myBankCard.getCard());
            myCommissionWithdrawCash.setCommissionCash(cash);
            myCommissionWithdrawCash.setCompleteTime(null);
            myCommissionWithdrawCash.setState(WithdrawCashStateType.APPLY.getKey());
            myCommissionWithdrawCash.setTime(new Date());
            myCommissionWithdrawCash.setUserId(userId);
            myCommissionWithdrawCash.setWealthDetailId(detail.getId());
            myCommissionWithdrawCash.setWealthId(myWealth.getId());
            myCommissionWithdrawCash.setCardholder(myBankCard.getCardholder());
            result = myCommissionWithdrawCashService.add(myCommissionWithdrawCash);
        } else {
            throw new PersonalCenterException("提现申请失败,账户正在被计算:" + userId + " " + cash);
        }
        return result;
    }

    @Transactional
    public boolean integralToCurrency(long userId, int integral) {

        MyWealth myWealth = getByUserId(userId);
        AssertUtil.assertTrue(myWealth.getIntegral() >= integral, "转换积分不能超过账户已有积分." + myWealth.getIntegral() + " " + integral);
        myWealth.setIntegral(myWealth.getIntegral() - integral);
        // 积分兑换比率
        double currency = integral * 50 / 100;
        myWealth.setConsumptionCurrency(myWealth.getConsumptionCurrency() + currency);
        myWealth.setConsumptionCurrencySummation(myWealth.getConsumptionCurrencySummation() + currency);
        boolean result = myWealthDao.updateLock(myWealth);
        if (result) {
            MyWealthDetail integralDetail = new MyWealthDetail();
            integralDetail.setWealthId(myWealth.getId());
            integralDetail.setUserId(userId);
            integralDetail.setType(WealthType.INTEGRAL.getKey());
            integralDetail.setTime(new Date());
            integralDetail.setPoint(0 - integral);
            integralDetail.setRemainder(myWealth.getIntegral());
            integralDetail.setDesc("积分兑换:-" + integral);
            result = myWealthDetailService.add(integralDetail);
            MyWealthDetail currencyDetail = new MyWealthDetail();
            currencyDetail.setWealthId(myWealth.getId());
            currencyDetail.setUserId(userId);
            currencyDetail.setType(WealthType.COMSUMPTION_CURRENCY.getKey());
            currencyDetail.setTime(new Date());
            currencyDetail.setPoint(currency);
            currencyDetail.setRemainder(myWealth.getConsumptionCurrency());
            currencyDetail.setDesc("积分兑换:+" + currency);
            result = myWealthDetailService.add(currencyDetail);
        } else {
            throw new PersonalCenterException("积分兑换失败,账户正在被计算:" + userId + " " + integral);
        }
        return result;
    }

    @Override
    public List<MyWealth> listTop(int number, int type) {

        return myWealthDao.listTop(number, type);
    }
}
