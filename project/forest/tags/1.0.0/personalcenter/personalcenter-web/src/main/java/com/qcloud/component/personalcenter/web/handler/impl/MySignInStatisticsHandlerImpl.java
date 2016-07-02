package com.qcloud.component.personalcenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.personalcenter.web.handler.MySignInStatisticsHandler;
import com.qcloud.component.personalcenter.model.MySignInStatistics;
import com.qcloud.component.personalcenter.web.vo.MySignInStatisticsVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMySignInStatisticsVO;

@Component
public class MySignInStatisticsHandlerImpl implements MySignInStatisticsHandler {

	@Override
	public List<MySignInStatisticsVO> toVOList(List<MySignInStatistics> list){
		List<MySignInStatisticsVO> voList = new ArrayList<MySignInStatisticsVO>();
		for (MySignInStatistics mySignInStatistics : list) {
			voList.add(toVO(mySignInStatistics));
		}
		return voList;
	}

	@Override
	public MySignInStatisticsVO toVO(MySignInStatistics mySignInStatistics){
		String json = Json.toJson(mySignInStatistics);
		return Json.toObject(json, MySignInStatisticsVO.class, true);

	}

	@Override
	public List<AdminMySignInStatisticsVO> toVOList4Admin(List<MySignInStatistics> list){
		List<AdminMySignInStatisticsVO> voList = new ArrayList<AdminMySignInStatisticsVO>();
		for (MySignInStatistics adminMySignInStatistics : list) {
			voList.add(toVO4Admin(adminMySignInStatistics));
		}
		return voList;
	}

	@Override
	public AdminMySignInStatisticsVO toVO4Admin(MySignInStatistics mySignInStatistics){
		String json = Json.toJson(mySignInStatistics);
		return Json.toObject(json, AdminMySignInStatisticsVO.class, true);
	}
}
