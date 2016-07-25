package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.web.handler.GradeMerchandiseHandler;
import com.qcloud.project.forest.model.GradeMerchandise;
import com.qcloud.project.forest.web.vo.GradeMerchandiseVO;
import com.qcloud.project.forest.web.vo.admin.AdminGradeMerchandiseVO;

@Component
public class GradeMerchandiseHandlerImpl implements GradeMerchandiseHandler {

	@Override
	public List<GradeMerchandiseVO> toVOList(List<GradeMerchandise> list){
		List<GradeMerchandiseVO> voList = new ArrayList<GradeMerchandiseVO>();
		for (GradeMerchandise gradeMerchandise : list) {
			voList.add(toVO(gradeMerchandise));
		}
		return voList;
	}

	@Override
	public GradeMerchandiseVO toVO(GradeMerchandise gradeMerchandise){
		String json = Json.toJson(gradeMerchandise);
		return Json.toObject(json, GradeMerchandiseVO.class, true);

	}

	@Override
	public List<AdminGradeMerchandiseVO> toVOList4Admin(List<GradeMerchandise> list){
		List<AdminGradeMerchandiseVO> voList = new ArrayList<AdminGradeMerchandiseVO>();
		for (GradeMerchandise adminGradeMerchandise : list) {
			voList.add(toVO4Admin(adminGradeMerchandise));
		}
		return voList;
	}

	@Override
	public AdminGradeMerchandiseVO toVO4Admin(GradeMerchandise gradeMerchandise){
		String json = Json.toJson(gradeMerchandise);
		return Json.toObject(json, AdminGradeMerchandiseVO.class, true);
	}
}
