package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.MerchandiseBrowsingHistoryHandler;
import com.qcloud.component.goods.model.MerchandiseBrowsingHistory;
import com.qcloud.component.goods.web.vo.MerchandiseBrowsingHistoryVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseBrowsingHistoryVO;

@Component
public class MerchandiseBrowsingHistoryHandlerImpl implements MerchandiseBrowsingHistoryHandler {

	@Override
	public List<MerchandiseBrowsingHistoryVO> toVOList(List<MerchandiseBrowsingHistory> list){
		List<MerchandiseBrowsingHistoryVO> voList = new ArrayList<MerchandiseBrowsingHistoryVO>();
		for (MerchandiseBrowsingHistory merchandiseBrowsingHistory : list) {
			voList.add(toVO(merchandiseBrowsingHistory));
		}
		return voList;
	}

	@Override
	public MerchandiseBrowsingHistoryVO toVO(MerchandiseBrowsingHistory merchandiseBrowsingHistory){
		String json = Json.toJson(merchandiseBrowsingHistory);
		return Json.toObject(json, MerchandiseBrowsingHistoryVO.class, true);

	}

	@Override
	public List<AdminMerchandiseBrowsingHistoryVO> toVOList4Admin(List<MerchandiseBrowsingHistory> list){
		List<AdminMerchandiseBrowsingHistoryVO> voList = new ArrayList<AdminMerchandiseBrowsingHistoryVO>();
		for (MerchandiseBrowsingHistory adminMerchandiseBrowsingHistory : list) {
			voList.add(toVO4Admin(adminMerchandiseBrowsingHistory));
		}
		return voList;
	}

	@Override
	public AdminMerchandiseBrowsingHistoryVO toVO4Admin(MerchandiseBrowsingHistory merchandiseBrowsingHistory){
		String json = Json.toJson(merchandiseBrowsingHistory);
		return Json.toObject(json, AdminMerchandiseBrowsingHistoryVO.class, true);
	}
}
