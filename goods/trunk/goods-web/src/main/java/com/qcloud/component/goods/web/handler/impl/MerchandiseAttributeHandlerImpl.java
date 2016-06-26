package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.MerchandiseAttributeHandler;
import com.qcloud.component.goods.model.MerchandiseAttribute;
import com.qcloud.component.goods.web.vo.MerchandiseAttributeVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseAttributeVO;

@Component
public class MerchandiseAttributeHandlerImpl implements MerchandiseAttributeHandler {

	@Override
	public List<MerchandiseAttributeVO> toVOList(List<MerchandiseAttribute> list){
		List<MerchandiseAttributeVO> voList = new ArrayList<MerchandiseAttributeVO>();
		for (MerchandiseAttribute merchandiseAttribute : list) {
			voList.add(toVO(merchandiseAttribute));
		}
		return voList;
	}

	@Override
	public MerchandiseAttributeVO toVO(MerchandiseAttribute merchandiseAttribute){
		String json = Json.toJson(merchandiseAttribute);
		return Json.toObject(json, MerchandiseAttributeVO.class, true);

	}

	@Override
	public List<AdminMerchandiseAttributeVO> toVOList4Admin(List<MerchandiseAttribute> list){
		List<AdminMerchandiseAttributeVO> voList = new ArrayList<AdminMerchandiseAttributeVO>();
		for (MerchandiseAttribute adminMerchandiseAttribute : list) {
			voList.add(toVO4Admin(adminMerchandiseAttribute));
		}
		return voList;
	}

	@Override
	public AdminMerchandiseAttributeVO toVO4Admin(MerchandiseAttribute merchandiseAttribute){
		String json = Json.toJson(merchandiseAttribute);
		return Json.toObject(json, AdminMerchandiseAttributeVO.class, true);
	}
}
