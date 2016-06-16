package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.web.handler.ModularHandler;
import com.qcloud.project.forest.model.Modular;
import com.qcloud.project.forest.web.vo.ModularVO;
import com.qcloud.project.forest.web.vo.admin.AdminModularVO;

@Component
public class ModularHandlerImpl implements ModularHandler {

	@Override
	public List<ModularVO> toVOList(List<Modular> list){
		List<ModularVO> voList = new ArrayList<ModularVO>();
		for (Modular modular : list) {
			voList.add(toVO(modular));
		}
		return voList;
	}

	@Override
	public ModularVO toVO(Modular modular){
		String json = Json.toJson(modular);
		return Json.toObject(json, ModularVO.class, true);

	}

	@Override
	public List<AdminModularVO> toVOList4Admin(List<Modular> list){
		List<AdminModularVO> voList = new ArrayList<AdminModularVO>();
		for (Modular adminModular : list) {
			voList.add(toVO4Admin(adminModular));
		}
		return voList;
	}

	@Override
	public AdminModularVO toVO4Admin(Modular modular){
		String json = Json.toJson(modular);
		return Json.toObject(json, AdminModularVO.class, true);
	}
}
