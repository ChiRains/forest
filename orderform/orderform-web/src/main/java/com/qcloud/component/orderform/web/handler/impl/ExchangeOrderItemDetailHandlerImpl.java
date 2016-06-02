package com.qcloud.component.orderform.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.model.ExchangeOrderItemDetail;
import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.service.OrderItemDetailService;
import com.qcloud.component.orderform.web.handler.ExchangeOrderItemDetailHandler;
import com.qcloud.component.orderform.web.vo.ExchangeOrderItemDetailVO;
import com.qcloud.component.orderform.web.vo.admin.AdminExchangeOrderItemDetailVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class ExchangeOrderItemDetailHandlerImpl implements ExchangeOrderItemDetailHandler {

    @Autowired
    private OrderItemDetailService orderItemDetailService;

    @Override
    public List<ExchangeOrderItemDetailVO> toVOList(List<ExchangeOrderItemDetail> list) {

        List<ExchangeOrderItemDetailVO> voList = new ArrayList<ExchangeOrderItemDetailVO>();
        for (ExchangeOrderItemDetail exchangeOrderItemDetail : list) {
            voList.add(toVO(exchangeOrderItemDetail));
        }
        return voList;
    }

    @Override
    public ExchangeOrderItemDetailVO toVO(ExchangeOrderItemDetail exchangeOrderItemDetail) {

        String json = Json.toJson(exchangeOrderItemDetail);
        return Json.toObject(json, ExchangeOrderItemDetailVO.class, true);
    }

    @Override
	public List<AdminExchangeOrderItemDetailVO> toVOList4Admin(List<ExchangeOrderItemDetail> list){
		List<AdminExchangeOrderItemDetailVO> voList = new ArrayList<AdminExchangeOrderItemDetailVO>();
		for (ExchangeOrderItemDetail adminExchangeOrderItemDetail : list) {
		    AdminExchangeOrderItemDetailVO vo=toVO4Admin(adminExchangeOrderItemDetail);
		    OrderItemDetail details= orderItemDetailService.get(vo.getOrderItemDetailId(), vo.getTime());
		    vo.setName(details.getName());
		    vo.setImage(details.getImage());
		    vo.setSpecifications(details.getSpecifications());
		    voList.add(vo);
		}
		return voList;
	}

    @Override
    public AdminExchangeOrderItemDetailVO toVO4Admin(ExchangeOrderItemDetail exchangeOrderItemDetail) {

        String json = Json.toJson(exchangeOrderItemDetail);
        return Json.toObject(json, AdminExchangeOrderItemDetailVO.class, true);
    }
}
