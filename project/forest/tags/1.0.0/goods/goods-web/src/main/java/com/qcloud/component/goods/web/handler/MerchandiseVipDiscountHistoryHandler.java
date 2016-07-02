package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.MerchandiseVipDiscountHistory;
import com.qcloud.component.goods.web.vo.MerchandiseVipDiscountHistoryVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseVipDiscountHistoryVO;

public interface MerchandiseVipDiscountHistoryHandler {

	List<MerchandiseVipDiscountHistoryVO> toVOList(List<MerchandiseVipDiscountHistory> list);

	MerchandiseVipDiscountHistoryVO toVO(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory);

	List<AdminMerchandiseVipDiscountHistoryVO> toVOList4Admin(List<MerchandiseVipDiscountHistory> list);

	AdminMerchandiseVipDiscountHistoryVO toVO4Admin(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory);
}
