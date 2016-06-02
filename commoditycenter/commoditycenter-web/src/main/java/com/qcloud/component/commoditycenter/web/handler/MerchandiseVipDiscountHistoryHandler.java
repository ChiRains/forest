package com.qcloud.component.commoditycenter.web.handler;

import java.util.List;

import com.qcloud.component.commoditycenter.model.MerchandiseVipDiscountHistory;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseVipDiscountHistoryVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminMerchandiseVipDiscountHistoryVO;

public interface MerchandiseVipDiscountHistoryHandler {

	List<MerchandiseVipDiscountHistoryVO> toVOList(List<MerchandiseVipDiscountHistory> list);

	MerchandiseVipDiscountHistoryVO toVO(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory);

	List<AdminMerchandiseVipDiscountHistoryVO> toVOList4Admin(List<MerchandiseVipDiscountHistory> list);

	AdminMerchandiseVipDiscountHistoryVO toVO4Admin(MerchandiseVipDiscountHistory merchandiseVipDiscountHistory);
}
