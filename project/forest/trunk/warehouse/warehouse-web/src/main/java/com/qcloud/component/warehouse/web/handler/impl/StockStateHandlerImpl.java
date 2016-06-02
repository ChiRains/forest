package com.qcloud.component.warehouse.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.warehouse.web.handler.StockStateHandler;
import com.qcloud.component.warehouse.model.StockState;
import com.qcloud.component.warehouse.web.vo.StockStateVO;
import com.qcloud.component.warehouse.web.vo.admin.AdminStockStateVO;

@Component
public class StockStateHandlerImpl implements StockStateHandler {

	@Override
	public List<StockStateVO> toVOList(List<StockState> list){
		List<StockStateVO> voList = new ArrayList<StockStateVO>();
		for (StockState stockState : list) {
			voList.add(toVO(stockState));
		}
		return voList;
	}

	@Override
	public StockStateVO toVO(StockState stockState){
		String json = Json.toJson(stockState);
		return Json.toObject(json, StockStateVO.class, true);

	}

	@Override
	public List<AdminStockStateVO> toVOList4Admin(List<StockState> list){
		List<AdminStockStateVO> voList = new ArrayList<AdminStockStateVO>();
		for (StockState adminStockState : list) {
			voList.add(toVO4Admin(adminStockState));
		}
		return voList;
	}

	@Override
	public AdminStockStateVO toVO4Admin(StockState stockState){
		String json = Json.toJson(stockState);
		return Json.toObject(json, AdminStockStateVO.class, true);
	}
}
