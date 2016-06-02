package com.qcloud.component.orderform.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.orderform.web.handler.ExchangeOrderHandler;
import com.qcloud.component.orderform.model.ExchangeOrder;
import com.qcloud.component.orderform.web.vo.ExchangeOrderVO;
import com.qcloud.component.orderform.web.vo.admin.AdminExchangeOrderVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;

@Component
public class ExchangeOrderHandlerImpl implements ExchangeOrderHandler {
    @Autowired
    private PersonalcenterClient personalcenterClient;
	@Override
	public List<ExchangeOrderVO> toVOList(List<ExchangeOrder> list){
		List<ExchangeOrderVO> voList = new ArrayList<ExchangeOrderVO>();
		for (ExchangeOrder exchangeOrder : list) {
			voList.add(toVO(exchangeOrder));
		}
		return voList;
	}

	@Override
	public ExchangeOrderVO toVO(ExchangeOrder exchangeOrder){
		String json = Json.toJson(exchangeOrder);
		return Json.toObject(json, ExchangeOrderVO.class, true);

	}

	@Override
	public List<AdminExchangeOrderVO> toVOList4Admin(List<ExchangeOrder> list){
		List<AdminExchangeOrderVO> voList = new ArrayList<AdminExchangeOrderVO>();
		for (ExchangeOrder adminExchangeOrder : list) {
			voList.add(toVO4Admin(adminExchangeOrder));
		}
		return voList;
	}

	@Override
	public AdminExchangeOrderVO toVO4Admin(ExchangeOrder exchangeOrder){
		String json = Json.toJson(exchangeOrder);
		AdminExchangeOrderVO vo=Json.toObject(json, AdminExchangeOrderVO.class, true);
		vo.setUserName(personalcenterClient.getUser(vo.getUserId()).getMobile());
		return vo;
	}
}
