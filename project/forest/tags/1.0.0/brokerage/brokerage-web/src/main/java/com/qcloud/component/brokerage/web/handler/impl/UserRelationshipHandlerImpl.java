package com.qcloud.component.brokerage.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.brokerage.model.UserRelationship;
import com.qcloud.component.brokerage.web.handler.UserRelationshipHandler;
import com.qcloud.component.brokerage.web.vo.UserRelationshipVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminUserRelationshipVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.core.json.Json;

@Component
public class UserRelationshipHandlerImpl implements UserRelationshipHandler {

    @Autowired
    private PersonalcenterClient personalcenterClient;

    @Override
    public List<UserRelationshipVO> toVOList(List<UserRelationship> list) {

        List<UserRelationshipVO> voList = new ArrayList<UserRelationshipVO>();
        for (UserRelationship userRelationship : list) {
            voList.add(toVO(userRelationship));
        }
        return voList;
    }

    @Override
    public UserRelationshipVO toVO(UserRelationship userRelationship) {

        String json = Json.toJson(userRelationship);
        return Json.toObject(json, UserRelationshipVO.class, true);
    }

    @Override
    public List<AdminUserRelationshipVO> toVOList4Admin(List<UserRelationship> list) {

        List<AdminUserRelationshipVO> voList = new ArrayList<AdminUserRelationshipVO>();
        for (UserRelationship adminUserRelationship : list) {
            voList.add(toVO4Admin(adminUserRelationship));
        }
        return voList;
    }

    @Override
    public AdminUserRelationshipVO toVO4Admin(UserRelationship userRelationship) {

        String json = Json.toJson(userRelationship);
        AdminUserRelationshipVO vo = Json.toObject(json, AdminUserRelationshipVO.class, true);
        if (userRelationship.getRecommedId() > 0) {
            QUser user = personalcenterClient.getUser(userRelationship.getRecommedId());
            String name = user == null ? "" : (user.getNickname() == null ? "" : user.getNickname()) + "(" + user.getMobile() + ")";
            vo.setRecommed(name);
        }
        if (userRelationship.getUserId() > 0) {
            QUser user = personalcenterClient.getUser(userRelationship.getUserId());
            String name = user == null ? "" : user.getNickname() == null ? "" : user.getNickname() + "(" + user.getMobile() + ")";
            vo.setUser(name);
        }
        return vo;
    }

    @Override
    public List<AdminUserRelationshipVO> toVOList4Recommed(List<UserRelationship> list) {

        List<AdminUserRelationshipVO> voList = new ArrayList<AdminUserRelationshipVO>();
        for (UserRelationship adminUserRelationship : list) {
            AdminUserRelationshipVO vo = toVO4Admin(adminUserRelationship);
            voList.add(vo);
        }
        for (AdminUserRelationshipVO adminUserRelationshipVO : voList) {
            setPath(adminUserRelationshipVO, voList);
        }
        return voList;
    }

    private void setPath(AdminUserRelationshipVO adminUserRelationshipVO, List<AdminUserRelationshipVO> voList) {

        List<AdminUserRelationshipVO> recommedList = new ArrayList<AdminUserRelationshipVO>();
        AdminUserRelationshipVO node = adminUserRelationshipVO;
        do {
            recommedList.add(node);
            node = getRecommed(node, voList);
        } while (node != null);
        StringBuffer sb = new StringBuffer();
        for (int index = recommedList.size() - 1; index >= 0; index--) {
            AdminUserRelationshipVO vo = recommedList.get(index);
            if (index == recommedList.size() - 1) {
                sb.append(vo.getRecommed());
                sb.append("-->");
            }
            sb.append(vo.getUser());
            if (index > 0) {
                sb.append("-->");
            }
        }
        adminUserRelationshipVO.setPath(sb.toString());
    }

    private AdminUserRelationshipVO getRecommed(AdminUserRelationshipVO adminUserRelationshipVO, List<AdminUserRelationshipVO> voList) {

        for (AdminUserRelationshipVO adminUserRelationshipVO2 : voList) {
            if (adminUserRelationshipVO2.getUserId() == adminUserRelationshipVO.getRecommedId()) {
                return adminUserRelationshipVO2;
            }
        }
        return null;
    }
}
