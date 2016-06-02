package com.qcloud.component.personalcenter.web.handler;

import java.util.List;

import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.web.vo.UserVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminUserVO;

public interface UserHandler {

	List<UserVO> toVOList(List<User> list);

	UserVO toVO(User user);

	List<AdminUserVO> toVOList4Admin(List<User> list);

	AdminUserVO toVO4Admin(User user);
}
