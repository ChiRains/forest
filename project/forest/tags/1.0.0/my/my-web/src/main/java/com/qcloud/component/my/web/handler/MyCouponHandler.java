package com.qcloud.component.my.web.handler;

import java.util.List;
import com.qcloud.component.my.model.MyCoupon;
import com.qcloud.component.my.web.vo.MyCouponVO;
import com.qcloud.component.my.web.vo.admin.AdminMyCouponVO;

public interface MyCouponHandler {

	List<MyCouponVO> toVOList(List<MyCoupon> list);

	MyCouponVO toVO(MyCoupon myCoupon);

	List<AdminMyCouponVO> toVOList4Admin(List<MyCoupon> list);

	AdminMyCouponVO toVO4Admin(MyCoupon myCoupon);
}
