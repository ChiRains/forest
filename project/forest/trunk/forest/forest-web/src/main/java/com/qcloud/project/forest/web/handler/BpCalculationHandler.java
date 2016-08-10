package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.BpCalculation;
import com.qcloud.project.forest.web.vo.BpCalculationVO;
import com.qcloud.project.forest.web.vo.admin.AdminBpCalculationVO;

public interface BpCalculationHandler {

	List<BpCalculationVO> toVOList(List<BpCalculation> list);

	BpCalculationVO toVO(BpCalculation bpCalculation);

	List<AdminBpCalculationVO> toVOList4Admin(List<BpCalculation> list);

	AdminBpCalculationVO toVO4Admin(BpCalculation bpCalculation);
}
