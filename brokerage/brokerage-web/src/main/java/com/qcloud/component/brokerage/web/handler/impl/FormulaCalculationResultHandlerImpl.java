package com.qcloud.component.brokerage.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.brokerage.web.handler.FormulaCalculationResultHandler;
import com.qcloud.component.brokerage.model.FormulaCalculationResult;
import com.qcloud.component.brokerage.model.key.TypeEnum.FormulaCalculationResultStateType;
import com.qcloud.component.brokerage.web.vo.FormulaCalculationResultVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminFormulaCalculationResultVO;

@Component
public class FormulaCalculationResultHandlerImpl implements FormulaCalculationResultHandler {

	@Override
	public List<FormulaCalculationResultVO> toVOList(List<FormulaCalculationResult> list){
		List<FormulaCalculationResultVO> voList = new ArrayList<FormulaCalculationResultVO>();
		for (FormulaCalculationResult formulaCalculationResult : list) {
			voList.add(toVO(formulaCalculationResult));
		}
		return voList;
	}

	@Override
	public FormulaCalculationResultVO toVO(FormulaCalculationResult formulaCalculationResult){
		String json = Json.toJson(formulaCalculationResult);
		return Json.toObject(json, FormulaCalculationResultVO.class, true);

	}

	@Override
	public List<AdminFormulaCalculationResultVO> toVOList4Admin(List<FormulaCalculationResult> list){
		List<AdminFormulaCalculationResultVO> voList = new ArrayList<AdminFormulaCalculationResultVO>();
		for (FormulaCalculationResult adminFormulaCalculationResult : list) {
			voList.add(toVO4Admin(adminFormulaCalculationResult));
		}
		return voList;
	}

	@Override
	public AdminFormulaCalculationResultVO toVO4Admin(FormulaCalculationResult formulaCalculationResult){
		String json = Json.toJson(formulaCalculationResult);
		AdminFormulaCalculationResultVO vo=Json.toObject(json, AdminFormulaCalculationResultVO.class, true);
		String resultStateStr="已分配";
		if(FormulaCalculationResultStateType.INIT.getKey()==vo.getState()){
		    resultStateStr=FormulaCalculationResultStateType.INIT.getName();
		}else if(FormulaCalculationResultStateType.ALLOCATING.getKey()==vo.getState()){
		    resultStateStr=FormulaCalculationResultStateType.ALLOCATING.getName();
		}
		vo.setResultStateStr(resultStateStr);
		return vo;
	}
}
