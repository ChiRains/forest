package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.web.handler.ForestOrderHandler;
import com.qcloud.project.forest.model.ForestOrder;
import com.qcloud.project.forest.web.vo.ForestOrderVO;
import com.qcloud.project.forest.web.vo.admin.AdminForestOrderVO;

@Component
public class ForestOrderHandlerImpl implements ForestOrderHandler {

	@Override
	public List<ForestOrderVO> toVOList(List<ForestOrder> list){
		List<ForestOrderVO> voList = new ArrayList<ForestOrderVO>();
		for (ForestOrder forestOrder : list) {
			voList.add(toVO(forestOrder));
		}
		return voList;
	}

	@Override
	public ForestOrderVO toVO(ForestOrder forestOrder){
		String json = Json.toJson(forestOrder);
		return Json.toObject(json, ForestOrderVO.class, true);

	}

	@Override
	public List<AdminForestOrderVO> toVOList4Admin(List<ForestOrder> list){
		List<AdminForestOrderVO> voList = new ArrayList<AdminForestOrderVO>();
		for (ForestOrder adminForestOrder : list) {
			voList.add(toVO4Admin(adminForestOrder));
		}
		return voList;
	}

	@Override
	public AdminForestOrderVO toVO4Admin(ForestOrder forestOrder){
		String json = Json.toJson(forestOrder);
		return Json.toObject(json, AdminForestOrderVO.class, true);
	}
}
