package com.qcloud.component.brokerage.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.brokerage.web.handler.UserDistributionGradeHandler;
import com.qcloud.component.brokerage.model.UserDistributionGrade;
import com.qcloud.component.brokerage.web.vo.UserDistributionGradeVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminUserDistributionGradeVO;

@Component
public class UserDistributionGradeHandlerImpl implements UserDistributionGradeHandler {

	@Override
	public List<UserDistributionGradeVO> toVOList(List<UserDistributionGrade> list){
		List<UserDistributionGradeVO> voList = new ArrayList<UserDistributionGradeVO>();
		for (UserDistributionGrade userDistributionGrade : list) {
			voList.add(toVO(userDistributionGrade));
		}
		return voList;
	}

	@Override
	public UserDistributionGradeVO toVO(UserDistributionGrade userDistributionGrade){
		String json = Json.toJson(userDistributionGrade);
		return Json.toObject(json, UserDistributionGradeVO.class, true);

	}

	@Override
	public List<AdminUserDistributionGradeVO> toVOList4Admin(List<UserDistributionGrade> list){
		List<AdminUserDistributionGradeVO> voList = new ArrayList<AdminUserDistributionGradeVO>();
		for (UserDistributionGrade adminUserDistributionGrade : list) {
			voList.add(toVO4Admin(adminUserDistributionGrade));
		}
		return voList;
	}

	@Override
	public AdminUserDistributionGradeVO toVO4Admin(UserDistributionGrade userDistributionGrade){
		String json = Json.toJson(userDistributionGrade);
		return Json.toObject(json, AdminUserDistributionGradeVO.class, true);
	}
}
