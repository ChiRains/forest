package com.qcloud.component.personalcenter.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.WealthType;
import com.qcloud.component.personalcenter.dao.MySignInDayDao;
import com.qcloud.component.personalcenter.model.MySignInDay;
import com.qcloud.component.personalcenter.model.MySignInRecord;
import com.qcloud.component.personalcenter.model.MySignInRuleConfig;
import com.qcloud.component.personalcenter.model.MySignInStatistics;
import com.qcloud.component.personalcenter.model.SignIntegral;
import com.qcloud.component.personalcenter.model.query.MySignInDayQuery;
import com.qcloud.component.personalcenter.service.MySignInDayService;
import com.qcloud.component.personalcenter.service.MySignInRecordService;
import com.qcloud.component.personalcenter.service.MySignInRuleConfigService;
import com.qcloud.component.personalcenter.service.MySignInStatisticsService;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.DateUtil;

@Service
public class MySignInDayServiceImpl implements MySignInDayService {

    @Autowired
    private MySignInDayDao            mySignInDayDao;

    @Autowired
    private MySignInStatisticsService mySignInStatisticsService;

    @Autowired
    private AutoIdGenerator           autoIdGenerator;

    private static final String       ID_KEY = "personalcenter_my_sign_in_day";

    @Autowired
    private PersonalcenterClient      personalcenterClient;

    @Autowired
    private MySignInRuleConfigService mySignInRuleConfigService;

    @Autowired
    private MySignInRecordService     mySignInRecordService;

    @Override
    public boolean add(MySignInDay mySignInDay) {

        long id = autoIdGenerator.get(ID_KEY);
        mySignInDay.setId(id);
        return mySignInDayDao.add(mySignInDay);
    }

    @Override
    public MySignInDay get(Long id) {

        return mySignInDayDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return mySignInDayDao.delete(id);
    }

    @Override
    public boolean update(MySignInDay mySignInDay) {

        return mySignInDayDao.update(mySignInDay);
    }

    @Override
    public Page<MySignInDay> page(MySignInDayQuery query, int start, int count) {

        return mySignInDayDao.page(query, start, count);
    }

    public List<MySignInDay> listAll() {

        return mySignInDayDao.listAll();
    }

    @Transactional
    @Override
    public boolean signIn(long userId) {

        Date now = new Date();
        MySignInDay mySignInDay = mySignInDayDao.getByDate(userId, now);
        if (mySignInDay != null) {
            return false;
        }
        mySignInDay = new MySignInDay();
        Calendar c0 = Calendar.getInstance();
        c0.setTime(now);
        mySignInDay.setDay(c0.get(Calendar.DAY_OF_MONTH));
        mySignInDay.setMonth(c0.get(Calendar.MONTH) + 1);
        mySignInDay.setYear(c0.get(Calendar.YEAR));
        mySignInDay.setUserId(userId);
        MySignInStatistics mySignInStatistics = mySignInStatisticsService.getByUser(userId);
        if (mySignInStatistics == null) {
            mySignInStatistics = new MySignInStatistics();
            mySignInStatistics.setCurrentSignIn(1);
            mySignInStatistics.setLastSignInDay(now);
            mySignInStatistics.setMaxSignIn(1);
            mySignInStatistics.setTotal(1);
            mySignInStatistics.setUserId(userId);
            mySignInStatistics.setFirstSignInDay(now);
            MySignInRuleConfig mySignInRuleConfig = mySignInRuleConfigService.get();
            mySignInStatistics.setFirstIntegral(0);
            if (mySignInRuleConfig.getFirstIntegral() > 0) {
                mySignInStatistics.setFirstIntegral(mySignInRuleConfig.getFirstIntegral());
                personalcenterClient.calculateMyWealth(userId, WealthType.INTEGRAL, mySignInRuleConfig.getFirstIntegral(), false, "首次签到送" + mySignInRuleConfig.getFirstIntegral() + "积分");
            }
            mySignInStatisticsService.add(mySignInStatistics);
            giveIntegral(mySignInStatistics);
        } else {
            mySignInStatistics.setTotal(mySignInStatistics.getTotal() + 1);
            Date last = mySignInStatistics.getLastSignInDay();
            if (DateUtil.date2String(DateUtil.addDate(last, 1), DateUtil.DATE_FORMAT_STRING).equals(DateUtil.date2String(now, DateUtil.DATE_FORMAT_STRING))) {
                mySignInStatistics.setCurrentSignIn(mySignInStatistics.getCurrentSignIn() + 1);
            } else {
                mySignInStatistics.setCurrentSignIn(1);
            }
            mySignInStatistics.setLastSignInDay(now);
            if (mySignInStatistics.getCurrentSignIn() > mySignInStatistics.getMaxSignIn()) {
                mySignInStatistics.setMaxSignIn(mySignInStatistics.getCurrentSignIn());
            }
            mySignInStatisticsService.update(mySignInStatistics);
            giveIntegral(mySignInStatistics);
        }
        return add(mySignInDay);
    }

    private boolean giveIntegral(MySignInStatistics mySignInStatistics) {

        MySignInRuleConfig mySignInRuleConfig = mySignInRuleConfigService.get();
        int integral = calculateGiveIntegral(mySignInStatistics.getCurrentSignIn(), mySignInRuleConfig.getList());
        if (integral > 0) {
            int last = calculateIntegral(mySignInStatistics.getCurrentSignIn() - 1, mySignInRuleConfig);
            integral = integral - last;
            if (integral > 0) {
                personalcenterClient.calculateMyWealth(mySignInStatistics.getUserId(), WealthType.INTEGRAL, integral, false, "连续签到" + mySignInStatistics.getCurrentSignIn() + "天送" + integral + "积分");
            }
            resetCurrentSign(mySignInStatistics, mySignInRuleConfig.getList());
        }
        MySignInRecord mySignInRecord = new MySignInRecord();
        mySignInRecord.setUserId(mySignInStatistics.getUserId());
        if (mySignInStatistics.getTotal() == 1) {// 第一次签到
            mySignInRecord.setIntegral(mySignInRuleConfig.getFirstIntegral());
        } else {
            mySignInRecord.setIntegral(integral < 0 ? 0 : integral);
        }
        mySignInRecord.setSigntime(new Date());
        mySignInRecordService.add(mySignInRecord);
        return true;
    }

    private boolean resetCurrentSign(MySignInStatistics mySignInStatistics, List<SignIntegral> ruleList) {

        if (CollectionUtils.isEmpty(ruleList)) {
            return false;
        }
        SignIntegral si = ruleList.get(ruleList.size() - 1);
        if (si.getSign() == mySignInStatistics.getCurrentSignIn()) {
            mySignInStatistics.setCurrentSignIn(0);
            mySignInStatisticsService.update(mySignInStatistics);
        }
        return true;
    }

    private int calculateGiveIntegral(int currentSignIn, List<SignIntegral> ruleList) {

        for (SignIntegral signIntegral : ruleList) {
            if (signIntegral.getSign() == currentSignIn) {
                return signIntegral.getIntegral();
            }
        }
        return -1;
    }

    @Override
    public List<MySignInDay> listByYearAndMonth(long userId, int year, int month) {

        return mySignInDayDao.listByYearAndMonth(userId, year, month);
    }

    @Override
    public int calculateIntegral(int signNumber, MySignInRuleConfig mySignInRuleConfig) {

        List<SignIntegral> list = mySignInRuleConfig.getList();
        SignIntegral si = null;
        for (SignIntegral signIntegral : list) {
            if (signNumber >= signIntegral.getSign()) {
                if (si == null) {
                    si = signIntegral;
                } else if (si.getSign() < signIntegral.getSign()) {
                    si = signIntegral;
                }
            }
        }
        if (si == null) {
            return 0;
        } else {
            return si.getIntegral();
        }
    }

    @Override
    public int calculateNextDay(int signNumber, MySignInRuleConfig mySignInRuleConfig) {

        List<SignIntegral> list = mySignInRuleConfig.getList();
        SignIntegral si = null;
        for (SignIntegral signIntegral : list) {
            if (signNumber < signIntegral.getSign()) {
                if (si == null) {
                    si = signIntegral;
                } else if (si.getSign() > signIntegral.getSign()) {
                    si = signIntegral;
                }
            }
        }
        if (si == null) {
            return 0;
        } else {
            return si.getSign() - signNumber;
        }
    }

    @Override
    public int calculateNextIntegral(int signNumber, MySignInRuleConfig mySignInRuleConfig) {

        List<SignIntegral> list = mySignInRuleConfig.getList();
        SignIntegral si = null;
        for (SignIntegral signIntegral : list) {
            if (signNumber < signIntegral.getSign()) {
                if (si == null) {
                    si = signIntegral;
                } else if (si.getSign() > signIntegral.getSign()) {
                    si = signIntegral;
                }
            }
        }
        if (si == null) {
            return 0;
        } else {
            return si.getIntegral();
        }
    }
}
