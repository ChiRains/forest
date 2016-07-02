package com.qcloud.component.brokerage.web.handler;

import java.util.List;

import com.qcloud.component.brokerage.model.FormulaCalculationResult;
import com.qcloud.component.brokerage.web.vo.FormulaCalculationResultVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminFormulaCalculationResultVO;

public interface FormulaCalculationResultHandler {

	List<FormulaCalculationResultVO> toVOList(List<FormulaCalculationResult> list);

	FormulaCalculationResultVO toVO(FormulaCalculationResult formulaCalculationResult);

	List<AdminFormulaCalculationResultVO> toVOList4Admin(List<FormulaCalculationResult> list);

	AdminFormulaCalculationResultVO toVO4Admin(FormulaCalculationResult formulaCalculationResult);
}
