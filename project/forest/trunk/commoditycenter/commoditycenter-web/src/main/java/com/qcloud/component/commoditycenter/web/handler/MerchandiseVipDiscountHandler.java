package com.qcloud.component.commoditycenter.web.handler;

import java.util.List;

import com.qcloud.component.commoditycenter.model.MerchandiseVipDiscount;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseVipDiscountVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminMerchandiseVipDiscountVO;

public interface MerchandiseVipDiscountHandler {

	List<MerchandiseVipDiscountVO> toVOList(List<MerchandiseVipDiscount> list);

	MerchandiseVipDiscountVO toVO(MerchandiseVipDiscount merchandiseVipDiscount);

	List<AdminMerchandiseVipDiscountVO> toVOList4Admin(List<MerchandiseVipDiscount> list);

	AdminMerchandiseVipDiscountVO toVO4Admin(MerchandiseVipDiscount merchandiseVipDiscount);
}
