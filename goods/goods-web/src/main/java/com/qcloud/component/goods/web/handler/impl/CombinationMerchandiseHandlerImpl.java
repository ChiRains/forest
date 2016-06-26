package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.CombinationMerchandiseHandler;
import com.qcloud.component.goods.model.CombinationMerchandise;
import com.qcloud.component.goods.web.vo.CombinationMerchandiseVO;
import com.qcloud.component.goods.web.vo.admin.AdminCombinationMerchandiseVO;

@Component
public class CombinationMerchandiseHandlerImpl implements CombinationMerchandiseHandler {

	@Override
	public List<CombinationMerchandiseVO> toVOList(List<CombinationMerchandise> list){
		List<CombinationMerchandiseVO> voList = new ArrayList<CombinationMerchandiseVO>();
		for (CombinationMerchandise combinationMerchandise : list) {
			voList.add(toVO(combinationMerchandise));
		}
		return voList;
	}

	@Override
	public CombinationMerchandiseVO toVO(CombinationMerchandise combinationMerchandise){
		String json = Json.toJson(combinationMerchandise);
		return Json.toObject(json, CombinationMerchandiseVO.class, true);

	}

	@Override
	public List<AdminCombinationMerchandiseVO> toVOList4Admin(List<CombinationMerchandise> list){
		List<AdminCombinationMerchandiseVO> voList = new ArrayList<AdminCombinationMerchandiseVO>();
		for (CombinationMerchandise adminCombinationMerchandise : list) {
			voList.add(toVO4Admin(adminCombinationMerchandise));
		}
		return voList;
	}

	@Override
	public AdminCombinationMerchandiseVO toVO4Admin(CombinationMerchandise combinationMerchandise){
		String json = Json.toJson(combinationMerchandise);
		return Json.toObject(json, AdminCombinationMerchandiseVO.class, true);
	}
}
