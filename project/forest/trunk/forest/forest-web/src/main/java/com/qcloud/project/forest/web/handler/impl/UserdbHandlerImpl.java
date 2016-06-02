package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.web.handler.UserdbHandler;
import com.qcloud.project.forest.model.Userdb;
import com.qcloud.project.forest.web.vo.UserdbVO;
import com.qcloud.project.forest.web.vo.admin.AdminUserdbVO;

@Component
public class UserdbHandlerImpl implements UserdbHandler {

	@Override
	public List<UserdbVO> toVOList(List<Userdb> list){
		List<UserdbVO> voList = new ArrayList<UserdbVO>();
		for (Userdb userdb : list) {
			voList.add(toVO(userdb));
		}
		return voList;
	}

	@Override
	public UserdbVO toVO(Userdb userdb){
		String json = Json.toJson(userdb);
		return Json.toObject(json, UserdbVO.class, true);

	}

	@Override
	public List<AdminUserdbVO> toVOList4Admin(List<Userdb> list){
		List<AdminUserdbVO> voList = new ArrayList<AdminUserdbVO>();
		for (Userdb adminUserdb : list) {
			voList.add(toVO4Admin(adminUserdb));
		}
		return voList;
	}

	@Override
	public AdminUserdbVO toVO4Admin(Userdb userdb){
		String json = Json.toJson(userdb);
		return Json.toObject(json, AdminUserdbVO.class, true);
	}
}
