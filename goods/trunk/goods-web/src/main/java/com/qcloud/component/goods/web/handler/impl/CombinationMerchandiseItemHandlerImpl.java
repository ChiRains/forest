package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.CombinationMerchandiseItemHandler;
import com.qcloud.component.goods.model.CombinationMerchandiseItem;
import com.qcloud.component.goods.web.vo.CombinationMerchandiseItemVO;
import com.qcloud.component.goods.web.vo.admin.AdminCombinationMerchandiseItemVO;

@Component
public class CombinationMerchandiseItemHandlerImpl implements CombinationMerchandiseItemHandler {

	@Override
	public List<CombinationMerchandiseItemVO> toVOList(List<CombinationMerchandiseItem> list){
		List<CombinationMerchandiseItemVO> voList = new ArrayList<CombinationMerchandiseItemVO>();
		for (CombinationMerchandiseItem combinationMerchandiseItem : list) {
			voList.add(toVO(combinationMerchandiseItem));
		}
		return voList;
	}

	@Override
	public CombinationMerchandiseItemVO toVO(CombinationMerchandiseItem combinationMerchandiseItem){
		String json = Json.toJson(combinationMerchandiseItem);
		return Json.toObject(json, CombinationMerchandiseItemVO.class, true);

	}

	@Override
	public List<AdminCombinationMerchandiseItemVO> toVOList4Admin(List<CombinationMerchandiseItem> list){
		List<AdminCombinationMerchandiseItemVO> voList = new ArrayList<AdminCombinationMerchandiseItemVO>();
		for (CombinationMerchandiseItem adminCombinationMerchandiseItem : list) {
			voList.add(toVO4Admin(adminCombinationMerchandiseItem));
		}
		return voList;
	}

	@Override
	public AdminCombinationMerchandiseItemVO toVO4Admin(CombinationMerchandiseItem combinationMerchandiseItem){
		String json = Json.toJson(combinationMerchandiseItem);
		return Json.toObject(json, AdminCombinationMerchandiseItemVO.class, true);
	}
}
