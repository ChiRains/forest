package com.qcloud.component.marketing.web.handler;

import java.util.List;

import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.web.vo.CouponItemsVO;
import com.qcloud.component.marketing.web.vo.admin.AdminCouponItemsVO;

public interface CouponItemsHandler {

	List<CouponItemsVO> toVOList(List<CouponItems> list);

	CouponItemsVO toVO(CouponItems couponItems);

	List<AdminCouponItemsVO> toVOList4Admin(List<CouponItems> list);

	AdminCouponItemsVO toVO4Admin(CouponItems couponItems);
}
