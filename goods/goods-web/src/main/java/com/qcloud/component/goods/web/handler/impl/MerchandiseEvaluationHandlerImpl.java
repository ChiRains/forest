package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.MerchandiseEvaluationHandler;
import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.web.vo.MerchandiseEvaluationVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseEvaluationVO;

@Component
public class MerchandiseEvaluationHandlerImpl implements MerchandiseEvaluationHandler {

	@Override
	public List<MerchandiseEvaluationVO> toVOList(List<MerchandiseEvaluation> list){
		List<MerchandiseEvaluationVO> voList = new ArrayList<MerchandiseEvaluationVO>();
		for (MerchandiseEvaluation merchandiseEvaluation : list) {
			voList.add(toVO(merchandiseEvaluation));
		}
		return voList;
	}

	@Override
	public MerchandiseEvaluationVO toVO(MerchandiseEvaluation merchandiseEvaluation){
		String json = Json.toJson(merchandiseEvaluation);
		return Json.toObject(json, MerchandiseEvaluationVO.class, true);

	}

	@Override
	public List<AdminMerchandiseEvaluationVO> toVOList4Admin(List<MerchandiseEvaluation> list){
		List<AdminMerchandiseEvaluationVO> voList = new ArrayList<AdminMerchandiseEvaluationVO>();
		for (MerchandiseEvaluation adminMerchandiseEvaluation : list) {
			voList.add(toVO4Admin(adminMerchandiseEvaluation));
		}
		return voList;
	}

	@Override
	public AdminMerchandiseEvaluationVO toVO4Admin(MerchandiseEvaluation merchandiseEvaluation){
		String json = Json.toJson(merchandiseEvaluation);
		return Json.toObject(json, AdminMerchandiseEvaluationVO.class, true);
	}
}
