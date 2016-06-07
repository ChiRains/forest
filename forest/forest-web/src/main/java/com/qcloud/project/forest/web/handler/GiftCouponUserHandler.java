package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.GiftCouponUser;
import com.qcloud.project.forest.web.vo.GiftCouponUserVO;
import com.qcloud.project.forest.web.vo.admin.AdminGiftCouponUserVO;

public interface GiftCouponUserHandler {

	List<GiftCouponUserVO> toVOList(List<GiftCouponUser> list);

	GiftCouponUserVO toVO(GiftCouponUser giftCouponUser);

	List<AdminGiftCouponUserVO> toVOList4Admin(List<GiftCouponUser> list);

	AdminGiftCouponUserVO toVO4Admin(GiftCouponUser giftCouponUser);
}
