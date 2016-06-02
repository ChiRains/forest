package com.qcloud.component.orderform.web.handler;

import java.util.List;

import com.qcloud.component.orderform.model.ExchangeOrder;
import com.qcloud.component.orderform.web.vo.ExchangeOrderVO;
import com.qcloud.component.orderform.web.vo.admin.AdminExchangeOrderVO;

public interface ExchangeOrderHandler {

	List<ExchangeOrderVO> toVOList(List<ExchangeOrder> list);

	ExchangeOrderVO toVO(ExchangeOrder exchangeOrder);

	List<AdminExchangeOrderVO> toVOList4Admin(List<ExchangeOrder> list);

	AdminExchangeOrderVO toVO4Admin(ExchangeOrder exchangeOrder);
}
