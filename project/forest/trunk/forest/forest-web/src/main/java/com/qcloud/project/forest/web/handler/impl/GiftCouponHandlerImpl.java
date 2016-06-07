package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.web.handler.GiftCouponHandler;
import com.qcloud.project.forest.model.GiftCoupon;
import com.qcloud.project.forest.web.vo.GiftCouponVO;
import com.qcloud.project.forest.web.vo.admin.AdminGiftCouponVO;

@Component
public class GiftCouponHandlerImpl implements GiftCouponHandler {

	@Override
	public List<GiftCouponVO> toVOList(List<GiftCoupon> list){
		List<GiftCouponVO> voList = new ArrayList<GiftCouponVO>();
		for (GiftCoupon giftCoupon : list) {
			voList.add(toVO(giftCoupon));
		}
		return voList;
	}

	@Override
	public GiftCouponVO toVO(GiftCoupon giftCoupon){
		String json = Json.toJson(giftCoupon);
		return Json.toObject(json, GiftCouponVO.class, true);

	}

	@Override
	public List<AdminGiftCouponVO> toVOList4Admin(List<GiftCoupon> list){
		List<AdminGiftCouponVO> voList = new ArrayList<AdminGiftCouponVO>();
		for (GiftCoupon adminGiftCoupon : list) {
			voList.add(toVO4Admin(adminGiftCoupon));
		}
		return voList;
	}

	@Override
	public AdminGiftCouponVO toVO4Admin(GiftCoupon giftCoupon){
		String json = Json.toJson(giftCoupon);
		return Json.toObject(json, AdminGiftCouponVO.class, true);
	}
}
