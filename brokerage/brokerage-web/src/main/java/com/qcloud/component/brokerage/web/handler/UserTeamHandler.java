package com.qcloud.component.brokerage.web.handler;

import java.util.List;
import com.qcloud.component.brokerage.model.UserTeam;
import com.qcloud.component.brokerage.web.vo.UserTeamVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminUserTeamVO;

public interface UserTeamHandler {

	List<UserTeamVO> toVOList(List<UserTeam> list);

	UserTeamVO toVO(UserTeam userTeam);

	List<AdminUserTeamVO> toVOList4Admin(List<UserTeam> list);
	
    List<AdminUserTeamVO> toVOList4Leader(List<UserTeam> list);

	AdminUserTeamVO toVO4Admin(UserTeam userTeam);
}
