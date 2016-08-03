package com.qcloud.component.orderform.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.orderform.web.handler.ReturnOrderItemDetailHandler;
import com.qcloud.component.orderform.model.ReturnOrderItemDetail;
import com.qcloud.component.orderform.web.vo.ReturnOrderItemDetailVO;
import com.qcloud.component.orderform.web.vo.admin.AdminReturnOrderItemDetailVO;

@Component
public class ReturnOrderItemDetailHandlerImpl implements ReturnOrderItemDetailHandler {

	@Override
	public List<ReturnOrderItemDetailVO> toVOList(List<ReturnOrderItemDetail> list){
		List<ReturnOrderItemDetailVO> voList = new ArrayList<ReturnOrderItemDetailVO>();
		for (ReturnOrderItemDetail returnOrderItemDetail : list) {
			voList.add(toVO(returnOrderItemDetail));
		}
		return voList;
	}

	@Override
	public ReturnOrderItemDetailVO toVO(ReturnOrderItemDetail returnOrderItemDetail){
		String json = Json.toJson(returnOrderItemDetail);
		return Json.toObject(json, ReturnOrderItemDetailVO.class, true);

	}

	@Override
	public List<AdminReturnOrderItemDetailVO> toVOList4Admin(List<ReturnOrderItemDetail> list){
		List<AdminReturnOrderItemDetailVO> voList = new ArrayList<AdminReturnOrderItemDetailVO>();
		for (ReturnOrderItemDetail adminReturnOrderItemDetail : list) {
			voList.add(toVO4Admin(adminReturnOrderItemDetail));
		}
		return voList;
	}

	@Override
	public AdminReturnOrderItemDetailVO toVO4Admin(ReturnOrderItemDetail returnOrderItemDetail){
		String json = Json.toJson(returnOrderItemDetail);
		return Json.toObject(json, AdminReturnOrderItemDetailVO.class, true);
	}
}
