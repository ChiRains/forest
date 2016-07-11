package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.web.handler.FeedbackHandler;
import com.qcloud.project.forest.model.Feedback;
import com.qcloud.project.forest.web.vo.FeedbackVO;
import com.qcloud.project.forest.web.vo.admin.AdminFeedbackVO;

@Component
public class FeedbackHandlerImpl implements FeedbackHandler {

	@Override
	public List<FeedbackVO> toVOList(List<Feedback> list){
		List<FeedbackVO> voList = new ArrayList<FeedbackVO>();
		for (Feedback feedback : list) {
			voList.add(toVO(feedback));
		}
		return voList;
	}

	@Override
	public FeedbackVO toVO(Feedback feedback){
		String json = Json.toJson(feedback);
		return Json.toObject(json, FeedbackVO.class, true);

	}

	@Override
	public List<AdminFeedbackVO> toVOList4Admin(List<Feedback> list){
		List<AdminFeedbackVO> voList = new ArrayList<AdminFeedbackVO>();
		for (Feedback adminFeedback : list) {
			voList.add(toVO4Admin(adminFeedback));
		}
		return voList;
	}

	@Override
	public AdminFeedbackVO toVO4Admin(Feedback feedback){
		String json = Json.toJson(feedback);
		return Json.toObject(json, AdminFeedbackVO.class, true);
	}
}
