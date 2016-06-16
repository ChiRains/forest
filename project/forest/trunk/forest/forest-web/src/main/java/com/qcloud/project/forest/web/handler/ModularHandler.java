package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.Modular;
import com.qcloud.project.forest.web.vo.ModularVO;
import com.qcloud.project.forest.web.vo.admin.AdminModularVO;

public interface ModularHandler {

	List<ModularVO> toVOList(List<Modular> list);

	ModularVO toVO(Modular modular);

	List<AdminModularVO> toVOList4Admin(List<Modular> list);

	AdminModularVO toVO4Admin(Modular modular);
}
