package com.qcloud.component.personalcenter.core;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QMyWealth;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.personalcenter.UserMessageType;
import com.qcloud.component.personalcenter.WealthType;
import com.qcloud.component.personalcenter.entity.MyWealthEntity;
import com.qcloud.component.personalcenter.entity.UserEntity;
import com.qcloud.component.personalcenter.model.Grade;
import com.qcloud.component.personalcenter.model.MembershipCardWarehouse;
import com.qcloud.component.personalcenter.model.MyWealth;
import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.model.UserThird;
import com.qcloud.component.personalcenter.model.WealthConfig;
import com.qcloud.component.personalcenter.model.key.TypeEnum;
import com.qcloud.component.personalcenter.model.key.TypeEnum.AccountType;
import com.qcloud.component.personalcenter.model.key.TypeEnum.MembershipCardWarehouseStateType;
import com.qcloud.component.personalcenter.model.key.TypeEnum.UserStateType;
import com.qcloud.component.personalcenter.model.query.UserQuery;
import com.qcloud.component.personalcenter.service.GradeService;
import com.qcloud.component.personalcenter.service.MembershipCardWarehouseService;
import com.qcloud.component.personalcenter.service.MyWealthService;
import com.qcloud.component.personalcenter.service.UserService;
import com.qcloud.component.personalcenter.service.UserThirdService;
import com.qcloud.component.personalcenter.service.WealthConfigService;
import com.qcloud.component.publicdata.SexType;
import com.qcloud.component.publicservice.MessageClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Component
public class PersonalcenterClientImpl implements PersonalcenterClient {

    @Autowired
    private GradeService                   gradeService;

    @Autowired
    private UserService                    userService;

    @Autowired
    private MessageClient                  messageClient;

    @Autowired
    private ParameterClient                parameterClient;

    @Autowired
    private UserThirdService               userThirdService;

    @Autowired
    private MembershipCardWarehouseService membershipCardWarehouseService;

    @Autowired
    private MyWealthService                myWealthService;

    @Autowired
    private WealthConfigService            wealthConfigService;

    private static final String            USER_REGIST_GRADE_KEY = "personalcenter-user-grade";

    @Override
    public boolean calculateMyWealth(long userId, WealthType type, double cash, boolean needProportion, String desc) {

        synchronized (new Long(userId)) {
            WealthConfig wealthConfig = wealthConfigService.get();
            double wealth = cash;
            // 获取转换比例
            if (needProportion && WealthType.INTEGRAL.equals(type)) {
                wealth = wealth * wealthConfig.getIntegral() / 100;
            } else if (needProportion && WealthType.COMSUMPTION_CURRENCY.equals(type)) {
                wealth = wealth * wealthConfig.getCurrency() / 100;
            }
            if (new Double(wealth * 100).longValue() == 0) {
                return false;
            }
            myWealthService.calculateWealth(userId, wealth, type.getKey(), desc);
            userService.calculateGrade(userId);
            return true;
        }
    }

    @Override
    public QMyWealth getMyWealth(long userId) {

        MyWealth myWealth = myWealthService.getByUserId(userId);
        if (myWealth == null) {
            return null;
        }
        MyWealthEntity myWealthEntity = new MyWealthEntity(myWealth);
        return myWealthEntity;
    }

    @Override
    public Long addUser(String mobile, String membershipCard, String name, String pwd) {

        User user = new User();
        user.setMobile(mobile);
        user.setMembershipCard(membershipCard);
        user.setState(UserStateType.ACTIVATE.getKey());
        user.setType(AccountType.REGISTER.getKey());
        user.setName(name);
        user.setNickname(name);
        Long gradeId = parameterClient.get(USER_REGIST_GRADE_KEY);
        user.setGradeId(gradeId);
        if (StringUtils.isNotEmpty(pwd)) {
            user.setState(UserStateType.ACTIVATE.getKey());
        }
        userService.add(user, pwd);
        return user.getId();
    }

    @Override
    public QUser getUser(long userId) {

        User user = userService.get(userId);
        if (user == null) {
            return null;
        }
        Grade grade = gradeService.get(user.getGradeId());
        UserEntity userEntity = new UserEntity(user, grade);
        return userEntity;
    }

    @Override
    public QUser getUser(String account) {

        User user = userService.getByAccount(account);
        if (user == null) {
            return null;
        }
        Grade grade = gradeService.get(user.getGradeId());
        UserEntity userEntity = new UserEntity(user, grade);
        return userEntity;
    }

    @Override
    public boolean sendMsg(long userId, UserMessageType type, String title, String content) {

        return messageClient.sendMsg(TypeEnum.USER_MESSAGE_CODE, type.getKey(), userId, title, content);
    }

    @Override
    public String getThirdId(Long userId) {

        UserThird userThird = userThirdService.getByUser(userId);
        if (userThird == null) {
            return "";
        }
        return userThird.getThirdId();
    }

    @Override
    public Page<QUser> userPage(String name, int start, int pAGE_SIZE) {

        UserQuery query = new UserQuery();
        query.setName(name);
        Page<User> userPage = userService.page(query, start, pAGE_SIZE);
        Page<QUser> qUserPage = new Page<QUser>();
        List<QUser> qUsers = new ArrayList<QUser>();
        for (User user : userPage.getData()) {
            Grade grade = gradeService.get(user.getGradeId());
            qUsers.add(new UserEntity(user, grade));
        }
        qUserPage.setData(qUsers);
        qUserPage.setCount(qUsers.size());
        return qUserPage;
    }

    @Override
    public boolean editSex(Long userId, int sex) {

        User user = userService.get(userId);
        SexType sexType = SexType.getSexType(sex);
        AssertUtil.assertNotNull(sexType, "性别类型数据不正确." + sex);
        user.setSex(sex);
        return userService.update(user);
    }

    @Override
    public boolean editHeadImage(Long userId, String headImage) {

        User user = userService.get(userId);
        user.setHeadImage(headImage);
        return userService.update(user);
    }

    @Override
    public boolean editUser(long userId, String mobile, String name) {

        User user = userService.get(userId);
        user.setName(name);
        user.setNickname(name);
        if (StringUtils.isNotEmpty(mobile) && !mobile.equals(user.getMobile())) {
            user.setMobile(mobile);
            userService.changeMobile(userId, mobile);
        }
        return userService.update(user);
    }

    @Override
    public List<QUser> userListAll() {

        List<User> userList = userService.listAll();
        List<QUser> qUsers = new ArrayList<QUser>();
        for (User user : userList) {
            Grade grade = gradeService.get(user.getGradeId());
            qUsers.add(new UserEntity(user, grade));
        }
        return qUsers;
    }

    @Override
    public boolean existAndUselessMembershipCard(String cardNumber) {

        MembershipCardWarehouse membershipCardWarehouse = membershipCardWarehouseService.getByCardNumber(cardNumber);
        if (membershipCardWarehouse == null) {
            return false;
        }
        if (membershipCardWarehouse.getState() == MembershipCardWarehouseStateType.ACTIVATE.getKey() || MembershipCardWarehouseStateType.LOSS.getKey() == membershipCardWarehouse.getState()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean activateUser(long userId) {

        User user = userService.get(userId);
        user.setState(UserStateType.ACTIVATE.getKey());
        return userService.update(user);
    }

	@Override
	public boolean editUserParam(long userId, String name, String nickName,String email, String headImage, int sex, 
									String mobile,String province, String city, String district,String address) {
		User user = userService.get(userId);
		user.setName(name);
		user.setNickname(nickName);
		user.setEmail(email);
		user.setHeadImage(headImage);
		user.setSex(sex);
		user.setMobile(mobile);
		user.setProvince(province);
		user.setCity(city);
		user.setDistrict(district);
		user.setAddress(address);
		return userService.update(user);
	}
}
