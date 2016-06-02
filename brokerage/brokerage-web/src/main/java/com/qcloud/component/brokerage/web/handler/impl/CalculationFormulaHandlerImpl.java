package com.qcloud.component.brokerage.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.brokerage.web.handler.CalculationFormulaHandler;
import com.qcloud.component.brokerage.model.CalculationFormula;
import com.qcloud.component.brokerage.model.key.TypeEnum.tradeUserDistributionType;
import com.qcloud.component.brokerage.web.vo.CalculationFormulaVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminCalculationFormulaVO;

@Component
public class CalculationFormulaHandlerImpl implements CalculationFormulaHandler {

	@Override
	public List<CalculationFormulaVO> toVOList(List<CalculationFormula> list){
		List<CalculationFormulaVO> voList = new ArrayList<CalculationFormulaVO>();
		for (CalculationFormula calculationFormula : list) {
			voList.add(toVO(calculationFormula));
		}
		return voList;
	}

	@Override
	public CalculationFormulaVO toVO(CalculationFormula calculationFormula){
		String json = Json.toJson(calculationFormula);
		return Json.toObject(json, CalculationFormulaVO.class, true);

	}

	@Override
	public List<AdminCalculationFormulaVO> toVOList4Admin(List<CalculationFormula> list){
		List<AdminCalculationFormulaVO> voList = new ArrayList<AdminCalculationFormulaVO>();
		for (CalculationFormula adminCalculationFormula : list) {
			voList.add(toVO4Admin(adminCalculationFormula));
		}
		return voList;
	}

	@Override
	public AdminCalculationFormulaVO toVO4Admin(CalculationFormula calculationFormula){
		String json = Json.toJson(calculationFormula);
		AdminCalculationFormulaVO vo=Json.toObject(json, AdminCalculationFormulaVO.class, true);
		String tradeUserDistributionStr=tradeUserDistributionType.NO.getName();
		if(vo.getTradeUserDistribution()==tradeUserDistributionType.YES.getKey()){
		    tradeUserDistributionStr=tradeUserDistributionType.YES.getName();
		}
		vo.setTradeUserDistributionStr(tradeUserDistributionStr);
		return vo;
		
	}
}
