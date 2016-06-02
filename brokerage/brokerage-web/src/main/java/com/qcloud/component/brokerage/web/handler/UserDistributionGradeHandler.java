package com.qcloud.component.brokerage.web.handler;

import java.util.List;

import com.qcloud.component.brokerage.model.UserDistributionGrade;
import com.qcloud.component.brokerage.web.vo.UserDistributionGradeVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminUserDistributionGradeVO;

public interface UserDistributionGradeHandler {

	List<UserDistributionGradeVO> toVOList(List<UserDistributionGrade> list);

	UserDistributionGradeVO toVO(UserDistributionGrade userDistributionGrade);

	List<AdminUserDistributionGradeVO> toVOList4Admin(List<UserDistributionGrade> list);

	AdminUserDistributionGradeVO toVO4Admin(UserDistributionGrade userDistributionGrade);
}
