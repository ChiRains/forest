package com.qcloud.component.pay.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.pay.web.handler.PayRecordHandler;
import com.qcloud.component.pay.model.PayRecord;
import com.qcloud.component.pay.web.vo.PayRecordVO;
import com.qcloud.component.pay.web.vo.admin.AdminPayRecordVO;

@Component
public class PayRecordHandlerImpl implements PayRecordHandler {

	@Override
	public List<PayRecordVO> toVOList(List<PayRecord> list){
		List<PayRecordVO> voList = new ArrayList<PayRecordVO>();
		for (PayRecord payRecord : list) {
			voList.add(toVO(payRecord));
		}
		return voList;
	}

	@Override
	public PayRecordVO toVO(PayRecord payRecord){
		String json = Json.toJson(payRecord);
		return Json.toObject(json, PayRecordVO.class, true);

	}

	@Override
	public List<AdminPayRecordVO> toVOList4Admin(List<PayRecord> list){
		List<AdminPayRecordVO> voList = new ArrayList<AdminPayRecordVO>();
		for (PayRecord adminPayRecord : list) {
			voList.add(toVO4Admin(adminPayRecord));
		}
		return voList;
	}

	@Override
	public AdminPayRecordVO toVO4Admin(PayRecord payRecord){
		String json = Json.toJson(payRecord);
		return Json.toObject(json, AdminPayRecordVO.class, true);
	}
}
