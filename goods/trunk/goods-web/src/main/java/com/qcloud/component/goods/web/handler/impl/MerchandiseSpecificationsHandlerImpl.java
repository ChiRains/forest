package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.MerchandiseSpecificationsHandler;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.web.vo.MerchandiseSpecificationsVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseSpecificationsVO;

@Component
public class MerchandiseSpecificationsHandlerImpl implements MerchandiseSpecificationsHandler {

	@Override
	public List<MerchandiseSpecificationsVO> toVOList(List<MerchandiseSpecifications> list){
		List<MerchandiseSpecificationsVO> voList = new ArrayList<MerchandiseSpecificationsVO>();
		for (MerchandiseSpecifications merchandiseSpecifications : list) {
			voList.add(toVO(merchandiseSpecifications));
		}
		return voList;
	}

	@Override
	public MerchandiseSpecificationsVO toVO(MerchandiseSpecifications merchandiseSpecifications){
		String json = Json.toJson(merchandiseSpecifications);
		return Json.toObject(json, MerchandiseSpecificationsVO.class, true);

	}

	@Override
	public List<AdminMerchandiseSpecificationsVO> toVOList4Admin(List<MerchandiseSpecifications> list){
		List<AdminMerchandiseSpecificationsVO> voList = new ArrayList<AdminMerchandiseSpecificationsVO>();
		for (MerchandiseSpecifications adminMerchandiseSpecifications : list) {
			voList.add(toVO4Admin(adminMerchandiseSpecifications));
		}
		return voList;
	}

	@Override
	public AdminMerchandiseSpecificationsVO toVO4Admin(MerchandiseSpecifications merchandiseSpecifications){
		String json = Json.toJson(merchandiseSpecifications);
		return Json.toObject(json, AdminMerchandiseSpecificationsVO.class, true);
	}
}
