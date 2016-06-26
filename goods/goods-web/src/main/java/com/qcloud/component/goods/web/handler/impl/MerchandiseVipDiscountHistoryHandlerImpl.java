package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.MerchandiseVipDiscountHistoryHandler;
import com.qcloud.component.goods.model.MerchandiseVipDiscountHistory;
import com.qcloud.component.goods.web.vo.MerchandiseVipDiscountHistoryVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseVipDiscountHistoryVO;

@Component
public class MerchandiseVipDiscountHistoryHandlerImpl implements MerchandiseVipDiscountHistoryHandler {

	@Override
	public List<MerchandiseVipDiscountHistoryVO> toVOList(List<MerchandiseVipDiscountHistory> list){
		List<MerchandiseVipDiscountHistoryVO> voList = new ArrayList<MerchandiseVipDiscountHistoryVO>();
		for (MerchandiseVipDiscountHistory merchandiseVipDiscountHistory : list) {
			voList.add(toVO(merchandiseVipDiscountHistory));
		}
		return voList;
	}

	@Override
	public MerchandiseVipDiscountHistoryVO toVO(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory){
		String json = Json.toJson(merchandiseVipDiscountHistory);
		return Json.toObject(json, MerchandiseVipDiscountHistoryVO.class, true);

	}

	@Override
	public List<AdminMerchandiseVipDiscountHistoryVO> toVOList4Admin(List<MerchandiseVipDiscountHistory> list){
		List<AdminMerchandiseVipDiscountHistoryVO> voList = new ArrayList<AdminMerchandiseVipDiscountHistoryVO>();
		for (MerchandiseVipDiscountHistory adminMerchandiseVipDiscountHistory : list) {
			voList.add(toVO4Admin(adminMerchandiseVipDiscountHistory));
		}
		return voList;
	}

	@Override
	public AdminMerchandiseVipDiscountHistoryVO toVO4Admin(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory){
		String json = Json.toJson(merchandiseVipDiscountHistory);
		return Json.toObject(json, AdminMerchandiseVipDiscountHistoryVO.class, true);
	}
}
