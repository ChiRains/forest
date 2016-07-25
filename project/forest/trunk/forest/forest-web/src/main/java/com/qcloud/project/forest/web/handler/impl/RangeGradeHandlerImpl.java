package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.web.handler.RangeGradeHandler;
import com.qcloud.project.forest.model.RangeGrade;
import com.qcloud.project.forest.web.vo.RangeGradeVO;
import com.qcloud.project.forest.web.vo.admin.AdminRangeGradeVO;

@Component
public class RangeGradeHandlerImpl implements RangeGradeHandler {

	@Override
	public List<RangeGradeVO> toVOList(List<RangeGrade> list){
		List<RangeGradeVO> voList = new ArrayList<RangeGradeVO>();
		for (RangeGrade rangeGrade : list) {
			voList.add(toVO(rangeGrade));
		}
		return voList;
	}

	@Override
	public RangeGradeVO toVO(RangeGrade rangeGrade){
		String json = Json.toJson(rangeGrade);
		return Json.toObject(json, RangeGradeVO.class, true);

	}

	@Override
	public List<AdminRangeGradeVO> toVOList4Admin(List<RangeGrade> list){
		List<AdminRangeGradeVO> voList = new ArrayList<AdminRangeGradeVO>();
		for (RangeGrade adminRangeGrade : list) {
			voList.add(toVO4Admin(adminRangeGrade));
		}
		return voList;
	}

	@Override
	public AdminRangeGradeVO toVO4Admin(RangeGrade rangeGrade){
		String json = Json.toJson(rangeGrade);
		return Json.toObject(json, AdminRangeGradeVO.class, true);
	}
}
