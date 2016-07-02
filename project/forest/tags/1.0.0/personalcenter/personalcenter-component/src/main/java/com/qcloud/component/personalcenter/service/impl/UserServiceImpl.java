package com.qcloud.component.personalcenter.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.account.QAccount;
import com.qcloud.component.account.UnifiedAccountClient;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.personalcenter.dao.UserDao;
import com.qcloud.component.personalcenter.exception.PersonalCenterException;
import com.qcloud.component.personalcenter.model.Grade;
import com.qcloud.component.personalcenter.model.MembershipCardWarehouse;
import com.qcloud.component.personalcenter.model.MyWealth;
import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.model.key.TypeEnum;
import com.qcloud.component.personalcenter.model.key.TypeEnum.MembershipCardWarehouseStateType;
import com.qcloud.component.personalcenter.model.key.TypeEnum.UserStateType;
import com.qcloud.component.personalcenter.model.query.UserQuery;
import com.qcloud.component.personalcenter.service.GradeService;
import com.qcloud.component.personalcenter.service.MembershipCardWarehouseService;
import com.qcloud.component.personalcenter.service.MyWealthService;
import com.qcloud.component.personalcenter.service.UserService;
import com.qcloud.component.publicdata.SexType;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao                        userDao;

    @Autowired
    private AutoIdGenerator                autoIdGenerator;

    private static final String            ID_KEY                   = "personalcenter_user";

    @Autowired
    private ParameterClient                parameterClient;

    private static final String            USER_PASSWORD_KEY        = "personalcenter-user-default-password";

    @Autowired
    private FileSDKClient                  fileSDKClient;

    @Autowired
    private GradeService                   gradeService;

    @Autowired
    private MyWealthService                myWealthService;

    @Autowired
    private UnifiedAccountClient           unifiedAccountClient;

    @Autowired
    private MembershipCardWarehouseService membershipCardWarehouseService;

    private static final String            ACCOUNT_TYPE_CODE        = "personalcenter_user_account_type";

    private static final String            MOBILE_TYPE_CODE         = "mobile";

    private static final String            EMAIL_TYPE_CODE          = "email";

    private static final String            MEMBERSHIPCARD_TYPE_CODE = "membershipCard";

    @PostConstruct
    public void init() {

        boolean mobile = enableAccountType(MOBILE_TYPE_CODE);
        boolean email = enableAccountType(EMAIL_TYPE_CODE);
        boolean membershipCard = enableAccountType(MEMBERSHIPCARD_TYPE_CODE);
        AssertUtil.assertTrue(mobile || email || membershipCard, "请在app.xml文件配置系统用户账号支持类型:手机或者Email或者会员卡,支持组合");
        final String password = parameterClient.get(USER_PASSWORD_KEY);
        if (password == null) {
            throw new PersonalCenterException("请初始化参数：" + USER_PASSWORD_KEY);
        }
    }

    private boolean enableAccountType(String accountName) {

        Xml xml = XmlFactory.get(ACCOUNT_TYPE_CODE);
        List<XmlItem> list = xml.getItemList();
        for (XmlItem xmlItem : list) {
            if (accountName.equals(xmlItem.getAttrMap().get("key")) && Boolean.valueOf(xmlItem.getAttrMap().get("enable"))) {
                return true;
            }
        }
        return false;
    }

    @Transactional
    @Override
    public boolean add(User user, String pwd) {

        Grade grade = gradeService.get(user.getGradeId());
        AssertUtil.assertNotNull(grade, "等级不存在");
        if (StringUtils.isNotEmpty(user.getMobile())) {
            User u = getByMobile(user.getMobile());
            AssertUtil.assertTrue(u == null, "手机号已经使用." + user.getMobile());
            if (enableAccountType(MOBILE_TYPE_CODE)) {
                AssertUtil.assertTrue(!unifiedAccountClient.exist(user.getMobile()), "账号已经使用." + user.getMobile());
            }
        }
        if (StringUtils.isNotEmpty(user.getEmail())) {
            User u = getByEmail(user.getEmail());
            AssertUtil.assertTrue(u == null, "Email已经使用." + user.getEmail());
            if (enableAccountType(EMAIL_TYPE_CODE)) {
                AssertUtil.assertTrue(!unifiedAccountClient.exist(user.getEmail()), "账号已经使用." + user.getEmail());
            }
        }
        if (StringUtils.isNotEmpty(user.getMembershipCard())) {
            User u = getByMembershipCard(user.getMembershipCard());
            AssertUtil.assertTrue(u == null, "会员卡已经使用." + user.getMembershipCard());
            if (enableAccountType(MEMBERSHIPCARD_TYPE_CODE)) {
                AssertUtil.assertTrue(!unifiedAccountClient.exist(user.getMembershipCard()), "账号已经使用." + user.getMembershipCard());
            }
            MembershipCardWarehouse membershipCardWarehouse = membershipCardWarehouseService.getByCardNumber(user.getMembershipCard());
            AssertUtil.assertNotNull(membershipCardWarehouse, "会员卡信息尚未登记." + user.getMembershipCard());
            AssertUtil.assertTrue(membershipCardWarehouse.getState() == MembershipCardWarehouseStateType.NEW.getKey(), "会员卡已经激活,不能重复使用.");
            membershipCardWarehouse.setState(MembershipCardWarehouseStateType.ACTIVATE.getKey());
            membershipCardWarehouseService.update(membershipCardWarehouse);
        }
        long id = autoIdGenerator.get(ID_KEY);
        user.setId(id);
        if (StringUtils.isEmpty(pwd)) {
            final String defaultPassword = parameterClient.get(USER_PASSWORD_KEY);
            pwd = defaultPassword;
            user.setState(UserStateType.NEW.getKey());
        } else {
            user.setState(UserStateType.ACTIVATE.getKey());
        }
        user.setRegistTime(new Date());
        if (StringUtils.isNotEmpty(user.getHeadImage())) {
            user.setHeadImage(fileSDKClient.uidToUrl(user.getHeadImage()));
        }
        SexType sexType = SexType.getSexType(user.getSex());
        if (sexType == null) {
            user.setSex(new Long(SexType.UNKNOW.getKey()).intValue());
        }
        if (StringUtils.isEmpty(user.getNickname())) {
            int number = new Random().nextInt(1000000);
            String nickname = "会员" + StringUtils.leftPad(String.valueOf(number), 6, "0");
            user.setNickname(nickname);
        }
        String group = null;
        String account = null;
        if (StringUtils.isNotEmpty(user.getMobile()) && enableAccountType(MOBILE_TYPE_CODE)) {
            group = regAccount(user.getMobile(), user.getNickname(), pwd, account, group);
            account = user.getMobile();
        }
        if (StringUtils.isNotEmpty(user.getEmail()) && enableAccountType(EMAIL_TYPE_CODE)) {
            group = regAccount(user.getEmail(), user.getNickname(), pwd, account, group);
            account = user.getEmail();
        }
        if (StringUtils.isNotEmpty(user.getMembershipCard()) && enableAccountType(MEMBERSHIPCARD_TYPE_CODE)) {
            group = regAccount(user.getMembershipCard(), user.getNickname(), pwd, account, group);
            account = user.getMembershipCard();
        }
        user.setAccountGroup(group);
        boolean result = userDao.add(user);
        if (result) {
            MyWealth myWealth = new MyWealth();
            myWealth.setTime(new Date());
            myWealth.setCommission(0.00);
            myWealth.setConsumptionCurrency(0.0);
            myWealth.setIntegral(0);
            myWealth.setCommissionSummation(0.00);
            myWealth.setConsumptionCurrencySummation(0.0);
            myWealth.setIntegralSummation(0);
            myWealth.setUserId(user.getId());
            myWealth.setVersion(1L);
            myWealthService.add(myWealth);
        }
        calculateGrade(id);
        return result;
    }

    private String regAccount(String account, String nickname, String pwd, String otherAccount, String group) {

        String resultGroup = group;
        if (StringUtils.isEmpty(otherAccount)) {
            resultGroup = unifiedAccountClient.reg(TypeEnum.USER_ACCOUNT_CODE, account, nickname, pwd);
        } else {
            unifiedAccountClient.regByOtherAccount(TypeEnum.USER_ACCOUNT_CODE, account, otherAccount, group);
        }
        return resultGroup;
    }

    // public String getEncodePsw(String psw) {
    //
    // return unifiedAccountClient.encodePwd(psw);
    // }
    @Override
    public User get(Long id) {

        return userDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return userDao.delete(id);
    }

    @Transactional
    @Override
    public boolean update(User user) {

        Grade grade = gradeService.get(user.getGradeId());
        AssertUtil.assertNotNull(grade, "等级不存在");
        User u = get(user.getId());
        AssertUtil.assertNotNull(u, "用户记录不存在");
        u.setGradeId(user.getGradeId());
        u.setHeadImage(user.getHeadImage());
        u.setName(user.getName());
        u.setState(user.getState());
        u.setNickname(user.getNickname());
        u.setSex(user.getSex());
        u.setBirthYear(user.getBirthYear());
        u.setBirthMonth(user.getBirthMonth());
        u.setBirthDay(user.getBirthDay());
        u.setProvince(user.getProvince());
        u.setCity(user.getCity());
        u.setDistrict(user.getDistrict());
        u.setAddress(user.getAddress());
        u.setHeadImage(fileSDKClient.uidToUrl(user.getHeadImage()));
        userDao.update(u);
        if (StringUtils.isNotEmpty(user.getEmail())) {
            if (!user.getEmail().equals(u.getEmail())) {
                changeEmail(u.getId(), user.getEmail());
            }
        }
        return true;
    }

    public boolean changePwd(Long id, String pwd) {

        AssertUtil.assertNotEmpty(pwd, "用户密码不能为空.");
        AssertUtil.assertNotNull(id, "用户ID不能为空.");
        User user = get(id);
        AssertUtil.assertNotNull(id, "用户不存在." + id);
        String account = null;
        QAccount qAccount = unifiedAccountClient.getByAccount(user.getMobile());
        account = user.getMobile();
        if (qAccount == null) {
            qAccount = unifiedAccountClient.getByAccount(user.getEmail());
            account = user.getEmail();
        }
        if (qAccount == null) {
            qAccount = unifiedAccountClient.getByAccount(user.getMembershipCard());
            account = user.getMembershipCard();
        }
        AssertUtil.assertNotNull(account, "找不到用户账号:" + user.getNickname());
        unifiedAccountClient.updatePwd(account, pwd);
        return true;
    }

    @Override
    public Page<User> page(UserQuery query, int start, int count) {

        return userDao.page(query, start, count);
    }

    public boolean calculateGrade(Long id) {

        User user = get(id);
        MyWealth myWealth = myWealthService.getByUserId(id);
        long less = Long.MAX_VALUE;
        List<Grade> list = gradeService.listAll();
        Grade nextGrade = null;
        for (Grade grade : list) {
            int point = grade.getPoint();
            if (myWealth.getIntegralSummation() - point >= 0 && myWealth.getIntegralSummation() - point < less) {
                nextGrade = grade;
            }
        }
        Grade grade = gradeService.get(user.getGradeId());
        if (nextGrade != null && ((nextGrade.getId() != user.getGradeId() && grade != null && grade.getPoint() < nextGrade.getPoint()) || grade == null)) {
            user.setGradeId(nextGrade.getId());
            user.setHeadImage(fileSDKClient.urlToUid(user.getHeadImage()));
            return update(user);
        }
        return false;
    }

    @Override
    public List<User> listAll() {

        return userDao.listAll();
    }

    private User getByMobile(String mobile) {

        return userDao.getByMobile(mobile);
    }

    private User getByEmail(String email) {

        return userDao.getByEmail(email);
    }

    private User getByMembershipCard(String membershipCard) {

        return userDao.getByMembershipCard(membershipCard);
    }

    public boolean isUser(String account, String password) {

        User user = getByAccount(account);
        AssertUtil.assertNotNull(user, "用户不存在" + account);
        return isEnableUser(user) && unifiedAccountClient.canEntrySystem(TypeEnum.USER_ACCOUNT_CODE, account, user.getAccountGroup(), password);
    }

    public User getByAccount(String account) {

        User user = getByMobile(account);
        if (user == null) {
            user = getByEmail(account);
        }
        if (user == null) {
            user = getByMembershipCard(account);
        }
        return user;
    }

    @Override
    public boolean isEnableUser(User user) {

        if (user != null && user.getState() == UserStateType.FROZEN.getKey()) {
            throw new PersonalCenterException("用户已经被冻结." + user.getMobile() + " " + user.getEmail() + " " + user.getMembershipCard());
        }
        if (user != null && user.getState() == UserStateType.FORBIDDEN.getKey()) {
            throw new PersonalCenterException("用户已经被禁用." + user.getMobile() + " " + user.getEmail() + " " + user.getMembershipCard());
        }
        return user != null;
    }

    @Transactional
    @Override
    public boolean changeMobile(Long id, String mobile) {

        AssertUtil.assertNotNull(id, "用户ID不能为空.");
        User user = get(id);
        AssertUtil.assertNotNull(id, "用户不存在." + id);
        User u = getByMobile(mobile);
        AssertUtil.assertTrue(u == null, "手机号已经使用." + mobile);
        if (enableAccountType(MOBILE_TYPE_CODE)) {
            AssertUtil.assertTrue(!unifiedAccountClient.exist(mobile), "手机号已使用." + mobile);
            unifiedAccountClient.updateAccount(user.getMobile(), mobile);
        }
        user.setMobile(mobile);
        return userDao.update(user);
    }

    @Transactional
    @Override
    public boolean changeEmail(Long id, String email) {

        AssertUtil.assertNotNull(id, "用户ID不能为空.");
        User user = get(id);
        AssertUtil.assertNotNull(id, "用户不存在." + id);
        // 2016-05-31 好太太-ryuma
        if (StringUtils.isNotEmpty(email)) {
            User u = getByEmail(email);
            AssertUtil.assertTrue(u == null || user.getId() == u.getId(), "电子邮箱已经使用." + email);
        }
        if (enableAccountType(EMAIL_TYPE_CODE)) {
            AssertUtil.assertNotEmpty(email, "电子邮箱不能为空.");
            AssertUtil.assertTrue(!unifiedAccountClient.exist(email), "电子邮箱已使用." + email);
            unifiedAccountClient.updateAccount(user.getEmail(), email);
        }
        user.setEmail(email);
        return userDao.update(user);
    }

    @Transactional
    @Override
    public boolean setMembershipCard(Long id, String membershipCard) {

        AssertUtil.assertNotNull(id, "用户ID不能为空.");
        User user = get(id);
        AssertUtil.assertNotNull(id, "用户不存在." + id);
        User u = getByMembershipCard(membershipCard);
        AssertUtil.assertTrue(u == null, "会员卡已经使用." + membershipCard);
        MembershipCardWarehouse membershipCardWarehouse = membershipCardWarehouseService.getByCardNumber(membershipCard);
        AssertUtil.assertNotNull(membershipCardWarehouse, "会员卡信息尚未登记." + user.getMembershipCard());
        AssertUtil.assertTrue(membershipCardWarehouse.getState() == MembershipCardWarehouseStateType.NEW.getKey(), "会员卡已经激活,不能重复使用.");
        membershipCardWarehouse.setState(MembershipCardWarehouseStateType.ACTIVATE.getKey());
        membershipCardWarehouseService.update(membershipCardWarehouse);
        if (enableAccountType(MEMBERSHIPCARD_TYPE_CODE)) {
            AssertUtil.assertTrue(!unifiedAccountClient.exist(membershipCard), "会员卡已使用." + membershipCard);
            unifiedAccountClient.regByOtherAccount(TypeEnum.USER_ACCOUNT_CODE, membershipCard, user.getMobile(), user.getAccountGroup());
        }
        user.setMembershipCard(membershipCard);
        return userDao.update(user);
    }

    @Override
    public String isUser(Long id, String password) {

        AssertUtil.assertNotNull(id, "用户ID不能为空.");
        User user = get(id);
        AssertUtil.assertNotNull(id, "用户不存在." + id);
        if (isUser(user.getMobile(), password)) {
            return user.getMobile();
        }
        if (isUser(user.getEmail(), password)) {
            return user.getEmail();
        }
        if (isUser(user.getMembershipCard(), password)) {
            return user.getMembershipCard();
        }
        return null;
    }

    @Override
    public boolean resetPwd(Long userId) {

        final String password = parameterClient.get(USER_PASSWORD_KEY);
        if (password == null) {
            throw new PersonalCenterException("请初始化参数：" + USER_PASSWORD_KEY);
        }
        return changePwd(userId, password);
    }
}
