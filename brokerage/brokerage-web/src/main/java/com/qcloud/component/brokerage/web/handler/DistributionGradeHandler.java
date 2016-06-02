package com.qcloud.component.brokerage.web.handler;

import java.util.List;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.web.vo.DistributionGradeVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminDistributionGradeTreeVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminDistributionGradeVO;
import com.qcloud.component.publicdata.QClassify;

public interface DistributionGradeHandler {

	List<DistributionGradeVO> toVOList(List<DistributionGrade> list);

	DistributionGradeVO toVO(DistributionGrade distributionGrade);

	List<AdminDistributionGradeVO> toVOList4Admin(List<DistributionGrade> list);

	AdminDistributionGradeVO toVO4Admin(DistributionGrade distributionGrade);
	
	List<AdminDistributionGradeTreeVO> toTree();
}
