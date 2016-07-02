package com.qcloud.component.brokerage.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.component.brokerage.service.CalculationFormulaService;
import com.qcloud.component.brokerage.service.DistributionGradeService;
import com.qcloud.component.brokerage.web.handler.AllocationSchemeHandler;
import com.qcloud.component.brokerage.model.AllocationScheme;
import com.qcloud.component.brokerage.model.CalculationFormula;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.web.vo.AllocationSchemeVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminAllocationSchemeVO;

@Component
public class AllocationSchemeHandlerImpl implements AllocationSchemeHandler {

    @Autowired
    private CalculationFormulaService calculationFormulaService;
    @Autowired
    private DistributionGradeService distributionGradeService;
    
	@Override
	public List<AllocationSchemeVO> toVOList(List<AllocationScheme> list){
		List<AllocationSchemeVO> voList = new ArrayList<AllocationSchemeVO>();
		for (AllocationScheme allocationScheme : list) {
			voList.add(toVO(allocationScheme));
		}
		return voList;
	}

	@Override
	public AllocationSchemeVO toVO(AllocationScheme allocationScheme){
		String json = Json.toJson(allocationScheme);
		return Json.toObject(json, AllocationSchemeVO.class, true);

	}

	@Override
	public List<AdminAllocationSchemeVO> toVOList4Admin(List<AllocationScheme> list){
		List<AdminAllocationSchemeVO> voList = new ArrayList<AdminAllocationSchemeVO>();
		for (AllocationScheme adminAllocationScheme : list) {
			voList.add(toVO4Admin(adminAllocationScheme));
		}
		return voList;
	}

	@Override
	public AdminAllocationSchemeVO toVO4Admin(AllocationScheme allocationScheme){
		String json = Json.toJson(allocationScheme);
		AdminAllocationSchemeVO vo=Json.toObject(json, AdminAllocationSchemeVO.class, true);
		CalculationFormula calculationFormula=calculationFormulaService.get(vo.getFormulaId());
		AssertUtil.assertNotNull(calculationFormula, "分佣公式不存在.");
		vo.setFormulaName(calculationFormula.getName());
		DistributionGrade distributionGrade=distributionGradeService.get(vo.getAllocationGradeId());
		AssertUtil.assertNotNull(distributionGrade, "会员等级不存在.");
		vo.setAllocationGradeName(distributionGrade.getName());
		return vo;
	}
}
