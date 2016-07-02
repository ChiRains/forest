package com.qcloud.component.brokerage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.autoid.UniqueCodeGenerator;
import com.qcloud.component.brokerage.dao.UserDistributionGradeDao;
import com.qcloud.component.brokerage.exception.BrokerageException;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.model.UpgradeOrder;
import com.qcloud.component.brokerage.model.UserDistributionGrade;
import com.qcloud.component.brokerage.model.query.UserDistributionGradeQuery;
import com.qcloud.component.brokerage.service.DistributionGradeService;
import com.qcloud.component.brokerage.service.UpgradeOrderService;
import com.qcloud.component.brokerage.service.UserDistributionGradeService;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.pirates.core.env.ProjectInfo;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.Base64;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.EncryptUtil;

@Service
public class UserDistributionGradeServiceImpl implements UserDistributionGradeService {

    @Autowired
    private UserDistributionGradeDao userDistributionGradeDao;

    @Autowired
    private AutoIdGenerator          autoIdGenerator;

    private static final String      ID_KEY     = "brokerage_user_distribution_grade";

    @Autowired
    private DistributionGradeService distributionGradeService;

    @Autowired
    private PersonalcenterClient     personalcenterClient;

    @Autowired
    private UpgradeOrderService      upgradeOrderService;

    @Autowired
    private UniqueCodeGenerator      uniqueCodeGenerator;

    @Autowired
    private ParameterClient          parameterClient;

    private final String             DOMAIN_KEY = "domain-key";

    @Value("${pirates.pay.key}")
    private String                   payKey     = "";

    private Log                      logger     = LogFactory.getLog(getClass());

    @PostConstruct
    public void init() {

        logger.info("payKey encode " + payKey);
        payKey = Base64.decode(payKey);
        logger.info("payKey decode " + payKey);
        AssertUtil.assertNotEmpty(payKey, "支付加密字符串不能为空.");
    }

    @Override
    public boolean add(UserDistributionGrade userDistributionGrade) {

        long id = autoIdGenerator.get(ID_KEY);
        userDistributionGrade.setId(id);
        return userDistributionGradeDao.add(userDistributionGrade);
    }

    @Override
    public UserDistributionGrade get(Long id) {

        return userDistributionGradeDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return userDistributionGradeDao.delete(id);
    }

    @Override
    public boolean update(UserDistributionGrade userDistributionGrade) {

        return userDistributionGradeDao.update(userDistributionGrade);
    }

    @Override
    public Page<UserDistributionGrade> page(UserDistributionGradeQuery query, int start, int count) {

        return userDistributionGradeDao.page(query, start, count);
    }

    public List<UserDistributionGrade> listAll() {

        return userDistributionGradeDao.listAll();
    }

    @Override
    public UserDistributionGrade getByUser(long userId) {

        UserDistributionGrade userDistributionGrade = userDistributionGradeDao.getByUser(userId);
        if (userDistributionGrade == null) {
            QUser user = personalcenterClient.getUser(userId);
            DistributionGrade distributionGrade = distributionGradeService.getDefault();
            userDistributionGrade = new UserDistributionGrade();
            userDistributionGrade.setCreateTime(user.getEntryDate());
            userDistributionGrade.setEffectiveTime(DateUtil.addDate(user.getEntryDate(), 36500));
            userDistributionGrade.setGradeId(distributionGrade.getId());
            userDistributionGrade.setId(-1L);
            userDistributionGrade.setUpgradeTime(user.getEntryDate());
            userDistributionGrade.setUserId(userId);
        }
        return userDistributionGrade;
    }

    @Override
    public Map<String, Object> requestAlipayPay(long userId, long gradeId) {

        UserDistributionGrade userDistributionGrade = userDistributionGradeDao.getByUser(userId);
        DistributionGrade currentDistributionGrade = null;
        if (userDistributionGrade == null) {
            currentDistributionGrade = distributionGradeService.getDefault();
        } else {
            currentDistributionGrade = distributionGradeService.get(userDistributionGrade.getGradeId());
        }
        DistributionGrade distributionGrade = distributionGradeService.get(gradeId);
        //
        double cash = distributionGrade.getCash() - currentDistributionGrade.getCash();
        if (new Double(cash * 100).intValue() <= 0) {
            throw new BrokerageException("级别配置有误.");
        }
        //
        Date date = new Date();
        Date deadlinePayTime = DateUtil.addTime(date, 60);
        int less = new Long((deadlinePayTime.getTime() - date.getTime()) / 1000).intValue();
        UpgradeOrder upgradeOrder = new UpgradeOrder();
        upgradeOrder.setCash(cash);
        upgradeOrder.setDeadlinePayTime(deadlinePayTime);
        upgradeOrder.setOrderNumber(uniqueCodeGenerator.generate("brokerage-upgrade-order-code", new HashMap<String, String>()));
        upgradeOrder.setOriginalGradeId(currentDistributionGrade.getId());
        upgradeOrder.setTime(date);
        upgradeOrder.setUpgradeGradeId(gradeId);
        upgradeOrder.setUserId(userId);
        upgradeOrder.setState(EnableType.DISABLE.getKey());
        upgradeOrderService.add(upgradeOrder);
        Map<String, Object> map = new HashMap<String, Object>();
        String orderNumber = upgradeOrder.getOrderNumber();
        String attach = String.valueOf(upgradeOrder.getId()) + "_" + (orderNumber.substring(0, 4) + orderNumber.substring(orderNumber.length() - 2, orderNumber.length()));
        map.put("out_trade_no", encodeAttach(attach));
        map.put("subject", "会员升级" + distributionGrade.getName());
        map.put("body", "支付订单" + upgradeOrder.getOrderNumber());
        if (ProjectInfo.isDev()) {
            map.put("total_fee", 1);
        } else {
            map.put("total_fee", new Double(cash * 100).intValue());
        }
        map.put("notify_url", getAlipayNotifyUrl(upgradeOrder));
        map.put("service", "mobile.securitypay.pay");
        map.put("payment_type", "1");
        map.put("_input_charset", "utf-8");
        map.put("it_b_pay", less + "m");
        return map;
    }

    @Override
    public boolean checkIsValidAttach(String attach) {

        if (attach == null) {
            return false;
        }
        String[] attachs = attach.split("_");
        if (attachs.length < 3) {
            return false;
        }
        String str = attachs[0] + "_" + attachs[1];
        return encodeAttach(str).equals(attach);
    }

    private String encodeAttach(String attach) {

        return attach + "_" + EncryptUtil.md5(payKey + "@" + attach);
    }

    private String getAlipayNotifyUrl(UpgradeOrder upgradeOrder) {

        return "http://" + parameterClient.get(DOMAIN_KEY) + "/userDistributionGrade/alipayPaied.do";
    }

    @Override
    public boolean changeUserGrade(long userId, long gradeId) {

        UserDistributionGrade userDistributionGrade = userDistributionGradeDao.getByUser(userId);
        DistributionGrade distributionGrade = distributionGradeService.get(gradeId);
        if (userDistributionGrade == null) {
            userDistributionGrade = new UserDistributionGrade();
            userDistributionGrade.setCreateTime(new Date());
            userDistributionGrade.setEffectiveTime(DateUtil.addDate(new Date(), distributionGrade.getMonthLimit()));
            userDistributionGrade.setGradeId(gradeId);
            userDistributionGrade.setUpgradeTime(new Date());
            userDistributionGrade.setUserId(userId);
            return add(userDistributionGrade);
        } else {
            userDistributionGrade.setEffectiveTime(DateUtil.addDate(new Date(), distributionGrade.getMonthLimit()));
            userDistributionGrade.setGradeId(gradeId);
            userDistributionGrade.setUpgradeTime(new Date());
            return update(userDistributionGrade);
        }
    }

    @Override
    public UserDistributionGrade getByUserForAdmin(long userId) {

        return userDistributionGradeDao.getByUser(userId);
    }
}
