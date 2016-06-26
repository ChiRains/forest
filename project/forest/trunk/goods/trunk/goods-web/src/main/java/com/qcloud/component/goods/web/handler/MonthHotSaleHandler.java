package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.MonthHotSale;
import com.qcloud.component.goods.web.vo.MonthHotSaleVO;
import com.qcloud.component.goods.web.vo.admin.AdminMonthHotSaleVO;

public interface MonthHotSaleHandler {

	List<MonthHotSaleVO> toVOList(List<MonthHotSale> list);

	MonthHotSaleVO toVO(MonthHotSale monthHotSale);

	List<AdminMonthHotSaleVO> toVOList4Admin(List<MonthHotSale> list);

	AdminMonthHotSaleVO toVO4Admin(MonthHotSale monthHotSale);
}
