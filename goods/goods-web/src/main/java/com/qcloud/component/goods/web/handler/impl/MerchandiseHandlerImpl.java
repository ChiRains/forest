package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.MerchandiseHandler;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.web.vo.MerchandiseVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseVO;

@Component
public class MerchandiseHandlerImpl implements MerchandiseHandler {

	@Override
	public List<MerchandiseVO> toVOList(List<Merchandise> list){
		List<MerchandiseVO> voList = new ArrayList<MerchandiseVO>();
		for (Merchandise merchandise : list) {
			voList.add(toVO(merchandise));
		}
		return voList;
	}

	@Override
	public MerchandiseVO toVO(Merchandise merchandise){
		String json = Json.toJson(merchandise);
		return Json.toObject(json, MerchandiseVO.class, true);

	}

	@Override
	public List<AdminMerchandiseVO> toVOList4Admin(List<Merchandise> list){
		List<AdminMerchandiseVO> voList = new ArrayList<AdminMerchandiseVO>();
		for (Merchandise adminMerchandise : list) {
			voList.add(toVO4Admin(adminMerchandise));
		}
		return voList;
	}

	@Override
	public AdminMerchandiseVO toVO4Admin(Merchandise merchandise){
		String json = Json.toJson(merchandise);
		return Json.toObject(json, AdminMerchandiseVO.class, true);
	}
}
