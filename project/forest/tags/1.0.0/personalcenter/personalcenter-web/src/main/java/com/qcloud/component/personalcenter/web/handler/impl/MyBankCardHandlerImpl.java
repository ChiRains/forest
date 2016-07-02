package com.qcloud.component.personalcenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.personalcenter.web.handler.MyBankCardHandler;
import com.qcloud.component.personalcenter.model.MyBankCard;
import com.qcloud.component.personalcenter.web.vo.MyBankCardVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMyBankCardVO;

@Component
public class MyBankCardHandlerImpl implements MyBankCardHandler {

	@Override
	public List<MyBankCardVO> toVOList(List<MyBankCard> list){
		List<MyBankCardVO> voList = new ArrayList<MyBankCardVO>();
		for (MyBankCard myBankCard : list) {
			voList.add(toVO(myBankCard));
		}
		return voList;
	}

	@Override
	public MyBankCardVO toVO(MyBankCard myBankCard){
		String json = Json.toJson(myBankCard);
		return Json.toObject(json, MyBankCardVO.class, true);

	}

	@Override
	public List<AdminMyBankCardVO> toVOList4Admin(List<MyBankCard> list){
		List<AdminMyBankCardVO> voList = new ArrayList<AdminMyBankCardVO>();
		for (MyBankCard adminMyBankCard : list) {
			voList.add(toVO4Admin(adminMyBankCard));
		}
		return voList;
	}

	@Override
	public AdminMyBankCardVO toVO4Admin(MyBankCard myBankCard){
		String json = Json.toJson(myBankCard);
		return Json.toObject(json, AdminMyBankCardVO.class, true);
	}
}
