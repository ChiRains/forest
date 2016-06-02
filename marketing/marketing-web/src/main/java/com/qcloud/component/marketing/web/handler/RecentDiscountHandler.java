package com.qcloud.component.marketing.web.handler;

import java.util.List;

import com.qcloud.component.marketing.model.RecentDiscount;
import com.qcloud.component.marketing.web.vo.RecentDiscountVO;
import com.qcloud.component.marketing.web.vo.admin.AdminRecentDiscountVO;

public interface RecentDiscountHandler {

	List<RecentDiscountVO> toVOList(List<RecentDiscount> list);

	RecentDiscountVO toVO(RecentDiscount recentDiscount);

	List<AdminRecentDiscountVO> toVOList4Admin(List<RecentDiscount> list);

	AdminRecentDiscountVO toVO4Admin(RecentDiscount recentDiscount);
}
