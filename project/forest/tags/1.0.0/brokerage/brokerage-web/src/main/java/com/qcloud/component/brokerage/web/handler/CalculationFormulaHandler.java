package com.qcloud.component.brokerage.web.handler;

import java.util.List;

import com.qcloud.component.brokerage.model.CalculationFormula;
import com.qcloud.component.brokerage.web.vo.CalculationFormulaVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminCalculationFormulaVO;

public interface CalculationFormulaHandler {

	List<CalculationFormulaVO> toVOList(List<CalculationFormula> list);

	CalculationFormulaVO toVO(CalculationFormula calculationFormula);

	List<AdminCalculationFormulaVO> toVOList4Admin(List<CalculationFormula> list);

	AdminCalculationFormulaVO toVO4Admin(CalculationFormula calculationFormula);
}
