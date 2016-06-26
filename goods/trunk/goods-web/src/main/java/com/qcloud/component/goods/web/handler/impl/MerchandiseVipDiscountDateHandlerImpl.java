package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.MerchandiseVipDiscountDateHandler;
import com.qcloud.component.goods.model.MerchandiseVipDiscountDate;
import com.qcloud.component.goods.web.vo.MerchandiseVipDiscountDateVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseVipDiscountDateVO;

@Component
public class MerchandiseVipDiscountDateHandlerImpl implements MerchandiseVipDiscountDateHandler {

	@Override
	public List<MerchandiseVipDiscountDateVO> toVOList(List<MerchandiseVipDiscountDate> list){
		List<MerchandiseVipDiscountDateVO> voList = new ArrayList<MerchandiseVipDiscountDateVO>();
		for (MerchandiseVipDiscountDate merchandiseVipDiscountDate : list) {
			voList.add(toVO(merchandiseVipDiscountDate));
		}
		return voList;
	}

	@Override
	public MerchandiseVipDiscountDateVO toVO(MerchandiseVipDiscountDate merchandiseVipDiscountDate){
		String json = Json.toJson(merchandiseVipDiscountDate);
		return Json.toObject(json, MerchandiseVipDiscountDateVO.class, true);

	}

	@Override
	public List<AdminMerchandiseVipDiscountDateVO> toVOList4Admin(List<MerchandiseVipDiscountDate> list){
		List<AdminMerchandiseVipDiscountDateVO> voList = new ArrayList<AdminMerchandiseVipDiscountDateVO>();
		for (MerchandiseVipDiscountDate adminMerchandiseVipDiscountDate : list) {
			voList.add(toVO4Admin(adminMerchandiseVipDiscountDate));
		}
		return voList;
	}

	@Override
	public AdminMerchandiseVipDiscountDateVO toVO4Admin(MerchandiseVipDiscountDate merchandiseVipDiscountDate){
		String json = Json.toJson(merchandiseVipDiscountDate);
		return Json.toObject(json, AdminMerchandiseVipDiscountDateVO.class, true);
	}
}
