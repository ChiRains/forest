package com.qcloud.component.warehouse.web.handler;

import java.util.List;

import com.qcloud.component.warehouse.model.MerchandiseStock;
import com.qcloud.component.warehouse.web.vo.MerchandiseStockVO;
import com.qcloud.component.warehouse.web.vo.admin.AdminMerchandiseStockVO;

public interface MerchandiseStockHandler {

	List<MerchandiseStockVO> toVOList(List<MerchandiseStock> list);

	MerchandiseStockVO toVO(MerchandiseStock merchandiseStock);

	List<AdminMerchandiseStockVO> toVOList4Admin(List<MerchandiseStock> list);

	AdminMerchandiseStockVO toVO4Admin(MerchandiseStock merchandiseStock);
}
