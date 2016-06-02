package com.qcloud.component.brokerage.web.handler;

import java.util.List;
import com.qcloud.component.brokerage.model.UserRelationship;
import com.qcloud.component.brokerage.web.vo.UserRelationshipVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminUserRelationshipVO;

public interface UserRelationshipHandler {

	List<UserRelationshipVO> toVOList(List<UserRelationship> list);

	UserRelationshipVO toVO(UserRelationship userRelationship);

	List<AdminUserRelationshipVO> toVOList4Admin(List<UserRelationship> list);
	
	List<AdminUserRelationshipVO> toVOList4Recommed(List<UserRelationship> list);

	AdminUserRelationshipVO toVO4Admin(UserRelationship userRelationship);
}
