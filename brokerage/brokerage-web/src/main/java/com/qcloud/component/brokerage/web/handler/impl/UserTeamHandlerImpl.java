package com.qcloud.component.brokerage.web.handler.impl;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.brokerage.model.UserTeam;
import com.qcloud.component.brokerage.service.UserTeamService;
import com.qcloud.component.brokerage.web.handler.UserTeamHandler;
import com.qcloud.component.brokerage.web.vo.UserTeamVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminUserTeamVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.core.json.Json;
@Component
public class UserTeamHandlerImpl implements UserTeamHandler {
    @Autowired
    private PersonalcenterClient personalcenterClient;
    @Autowired
    private UserTeamService      userTeamService;

    @Override
    public List<UserTeamVO> toVOList(List<UserTeam> list) {
        List<UserTeamVO> voList = new ArrayList<UserTeamVO>();
        for (UserTeam userTeam : list) {
            voList.add(toVO(userTeam));
        }
        return voList;
    }

    @Override
    public UserTeamVO toVO(UserTeam userTeam) {
        String json = Json.toJson(userTeam);
        return Json.toObject(json, UserTeamVO.class, true);
    }

    @Override
    public List<AdminUserTeamVO> toVOList4Admin(List<UserTeam> list) {
        List<AdminUserTeamVO> voList = new ArrayList<AdminUserTeamVO>();
        for (UserTeam adminUserTeam : list) {
            AdminUserTeamVO vo = toVO4Admin(adminUserTeam);
            List<UserTeam> teamList = userTeamService.listChildren(adminUserTeam.getLeader());
            StringBuffer sbc = new StringBuffer();
            if (CollectionUtils.isNotEmpty(teamList)) {
                for (int index = 0; index < teamList.size() && index < 3; index++) {
                    UserTeam userTeam = teamList.get(index);
                    QUser user = personalcenterClient.getUser(userTeam.getUserId());
                    if (user != null) {
                        sbc.append(user.getName()).append("(").append(user.getMobile()).append(")");
                        if (index != teamList.size() - 1) {
                            sbc.append(";");
                        }
                    }
                }
                if (teamList.size() > 3) {
                    sbc.append("......");
                }
            }
            vo.setChildrenStr(sbc.toString());
            QUser father = getLeader(adminUserTeam.getLeader());
            QUser grandpa = null;
            QUser grandfather = null;
            if (father != null) {
                grandpa = getLeader(father.getId());
            }
            if (grandpa != null) {
                grandfather = getLeader(grandpa.getId());
            }
            StringBuffer sbp = new StringBuffer();
            if (grandfather != null) {
                sbp.append(grandfather.getName()).append("(").append(grandfather.getMobile()).append(")");
                sbp.append("<--");
            }
            if (grandpa != null) {
                sbp.append(grandpa.getName()).append("(").append(grandpa.getMobile()).append(")");
                sbp.append("<--");
            }
            if (father != null) {
                sbp.append(father.getName()).append("(").append(father.getMobile()).append(")");
            }
            vo.setParentStr(sbp.toString());
            voList.add(vo);
        }
        return voList;
    }

    private QUser getLeader(long userId) {
        UserTeam userTeam = userTeamService.getByUserId(userId);
        if (userTeam != null) {
            QUser leader = personalcenterClient.getUser(userTeam.getLeader());
            return leader;
        }
        return null;
    }

    @Override
    public AdminUserTeamVO toVO4Admin(UserTeam userTeam) {
        String json = Json.toJson(userTeam);
        AdminUserTeamVO vo = Json.toObject(json, AdminUserTeamVO.class, true);
        if (userTeam.getUserId() > 0) {
            QUser user = personalcenterClient.getUser(userTeam.getUserId());
            String name = user == null ? "" : user.getName() + "(" + user.getMobile() + ")";
            vo.setUserStr(name);
        }
        if (userTeam.getLeader() > 0) {
            QUser ld = personalcenterClient.getUser(userTeam.getLeader());
            String name = ld == null ? "" : ld.getName() + "(" + ld.getMobile() + ")";
            vo.setLeaderStr(name);
        }
        return vo;
    }

    @Override
    public List<AdminUserTeamVO> toVOList4Leader(List<UserTeam> list) {
        List<AdminUserTeamVO> voList = new ArrayList<AdminUserTeamVO>();
        for (UserTeam adminUserTeam : list) {
            AdminUserTeamVO vo = toVO4Admin(adminUserTeam);
            voList.add(vo);
        }
        return voList;
    }
}
