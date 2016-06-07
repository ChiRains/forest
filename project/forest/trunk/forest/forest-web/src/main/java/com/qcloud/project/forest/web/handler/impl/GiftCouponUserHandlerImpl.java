package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.web.handler.GiftCouponUserHandler;
import com.qcloud.project.forest.model.GiftCouponUser;
import com.qcloud.project.forest.web.vo.GiftCouponUserVO;
import com.qcloud.project.forest.web.vo.admin.AdminGiftCouponUserVO;

@Component
public class GiftCouponUserHandlerImpl implements GiftCouponUserHandler {

	@Override
	public List<GiftCouponUserVO> toVOList(List<GiftCouponUser> list){
		List<GiftCouponUserVO> voList = new ArrayList<GiftCouponUserVO>();
		for (GiftCouponUser giftCouponUser : list) {
			voList.add(toVO(giftCouponUser));
		}
		return voList;
	}

	@Override
	public GiftCouponUserVO toVO(GiftCouponUser giftCouponUser){
		String json = Json.toJson(giftCouponUser);
		return Json.toObject(json, GiftCouponUserVO.class, true);

	}

	@Override
	public List<AdminGiftCouponUserVO> toVOList4Admin(List<GiftCouponUser> list){
		List<AdminGiftCouponUserVO> voList = new ArrayList<AdminGiftCouponUserVO>();
		for (GiftCouponUser adminGiftCouponUser : list) {
			voList.add(toVO4Admin(adminGiftCouponUser));
		}
		return voList;
	}

	@Override
	public AdminGiftCouponUserVO toVO4Admin(GiftCouponUser giftCouponUser){
		String json = Json.toJson(giftCouponUser);
		return Json.toObject(json, AdminGiftCouponUserVO.class, true);
	}
}
