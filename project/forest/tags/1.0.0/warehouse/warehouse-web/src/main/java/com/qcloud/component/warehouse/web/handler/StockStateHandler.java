package com.qcloud.component.warehouse.web.handler;

import java.util.List;

import com.qcloud.component.warehouse.model.StockState;
import com.qcloud.component.warehouse.web.vo.StockStateVO;
import com.qcloud.component.warehouse.web.vo.admin.AdminStockStateVO;

public interface StockStateHandler {

	List<StockStateVO> toVOList(List<StockState> list);

	StockStateVO toVO(StockState stockState);

	List<AdminStockStateVO> toVOList4Admin(List<StockState> list);

	AdminStockStateVO toVO4Admin(StockState stockState);
}
