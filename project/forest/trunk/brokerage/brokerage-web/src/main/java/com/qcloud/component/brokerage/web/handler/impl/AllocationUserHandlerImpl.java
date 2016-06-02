package com.qcloud.component.brokerage.web.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.brokerage.model.UserRelationship;
import com.qcloud.component.brokerage.model.UserShareToken;
import com.qcloud.component.brokerage.model.UserTeam;
import com.qcloud.component.brokerage.service.UserRelationshipService;
import com.qcloud.component.brokerage.service.UserShareTokenService;
import com.qcloud.component.brokerage.service.UserTeamService;
import com.qcloud.component.brokerage.web.handler.AllocationUserHandler;
import com.qcloud.component.brokerage.web.vo.AllocationUser;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QMyWealth;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.StringUtil;

@Component
public class AllocationUserHandlerImpl implements AllocationUserHandler {

    @Autowired
    UserShareTokenService   userShareTokenService;

    @Autowired
    PersonalcenterClient    personalcenterClient;

    @Autowired
    FileSDKClient           fileSDKClient;

    @Autowired
    UserRelationshipService userRelationshipService;

    @Autowired
    UserTeamService         userTeamService;

    @Override
    public List<AllocationUser> toVOList4Relationship(List<UserRelationship> list) {

        List<AllocationUser> allocationUserList = new ArrayList<AllocationUser>();
        for (UserRelationship userRelationship : list) {
            allocationUserList.add(toVO4UserRelationship(userRelationship));
        }
        return allocationUserList;
    }

    @Override
    public AllocationUser toVO4UserRelationship(UserRelationship userRelationship) {

        AllocationUser allocationUser = toVO(userRelationship.getRecommedId(), userRelationship.getUserId());
        int number = userRelationshipService.countChildren(userRelationship.getUserId(), null);
        allocationUser.setNumber(number);
        return allocationUser;
    }

    @Override
    public List<AllocationUser> toVOList4Team(List<UserTeam> list) {

        List<AllocationUser> allocationUserList = new ArrayList<AllocationUser>();
        for (UserTeam userTeam : list) {
            allocationUserList.add(toVO4Team(userTeam));
        }
        return allocationUserList;
    }

    @Override
    public AllocationUser toVO4Team(UserTeam userTeam) {

        AllocationUser allocationUser = toVO(userTeam.getLeader(), userTeam.getUserId());
        int number = userTeamService.countChildren(userTeam.getUserId());
        allocationUser.setNumber(number);
        return allocationUser;
    }

    @Override
    public AllocationUser toVO(QUser user) {

        AllocationUser allocationUser = toVO(user.getId());
        int number = userTeamService.countChildren(user.getId());
        allocationUser.setNumber(number);
        return allocationUser;
    }

    private AllocationUser toVO(Long recommedId, Long userId) {

        //
        UserShareToken recommedToken = userShareTokenService.getByUser(recommedId);
        AssertUtil.assertNotNull(recommedToken, "用户分佣分享token不存在." + recommedId);
        //
        AllocationUser allocationUser = toVO(userId);
        allocationUser.setRecommedToken(recommedToken.getToken());
        //
        return allocationUser;
    }

    private AllocationUser toVO(Long userId) {

        AllocationUser allocationUser = new AllocationUser();
        //
        UserShareToken userShareToken = userShareTokenService.getByUser(userId);
        AssertUtil.assertNotNull(userShareToken, "用户分佣分享token不存在." + userId);
        allocationUser.setUserShareToken(userShareToken.getToken());
        //
        QUser user = personalcenterClient.getUser(userId);
        QMyWealth myWealth = personalcenterClient.getMyWealth(userId);
        allocationUser.setCommission(myWealth.getCommission());
        if (StringUtils.isNotEmpty(user.getHeadImage())) {
            allocationUser.setImage(fileSDKClient.getFileServerUrl() + user.getHeadImage());
        } else {
            allocationUser.setImage(StringUtil.nullToEmpty(user.getHeadImage()));
        }
        allocationUser.setName(user.getName());
        allocationUser.setNickName(user.getNickname());
        allocationUser.setMobile(user.getMobile());
        //
        Date entryDate = user.getEntryDate();
        allocationUser.setEntryDateStr(DateUtil.date2String(entryDate, DateUtil.DATE_FORMAT_STRING));
        return allocationUser;
    }
}
