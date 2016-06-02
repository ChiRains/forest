package com.qcloud.component.orderform.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.model.RefundOrder;
import com.qcloud.component.orderform.web.handler.RefundOrderHandler;
import com.qcloud.component.orderform.web.vo.RefundOrderVO;
import com.qcloud.component.orderform.web.vo.admin.AdminRefundOrderVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.core.json.Json;

@Component
public class RefundOrderHandlerImpl implements RefundOrderHandler {
    @Autowired
    private PersonalcenterClient personalcenterClient;
	@Override
	public List<RefundOrderVO> toVOList(List<RefundOrder> list){
		List<RefundOrderVO> voList = new ArrayList<RefundOrderVO>();
		for (RefundOrder refundOrder : list) {
			voList.add(toVO(refundOrder));
		}
		return voList;
	}

	@Override
	public RefundOrderVO toVO(RefundOrder refundOrder){
		String json = Json.toJson(refundOrder);
		return Json.toObject(json, RefundOrderVO.class, true);

	}

	@Override
	public List<AdminRefundOrderVO> toVOList4Admin(List<RefundOrder> list){
		List<AdminRefundOrderVO> voList = new ArrayList<AdminRefundOrderVO>();
		for (RefundOrder adminRefundOrder : list) {
			voList.add(toVO4Admin(adminRefundOrder));
		}
		return voList;
	}

	@Override
	public AdminRefundOrderVO toVO4Admin(RefundOrder refundOrder){
		String json = Json.toJson(refundOrder);
		AdminRefundOrderVO vo=Json.toObject(json, AdminRefundOrderVO.class, true);
		QUser user=personalcenterClient.getUser(vo.getUserId());
		if(user!=null){
		    vo.setUserName(user.getMobile());
		}
		
		return vo;
	}
}
