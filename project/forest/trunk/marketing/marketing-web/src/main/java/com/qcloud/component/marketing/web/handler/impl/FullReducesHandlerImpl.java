package com.qcloud.component.marketing.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.marketing.web.handler.FullReducesHandler;
import com.qcloud.component.marketing.model.FullReduces;
import com.qcloud.component.marketing.web.vo.FullReducesVO;
import com.qcloud.component.marketing.web.vo.admin.AdminFullReducesVO;

@Component
public class FullReducesHandlerImpl implements FullReducesHandler {

	@Override
	public List<FullReducesVO> toVOList(List<FullReduces> list){
		List<FullReducesVO> voList = new ArrayList<FullReducesVO>();
		for (FullReduces fullReduces : list) {
			voList.add(toVO(fullReduces));
		}
		return voList;
	}

	@Override
	public FullReducesVO toVO(FullReduces fullReduces){
		String json = Json.toJson(fullReduces);
		return Json.toObject(json, FullReducesVO.class, true);

	}

	@Override
	public List<AdminFullReducesVO> toVOList4Admin(List<FullReduces> list){
		List<AdminFullReducesVO> voList = new ArrayList<AdminFullReducesVO>();
		for (FullReduces adminFullReduces : list) {
			voList.add(toVO4Admin(adminFullReduces));
		}
		return voList;
	}

	@Override
	public AdminFullReducesVO toVO4Admin(FullReduces fullReduces){
		String json = Json.toJson(fullReduces);
		return Json.toObject(json, AdminFullReducesVO.class, true);
	}
}
