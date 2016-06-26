package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.MerchandiseVipDiscountHandler;
import com.qcloud.component.goods.model.MerchandiseVipDiscount;
import com.qcloud.component.goods.web.vo.MerchandiseVipDiscountVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseVipDiscountVO;

@Component
public class MerchandiseVipDiscountHandlerImpl implements MerchandiseVipDiscountHandler {

	@Override
	public List<MerchandiseVipDiscountVO> toVOList(List<MerchandiseVipDiscount> list){
		List<MerchandiseVipDiscountVO> voList = new ArrayList<MerchandiseVipDiscountVO>();
		for (MerchandiseVipDiscount merchandiseVipDiscount : list) {
			voList.add(toVO(merchandiseVipDiscount));
		}
		return voList;
	}

	@Override
	public MerchandiseVipDiscountVO toVO(MerchandiseVipDiscount merchandiseVipDiscount){
		String json = Json.toJson(merchandiseVipDiscount);
		return Json.toObject(json, MerchandiseVipDiscountVO.class, true);

	}

	@Override
	public List<AdminMerchandiseVipDiscountVO> toVOList4Admin(List<MerchandiseVipDiscount> list){
		List<AdminMerchandiseVipDiscountVO> voList = new ArrayList<AdminMerchandiseVipDiscountVO>();
		for (MerchandiseVipDiscount adminMerchandiseVipDiscount : list) {
			voList.add(toVO4Admin(adminMerchandiseVipDiscount));
		}
		return voList;
	}

	@Override
	public AdminMerchandiseVipDiscountVO toVO4Admin(MerchandiseVipDiscount merchandiseVipDiscount){
		String json = Json.toJson(merchandiseVipDiscount);
		return Json.toObject(json, AdminMerchandiseVipDiscountVO.class, true);
	}
}
