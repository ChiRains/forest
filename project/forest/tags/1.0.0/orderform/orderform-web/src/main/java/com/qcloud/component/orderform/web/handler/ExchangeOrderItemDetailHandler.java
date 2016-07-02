package com.qcloud.component.orderform.web.handler;

import java.util.List;

import com.qcloud.component.orderform.model.ExchangeOrderItemDetail;
import com.qcloud.component.orderform.web.vo.ExchangeOrderItemDetailVO;
import com.qcloud.component.orderform.web.vo.admin.AdminExchangeOrderItemDetailVO;

public interface ExchangeOrderItemDetailHandler {

	List<ExchangeOrderItemDetailVO> toVOList(List<ExchangeOrderItemDetail> list);

	ExchangeOrderItemDetailVO toVO(ExchangeOrderItemDetail exchangeOrderItemDetail);

	List<AdminExchangeOrderItemDetailVO> toVOList4Admin(List<ExchangeOrderItemDetail> list);

	AdminExchangeOrderItemDetailVO toVO4Admin(ExchangeOrderItemDetail exchangeOrderItemDetail);
}
