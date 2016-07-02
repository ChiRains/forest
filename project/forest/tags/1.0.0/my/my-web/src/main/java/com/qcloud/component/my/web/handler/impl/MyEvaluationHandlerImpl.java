package com.qcloud.component.my.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.my.model.MyEvaluation;
import com.qcloud.component.my.web.handler.MyEvaluationHandler;
import com.qcloud.component.my.web.vo.MyEvaluationVO;
import com.qcloud.component.my.web.vo.admin.AdminMyEvaluationVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class MyEvaluationHandlerImpl implements MyEvaluationHandler {

	@Override
	public List<MyEvaluationVO> toVOList(List<MyEvaluation> list){
		List<MyEvaluationVO> voList = new ArrayList<MyEvaluationVO>();
		for (MyEvaluation MyEvaluation : list) {
			voList.add(toVO(MyEvaluation));
		}
		return voList;
	}

	@Override
	public MyEvaluationVO toVO(MyEvaluation MyEvaluation){
		String json = Json.toJson(MyEvaluation);
		return Json.toObject(json, MyEvaluationVO.class, true);

	}

	@Override
	public List<AdminMyEvaluationVO> toVOList4Admin(List<MyEvaluation> list){
		List<AdminMyEvaluationVO> voList = new ArrayList<AdminMyEvaluationVO>();
		for (MyEvaluation adminMyEvaluation : list) {
			voList.add(toVO4Admin(adminMyEvaluation));
		}
		return voList;
	}

	@Override
	public AdminMyEvaluationVO toVO4Admin(MyEvaluation MyEvaluation){
		String json = Json.toJson(MyEvaluation);
		return Json.toObject(json, AdminMyEvaluationVO.class, true);
	}
}
