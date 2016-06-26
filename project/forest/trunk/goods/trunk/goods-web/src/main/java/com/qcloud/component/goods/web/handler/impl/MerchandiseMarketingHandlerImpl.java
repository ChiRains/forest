package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.MerchandiseMarketingHandler;
import com.qcloud.component.goods.model.MerchandiseMarketing;
import com.qcloud.component.goods.web.vo.MerchandiseMarketingVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseMarketingVO;

@Component
public class MerchandiseMarketingHandlerImpl implements MerchandiseMarketingHandler {

	@Override
	public List<MerchandiseMarketingVO> toVOList(List<MerchandiseMarketing> list){
		List<MerchandiseMarketingVO> voList = new ArrayList<MerchandiseMarketingVO>();
		for (MerchandiseMarketing merchandiseMarketing : list) {
			voList.add(toVO(merchandiseMarketing));
		}
		return voList;
	}

	@Override
	public MerchandiseMarketingVO toVO(MerchandiseMarketing merchandiseMarketing){
		String json = Json.toJson(merchandiseMarketing);
		return Json.toObject(json, MerchandiseMarketingVO.class, true);

	}

	@Override
	public List<AdminMerchandiseMarketingVO> toVOList4Admin(List<MerchandiseMarketing> list){
		List<AdminMerchandiseMarketingVO> voList = new ArrayList<AdminMerchandiseMarketingVO>();
		for (MerchandiseMarketing adminMerchandiseMarketing : list) {
			voList.add(toVO4Admin(adminMerchandiseMarketing));
		}
		return voList;
	}

	@Override
	public AdminMerchandiseMarketingVO toVO4Admin(MerchandiseMarketing merchandiseMarketing){
		String json = Json.toJson(merchandiseMarketing);
		return Json.toObject(json, AdminMerchandiseMarketingVO.class, true);
	}
}
