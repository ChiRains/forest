package com.qcloud.component.orderform.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.web.handler.SubOrderHandler;
import com.qcloud.component.orderform.web.vo.admin.AdminSubOrderVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class SubOrderHandlerImpl implements SubOrderHandler {

//	@Override
//	public List<SubOrderVO> toVOList(List<SubOrder> list){
//		List<SubOrderVO> voList = new ArrayList<SubOrderVO>();
//		for (SubOrder subOrder : list) {
//			voList.add(toVO(subOrder));
//		}
//		return voList;
//	}
//
//	@Override
//	public SubOrderVO toVO(SubOrder subOrder){
//		String json = Json.toJson(subOrder);
//		return Json.toObject(json, SubOrderVO.class, true);
//
//	}

	@Override
	public List<AdminSubOrderVO> toVOList4Admin(List<SubOrder> list){
		List<AdminSubOrderVO> voList = new ArrayList<AdminSubOrderVO>();
		for (SubOrder adminSubOrder : list) {
			voList.add(toVO4Admin(adminSubOrder));
		}
		return voList;
	}

	@Override
	public AdminSubOrderVO toVO4Admin(SubOrder subOrder){
		String json = Json.toJson(subOrder);
		return Json.toObject(json, AdminSubOrderVO.class, true);
	}
}
