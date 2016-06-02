package com.qcloud.component.brokerage.web.handler;

import java.util.List;
import com.qcloud.component.brokerage.model.UserRelationship;
import com.qcloud.component.brokerage.model.UserTeam;
import com.qcloud.component.brokerage.web.vo.AllocationUser;
import com.qcloud.component.personalcenter.QUser;

public interface AllocationUserHandler {

    List<AllocationUser> toVOList4Relationship(List<UserRelationship> list);

    AllocationUser toVO4UserRelationship(UserRelationship userRelationship);

    List<AllocationUser> toVOList4Team(List<UserTeam> list);

    AllocationUser toVO4Team(UserTeam userTeam);

    AllocationUser toVO(QUser user);
}
