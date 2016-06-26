package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.MerchandiseVipDiscountDate;
import com.qcloud.component.goods.web.vo.MerchandiseVipDiscountDateVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseVipDiscountDateVO;

public interface MerchandiseVipDiscountDateHandler {

	List<MerchandiseVipDiscountDateVO> toVOList(List<MerchandiseVipDiscountDate> list);

	MerchandiseVipDiscountDateVO toVO(MerchandiseVipDiscountDate merchandiseVipDiscountDate);

	List<AdminMerchandiseVipDiscountDateVO> toVOList4Admin(List<MerchandiseVipDiscountDate> list);

	AdminMerchandiseVipDiscountDateVO toVO4Admin(MerchandiseVipDiscountDate merchandiseVipDiscountDate);
}
