package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.MonthHotSaleHandler;
import com.qcloud.component.goods.model.MonthHotSale;
import com.qcloud.component.goods.web.vo.MonthHotSaleVO;
import com.qcloud.component.goods.web.vo.admin.AdminMonthHotSaleVO;

@Component
public class MonthHotSaleHandlerImpl implements MonthHotSaleHandler {

	@Override
	public List<MonthHotSaleVO> toVOList(List<MonthHotSale> list){
		List<MonthHotSaleVO> voList = new ArrayList<MonthHotSaleVO>();
		for (MonthHotSale monthHotSale : list) {
			voList.add(toVO(monthHotSale));
		}
		return voList;
	}

	@Override
	public MonthHotSaleVO toVO(MonthHotSale monthHotSale){
		String json = Json.toJson(monthHotSale);
		return Json.toObject(json, MonthHotSaleVO.class, true);

	}

	@Override
	public List<AdminMonthHotSaleVO> toVOList4Admin(List<MonthHotSale> list){
		List<AdminMonthHotSaleVO> voList = new ArrayList<AdminMonthHotSaleVO>();
		for (MonthHotSale adminMonthHotSale : list) {
			voList.add(toVO4Admin(adminMonthHotSale));
		}
		return voList;
	}

	@Override
	public AdminMonthHotSaleVO toVO4Admin(MonthHotSale monthHotSale){
		String json = Json.toJson(monthHotSale);
		return Json.toObject(json, AdminMonthHotSaleVO.class, true);
	}
}
