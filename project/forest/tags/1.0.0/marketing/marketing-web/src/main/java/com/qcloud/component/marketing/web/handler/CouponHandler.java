package com.qcloud.component.marketing.web.handler;

import java.util.List;

import com.qcloud.component.marketing.model.Coupon;
import com.qcloud.component.marketing.web.vo.CouponVO;
import com.qcloud.component.marketing.web.vo.admin.AdminCouponVO;

public interface CouponHandler {

	List<CouponVO> toVOList(List<Coupon> list);

	CouponVO toVO(Coupon coupon);

	List<AdminCouponVO> toVOList4Admin(List<Coupon> list);

	AdminCouponVO toVO4Admin(Coupon coupon);
}
