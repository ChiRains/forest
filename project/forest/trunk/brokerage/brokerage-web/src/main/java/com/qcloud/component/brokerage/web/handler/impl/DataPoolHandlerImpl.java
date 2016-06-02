package com.qcloud.component.brokerage.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.brokerage.web.handler.DataPoolHandler;
import com.qcloud.component.brokerage.model.DataPool;
import com.qcloud.component.brokerage.web.vo.DataPoolVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminDataPoolVO;

@Component
public class DataPoolHandlerImpl implements DataPoolHandler {

	@Override
	public List<DataPoolVO> toVOList(List<DataPool> list){
		List<DataPoolVO> voList = new ArrayList<DataPoolVO>();
		for (DataPool dataPool : list) {
			voList.add(toVO(dataPool));
		}
		return voList;
	}

	@Override
	public DataPoolVO toVO(DataPool dataPool){
		String json = Json.toJson(dataPool);
		return Json.toObject(json, DataPoolVO.class, true);

	}

	@Override
	public List<AdminDataPoolVO> toVOList4Admin(List<DataPool> list){
		List<AdminDataPoolVO> voList = new ArrayList<AdminDataPoolVO>();
		for (DataPool adminDataPool : list) {
			voList.add(toVO4Admin(adminDataPool));
		}
		return voList;
	}

	@Override
	public AdminDataPoolVO toVO4Admin(DataPool dataPool){
		String json = Json.toJson(dataPool);
		return Json.toObject(json, AdminDataPoolVO.class, true);
	}
}
