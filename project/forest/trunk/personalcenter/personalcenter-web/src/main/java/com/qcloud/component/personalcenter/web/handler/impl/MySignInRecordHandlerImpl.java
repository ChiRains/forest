package com.qcloud.component.personalcenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qcloud.component.personalcenter.model.MySignInRecord;
import com.qcloud.component.personalcenter.web.handler.MySignInRecordHandler;
import com.qcloud.component.personalcenter.web.vo.MySignInRecordVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class MySignInRecordHandlerImpl implements MySignInRecordHandler {

	@Override
	public List<MySignInRecordVO> toVOList(List<MySignInRecord> list) {
		List<MySignInRecordVO> voList = new ArrayList<MySignInRecordVO>();
		for (MySignInRecord mySignInRecord : list) {
			voList.add(toVO(mySignInRecord));
		}
		return voList;
	}

	@Override
	public MySignInRecordVO toVO(MySignInRecord mySignInRecord) {
		String json = Json.toJson(mySignInRecord);
		return Json.toObject(json, MySignInRecordVO.class, true);

	}

}
