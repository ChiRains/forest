package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.web.handler.ActivityHandler;
import com.qcloud.project.forest.model.Activity;
import com.qcloud.project.forest.web.vo.ActivityVO;
import com.qcloud.project.forest.web.vo.admin.AdminActivityVO;

@Component
public class ActivityHandlerImpl implements ActivityHandler {

	@Override
	public List<ActivityVO> toVOList(List<Activity> list){
		List<ActivityVO> voList = new ArrayList<ActivityVO>();
		for (Activity activity : list) {
			voList.add(toVO(activity));
		}
		return voList;
	}

	@Override
	public ActivityVO toVO(Activity activity){
		String json = Json.toJson(activity);
		return Json.toObject(json, ActivityVO.class, true);

	}

	@Override
	public List<AdminActivityVO> toVOList4Admin(List<Activity> list){
		List<AdminActivityVO> voList = new ArrayList<AdminActivityVO>();
		for (Activity adminActivity : list) {
			voList.add(toVO4Admin(adminActivity));
		}
		return voList;
	}

	@Override
	public AdminActivityVO toVO4Admin(Activity activity){
		String json = Json.toJson(activity);
		return Json.toObject(json, AdminActivityVO.class, true);
	}
}
