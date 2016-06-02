package com.qcloud.component.brokerage.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.brokerage.web.handler.UpgradeGiftHandler;
import com.qcloud.component.brokerage.model.UpgradeGift;
import com.qcloud.component.brokerage.web.vo.UpgradeGiftVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminUpgradeGiftVO;

@Component
public class UpgradeGiftHandlerImpl implements UpgradeGiftHandler {

	@Override
	public List<UpgradeGiftVO> toVOList(List<UpgradeGift> list){
		List<UpgradeGiftVO> voList = new ArrayList<UpgradeGiftVO>();
		for (UpgradeGift upgradeGift : list) {
			voList.add(toVO(upgradeGift));
		}
		return voList;
	}

	@Override
	public UpgradeGiftVO toVO(UpgradeGift upgradeGift){
		String json = Json.toJson(upgradeGift);
		return Json.toObject(json, UpgradeGiftVO.class, true);

	}

	@Override
	public List<AdminUpgradeGiftVO> toVOList4Admin(List<UpgradeGift> list){
		List<AdminUpgradeGiftVO> voList = new ArrayList<AdminUpgradeGiftVO>();
		for (UpgradeGift adminUpgradeGift : list) {
			voList.add(toVO4Admin(adminUpgradeGift));
		}
		return voList;
	}

	@Override
	public AdminUpgradeGiftVO toVO4Admin(UpgradeGift upgradeGift){
		String json = Json.toJson(upgradeGift);
		return Json.toObject(json, AdminUpgradeGiftVO.class, true);
	}
}
