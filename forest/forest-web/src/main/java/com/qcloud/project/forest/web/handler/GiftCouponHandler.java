package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.GiftCoupon;
import com.qcloud.project.forest.web.vo.GiftCouponVO;
import com.qcloud.project.forest.web.vo.admin.AdminGiftCouponVO;

public interface GiftCouponHandler {

	List<GiftCouponVO> toVOList(List<GiftCoupon> list);

	GiftCouponVO toVO(GiftCoupon giftCoupon);

	List<AdminGiftCouponVO> toVOList4Admin(List<GiftCoupon> list);

	AdminGiftCouponVO toVO4Admin(GiftCoupon giftCoupon);
}
