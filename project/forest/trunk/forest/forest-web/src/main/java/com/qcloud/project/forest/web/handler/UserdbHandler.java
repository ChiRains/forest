package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.Userdb;
import com.qcloud.project.forest.web.vo.UserdbVO;
import com.qcloud.project.forest.web.vo.admin.AdminUserdbVO;

public interface UserdbHandler {

	List<UserdbVO> toVOList(List<Userdb> list);

	UserdbVO toVO(Userdb userdb);

	List<AdminUserdbVO> toVOList4Admin(List<Userdb> list);

	AdminUserdbVO toVO4Admin(Userdb userdb);
}
