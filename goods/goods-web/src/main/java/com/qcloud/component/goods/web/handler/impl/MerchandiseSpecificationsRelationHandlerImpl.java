package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.MerchandiseSpecificationsRelationHandler;
import com.qcloud.component.goods.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.goods.web.vo.MerchandiseSpecificationsRelationVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseSpecificationsRelationVO;

@Component
public class MerchandiseSpecificationsRelationHandlerImpl implements MerchandiseSpecificationsRelationHandler {

	@Override
	public List<MerchandiseSpecificationsRelationVO> toVOList(List<MerchandiseSpecificationsRelation> list){
		List<MerchandiseSpecificationsRelationVO> voList = new ArrayList<MerchandiseSpecificationsRelationVO>();
		for (MerchandiseSpecificationsRelation merchandiseSpecificationsRelation : list) {
			voList.add(toVO(merchandiseSpecificationsRelation));
		}
		return voList;
	}

	@Override
	public MerchandiseSpecificationsRelationVO toVO(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation){
		String json = Json.toJson(merchandiseSpecificationsRelation);
		return Json.toObject(json, MerchandiseSpecificationsRelationVO.class, true);

	}

	@Override
	public List<AdminMerchandiseSpecificationsRelationVO> toVOList4Admin(List<MerchandiseSpecificationsRelation> list){
		List<AdminMerchandiseSpecificationsRelationVO> voList = new ArrayList<AdminMerchandiseSpecificationsRelationVO>();
		for (MerchandiseSpecificationsRelation adminMerchandiseSpecificationsRelation : list) {
			voList.add(toVO4Admin(adminMerchandiseSpecificationsRelation));
		}
		return voList;
	}

	@Override
	public AdminMerchandiseSpecificationsRelationVO toVO4Admin(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation){
		String json = Json.toJson(merchandiseSpecificationsRelation);
		return Json.toObject(json, AdminMerchandiseSpecificationsRelationVO.class, true);
	}
}
