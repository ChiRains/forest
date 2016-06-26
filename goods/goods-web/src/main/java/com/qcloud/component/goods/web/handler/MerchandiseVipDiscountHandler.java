package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.MerchandiseVipDiscount;
import com.qcloud.component.goods.web.vo.MerchandiseVipDiscountVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseVipDiscountVO;

public interface MerchandiseVipDiscountHandler {

	List<MerchandiseVipDiscountVO> toVOList(List<MerchandiseVipDiscount> list);

	MerchandiseVipDiscountVO toVO(MerchandiseVipDiscount merchandiseVipDiscount);

	List<AdminMerchandiseVipDiscountVO> toVOList4Admin(List<MerchandiseVipDiscount> list);

	AdminMerchandiseVipDiscountVO toVO4Admin(MerchandiseVipDiscount merchandiseVipDiscount);
}
