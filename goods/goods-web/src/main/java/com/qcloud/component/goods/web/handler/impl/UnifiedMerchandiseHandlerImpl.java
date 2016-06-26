package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.UnifiedMerchandiseHandler;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.web.vo.UnifiedMerchandiseVO;
import com.qcloud.component.goods.web.vo.admin.AdminUnifiedMerchandiseVO;

@Component
public class UnifiedMerchandiseHandlerImpl implements UnifiedMerchandiseHandler {

	@Override
	public List<UnifiedMerchandiseVO> toVOList(List<UnifiedMerchandise> list){
		List<UnifiedMerchandiseVO> voList = new ArrayList<UnifiedMerchandiseVO>();
		for (UnifiedMerchandise unifiedMerchandise : list) {
			voList.add(toVO(unifiedMerchandise));
		}
		return voList;
	}

	@Override
	public UnifiedMerchandiseVO toVO(UnifiedMerchandise unifiedMerchandise){
		String json = Json.toJson(unifiedMerchandise);
		return Json.toObject(json, UnifiedMerchandiseVO.class, true);

	}

	@Override
	public List<AdminUnifiedMerchandiseVO> toVOList4Admin(List<UnifiedMerchandise> list){
		List<AdminUnifiedMerchandiseVO> voList = new ArrayList<AdminUnifiedMerchandiseVO>();
		for (UnifiedMerchandise adminUnifiedMerchandise : list) {
			voList.add(toVO4Admin(adminUnifiedMerchandise));
		}
		return voList;
	}

	@Override
	public AdminUnifiedMerchandiseVO toVO4Admin(UnifiedMerchandise unifiedMerchandise){
		String json = Json.toJson(unifiedMerchandise);
		return Json.toObject(json, AdminUnifiedMerchandiseVO.class, true);
	}
}
