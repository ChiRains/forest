package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.MerchandiseDealRecordHandler;
import com.qcloud.component.goods.model.MerchandiseDealRecord;
import com.qcloud.component.goods.web.vo.MerchandiseDealRecordVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseDealRecordVO;

@Component
public class MerchandiseDealRecordHandlerImpl implements MerchandiseDealRecordHandler {

	@Override
	public List<MerchandiseDealRecordVO> toVOList(List<MerchandiseDealRecord> list){
		List<MerchandiseDealRecordVO> voList = new ArrayList<MerchandiseDealRecordVO>();
		for (MerchandiseDealRecord merchandiseDealRecord : list) {
			voList.add(toVO(merchandiseDealRecord));
		}
		return voList;
	}

	@Override
	public MerchandiseDealRecordVO toVO(MerchandiseDealRecord merchandiseDealRecord){
		String json = Json.toJson(merchandiseDealRecord);
		return Json.toObject(json, MerchandiseDealRecordVO.class, true);

	}

	@Override
	public List<AdminMerchandiseDealRecordVO> toVOList4Admin(List<MerchandiseDealRecord> list){
		List<AdminMerchandiseDealRecordVO> voList = new ArrayList<AdminMerchandiseDealRecordVO>();
		for (MerchandiseDealRecord adminMerchandiseDealRecord : list) {
			voList.add(toVO4Admin(adminMerchandiseDealRecord));
		}
		return voList;
	}

	@Override
	public AdminMerchandiseDealRecordVO toVO4Admin(MerchandiseDealRecord merchandiseDealRecord){
		String json = Json.toJson(merchandiseDealRecord);
		return Json.toObject(json, AdminMerchandiseDealRecordVO.class, true);
	}
}
