package com.qcloud.component.personalcenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.personalcenter.web.handler.MySignInMonthHandler;
import com.qcloud.component.personalcenter.model.MySignInMonth;
import com.qcloud.component.personalcenter.web.vo.MySignInMonthVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMySignInMonthVO;

@Component
public class MySignInMonthHandlerImpl implements MySignInMonthHandler {

	@Override
	public List<MySignInMonthVO> toVOList(List<MySignInMonth> list){
		List<MySignInMonthVO> voList = new ArrayList<MySignInMonthVO>();
		for (MySignInMonth mySignInMonth : list) {
			voList.add(toVO(mySignInMonth));
		}
		return voList;
	}

	@Override
	public MySignInMonthVO toVO(MySignInMonth mySignInMonth){
		String json = Json.toJson(mySignInMonth);
		return Json.toObject(json, MySignInMonthVO.class, true);

	}

	@Override
	public List<AdminMySignInMonthVO> toVOList4Admin(List<MySignInMonth> list){
		List<AdminMySignInMonthVO> voList = new ArrayList<AdminMySignInMonthVO>();
		for (MySignInMonth adminMySignInMonth : list) {
			voList.add(toVO4Admin(adminMySignInMonth));
		}
		return voList;
	}

	@Override
	public AdminMySignInMonthVO toVO4Admin(MySignInMonth mySignInMonth){
		String json = Json.toJson(mySignInMonth);
		return Json.toObject(json, AdminMySignInMonthVO.class, true);
	}
}
