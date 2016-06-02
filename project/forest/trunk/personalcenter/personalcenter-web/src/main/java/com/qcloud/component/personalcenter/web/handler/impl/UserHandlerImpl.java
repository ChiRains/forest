package com.qcloud.component.personalcenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.personalcenter.model.Grade;
import com.qcloud.component.personalcenter.model.MyWealth;
import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.model.key.TypeEnum.AccountType;
import com.qcloud.component.personalcenter.model.key.TypeEnum.CollectionType;
import com.qcloud.component.personalcenter.model.key.TypeEnum.UserStateType;
import com.qcloud.component.personalcenter.service.GradeService;
import com.qcloud.component.personalcenter.service.MyCommissionWithdrawCashService;
import com.qcloud.component.personalcenter.service.MyWealthService;
import com.qcloud.component.personalcenter.web.handler.UserHandler;
import com.qcloud.component.personalcenter.web.vo.UserVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminUserVO;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.component.publicdata.SexType;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.StringUtil;

@Component
public class UserHandlerImpl implements UserHandler {

    @Autowired
    private GradeService                    gradeService;

    @Autowired
    private FileSDKClient                   fileSDKClient;

    @Autowired
    private MyWealthService                 myWealthService;

    @Autowired
    private MyCommissionWithdrawCashService myCommissionWithdrawCashService;

    @Autowired(required = false)
    private MyClient                        myClient;

    @Override
    public List<UserVO> toVOList(List<User> list) {

        List<UserVO> voList = new ArrayList<UserVO>();
        for (User user : list) {
            voList.add(toVO(user));
        }
        return voList;
    }

    @Override
    public UserVO toVO(User user) {

        String json = Json.toJson(user);
        UserVO vo = Json.toObject(json, UserVO.class, true);
        Grade grade = gradeService.get(user.getGradeId());
        vo.setGrade(grade == null ? "" : grade.getName());
        MyWealth myWealth = myWealthService.getByUserId(user.getId());
        vo.setCommission(myWealth.getCommission());
        vo.setConsumptionCurrency(myWealth.getConsumptionCurrency());
        vo.setIntegral(myWealth.getIntegral());
        String sexStr = SexType.UNKNOW.getValue();
        if (SexType.MALE.getKey() == user.getSex()) {
            sexStr = SexType.MALE.getValue();
        } else if (SexType.FEMALE.getKey() == user.getSex()) {
            sexStr = SexType.FEMALE.getValue();
        }
        vo.setSexStr(sexStr);
        vo.setEntryTimeStr(DateUtil.date2String(user.getRegistTime()));
        //
        double withdrawingCommission = myCommissionWithdrawCashService.statWithdrawingCommission(user.getId());
        double withdrawedCommission = myCommissionWithdrawCashService.statWithdrawedCommission(user.getId());
        vo.setWithdrawingCommission(withdrawingCommission);
        vo.setWithdrawedCommission(withdrawedCommission);
        int merchandiseCollectNumber = 0;
        int merchantCollectNumber = 0;
        if (myClient != null) {
            merchandiseCollectNumber = myClient.statCollection(user.getId(), CollectionType.MERCHANDISE.getKey());
            merchantCollectNumber = myClient.statCollection(user.getId(), CollectionType.MERCHANT.getKey());
        }
        vo.setMerchantCollectNumber(merchantCollectNumber);
        vo.setMerchandiseCollectNumber(merchandiseCollectNumber);
        if (StringUtils.isEmpty(user.getHeadImage())) {
            vo.setHeadImage(StringUtil.nullToEmpty(user.getHeadImage()));
        } else {
            vo.setHeadImage(fileSDKClient.getFileServerUrl() + user.getHeadImage());
        }
        //
        return vo;
    }

    @Override
    public List<AdminUserVO> toVOList4Admin(List<User> list) {

        List<AdminUserVO> voList = new ArrayList<AdminUserVO>();
        for (User adminUser : list) {
            voList.add(toVO4Admin(adminUser));
        }
        return voList;
    }

    @Override
    public AdminUserVO toVO4Admin(User user) {

        String json = Json.toJson(user);
        AdminUserVO vo = Json.toObject(json, AdminUserVO.class, true);
        Grade grade = gradeService.get(user.getGradeId());
        vo.setStateStr(UserStateType.getDesc(user.getState()));
        String sexStr = SexType.UNKNOW.getValue();
        if (SexType.MALE.getKey() == user.getSex()) {
            sexStr = SexType.MALE.getValue();
        } else if (SexType.FEMALE.getKey() == user.getSex()) {
            sexStr = SexType.FEMALE.getValue();
        }
        vo.setSexStr(sexStr);
        vo.setEnable(UserStateType.FORBIDDEN.getKey() == user.getState() ? EnableType.DISABLE.getKey() : EnableType.ENABLE.getKey());
        vo.setEnableSelected(vo.getEnable() == EnableType.ENABLE.getKey() ? "checked" : "");
        String typeStr = "";
        switch (user.getType()) {
        case 1:
            typeStr = AccountType.REGISTER.getName();
            break;
        case 2:
            typeStr = AccountType.QQ.getName();
            break;
        case 3:
            typeStr = AccountType.WEIXIN.getName();
            break;
        case 4:
            typeStr = AccountType.WEIBO.getName();
            break;
        }
        vo.setTypeStr(typeStr);
        vo.setHeadImageUid(fileSDKClient.urlToUid(vo.getHeadImage()));
        vo.setHeadImage(fileSDKClient.getFileServerUrl() + vo.getHeadImage());
        vo.setGrade(grade == null ? "" : grade.getName());
        return vo;
    }
}
