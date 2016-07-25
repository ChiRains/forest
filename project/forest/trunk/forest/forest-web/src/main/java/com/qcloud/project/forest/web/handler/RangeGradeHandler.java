package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.RangeGrade;
import com.qcloud.project.forest.web.vo.RangeGradeVO;
import com.qcloud.project.forest.web.vo.admin.AdminRangeGradeVO;

public interface RangeGradeHandler {

	List<RangeGradeVO> toVOList(List<RangeGrade> list);

	RangeGradeVO toVO(RangeGrade rangeGrade);

	List<AdminRangeGradeVO> toVOList4Admin(List<RangeGrade> list);

	AdminRangeGradeVO toVO4Admin(RangeGrade rangeGrade);
}
