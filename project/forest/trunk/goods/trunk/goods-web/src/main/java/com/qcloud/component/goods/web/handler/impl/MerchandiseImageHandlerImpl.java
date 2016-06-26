package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.MerchandiseImageHandler;
import com.qcloud.component.goods.model.MerchandiseImage;
import com.qcloud.component.goods.web.vo.MerchandiseImageVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseImageVO;

@Component
public class MerchandiseImageHandlerImpl implements MerchandiseImageHandler {

	@Override
	public List<MerchandiseImageVO> toVOList(List<MerchandiseImage> list){
		List<MerchandiseImageVO> voList = new ArrayList<MerchandiseImageVO>();
		for (MerchandiseImage merchandiseImage : list) {
			voList.add(toVO(merchandiseImage));
		}
		return voList;
	}

	@Override
	public MerchandiseImageVO toVO(MerchandiseImage merchandiseImage){
		String json = Json.toJson(merchandiseImage);
		return Json.toObject(json, MerchandiseImageVO.class, true);

	}

	@Override
	public List<AdminMerchandiseImageVO> toVOList4Admin(List<MerchandiseImage> list){
		List<AdminMerchandiseImageVO> voList = new ArrayList<AdminMerchandiseImageVO>();
		for (MerchandiseImage adminMerchandiseImage : list) {
			voList.add(toVO4Admin(adminMerchandiseImage));
		}
		return voList;
	}

	@Override
	public AdminMerchandiseImageVO toVO4Admin(MerchandiseImage merchandiseImage){
		String json = Json.toJson(merchandiseImage);
		return Json.toObject(json, AdminMerchandiseImageVO.class, true);
	}
}
