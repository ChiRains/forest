package com.qcloud.component.warehouse.web.handler;

import java.util.List;

import com.qcloud.component.warehouse.model.MerchandiseStockState;
import com.qcloud.component.warehouse.web.vo.MerchandiseStockStateVO;
import com.qcloud.component.warehouse.web.vo.admin.AdminMerchandiseStockStateVO;

public interface MerchandiseStockStateHandler {

	List<MerchandiseStockStateVO> toVOList(List<MerchandiseStockState> list);

	MerchandiseStockStateVO toVO(MerchandiseStockState merchandiseStockState);

	List<AdminMerchandiseStockStateVO> toVOList4Admin(List<MerchandiseStockState> list);

	AdminMerchandiseStockStateVO toVO4Admin(MerchandiseStockState merchandiseStockState);
}
