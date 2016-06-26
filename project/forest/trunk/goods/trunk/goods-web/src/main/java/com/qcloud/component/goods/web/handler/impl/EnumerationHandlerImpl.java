package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.EnumerationHandler;
import com.qcloud.component.goods.model.Enumeration;
import com.qcloud.component.goods.web.vo.EnumerationVO;
import com.qcloud.component.goods.web.vo.admin.AdminEnumerationVO;

@Component
public class EnumerationHandlerImpl implements EnumerationHandler {

	@Override
	public List<EnumerationVO> toVOList(List<Enumeration> list){
		List<EnumerationVO> voList = new ArrayList<EnumerationVO>();
		for (Enumeration enumeration : list) {
			voList.add(toVO(enumeration));
		}
		return voList;
	}

	@Override
	public EnumerationVO toVO(Enumeration enumeration){
		String json = Json.toJson(enumeration);
		return Json.toObject(json, EnumerationVO.class, true);

	}

	@Override
	public List<AdminEnumerationVO> toVOList4Admin(List<Enumeration> list){
		List<AdminEnumerationVO> voList = new ArrayList<AdminEnumerationVO>();
		for (Enumeration adminEnumeration : list) {
			voList.add(toVO4Admin(adminEnumeration));
		}
		return voList;
	}

	@Override
	public AdminEnumerationVO toVO4Admin(Enumeration enumeration){
		String json = Json.toJson(enumeration);
		return Json.toObject(json, AdminEnumerationVO.class, true);
	}
}
