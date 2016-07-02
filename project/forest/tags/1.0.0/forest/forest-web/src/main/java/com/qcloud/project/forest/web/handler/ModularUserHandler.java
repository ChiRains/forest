package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.ModularUser;
import com.qcloud.project.forest.web.vo.ModularUserVO;
import com.qcloud.project.forest.web.vo.admin.AdminModularUserVO;

public interface ModularUserHandler {

	List<ModularUserVO> toVOList(List<ModularUser> list);

	ModularUserVO toVO(ModularUser modularUser);

	List<AdminModularUserVO> toVOList4Admin(List<ModularUser> list);

	AdminModularUserVO toVO4Admin(ModularUser modularUser);
}
