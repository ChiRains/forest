package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.web.handler.BpCalculationHandler;
import com.qcloud.project.forest.model.BpCalculation;
import com.qcloud.project.forest.web.vo.BpCalculationVO;
import com.qcloud.project.forest.web.vo.admin.AdminBpCalculationVO;

@Component
public class BpCalculationHandlerImpl implements BpCalculationHandler {

	@Override
	public List<BpCalculationVO> toVOList(List<BpCalculation> list){
		List<BpCalculationVO> voList = new ArrayList<BpCalculationVO>();
		for (BpCalculation bpCalculation : list) {
			voList.add(toVO(bpCalculation));
		}
		return voList;
	}

	@Override
	public BpCalculationVO toVO(BpCalculation bpCalculation){
		String json = Json.toJson(bpCalculation);
		return Json.toObject(json, BpCalculationVO.class, true);

	}

	@Override
	public List<AdminBpCalculationVO> toVOList4Admin(List<BpCalculation> list){
		List<AdminBpCalculationVO> voList = new ArrayList<AdminBpCalculationVO>();
		for (BpCalculation adminBpCalculation : list) {
			voList.add(toVO4Admin(adminBpCalculation));
		}
		return voList;
	}

	@Override
	public AdminBpCalculationVO toVO4Admin(BpCalculation bpCalculation){
		String json = Json.toJson(bpCalculation);
		return Json.toObject(json, AdminBpCalculationVO.class, true);
	}
}
