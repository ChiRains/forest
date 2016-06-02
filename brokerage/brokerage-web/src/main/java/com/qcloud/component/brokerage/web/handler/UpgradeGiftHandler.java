package com.qcloud.component.brokerage.web.handler;

import java.util.List;

import com.qcloud.component.brokerage.model.UpgradeGift;
import com.qcloud.component.brokerage.web.vo.UpgradeGiftVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminUpgradeGiftVO;

public interface UpgradeGiftHandler {

	List<UpgradeGiftVO> toVOList(List<UpgradeGift> list);

	UpgradeGiftVO toVO(UpgradeGift upgradeGift);

	List<AdminUpgradeGiftVO> toVOList4Admin(List<UpgradeGift> list);

	AdminUpgradeGiftVO toVO4Admin(UpgradeGift upgradeGift);
}
