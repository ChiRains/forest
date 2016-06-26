package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.ClassifySpecificationsHandler;
import com.qcloud.component.goods.model.ClassifySpecifications;
import com.qcloud.component.goods.web.vo.ClassifySpecificationsVO;
import com.qcloud.component.goods.web.vo.admin.AdminClassifySpecificationsVO;

@Component
public class ClassifySpecificationsHandlerImpl implements ClassifySpecificationsHandler {

	@Override
	public List<ClassifySpecificationsVO> toVOList(List<ClassifySpecifications> list){
		List<ClassifySpecificationsVO> voList = new ArrayList<ClassifySpecificationsVO>();
		for (ClassifySpecifications classifySpecifications : list) {
			voList.add(toVO(classifySpecifications));
		}
		return voList;
	}

	@Override
	public ClassifySpecificationsVO toVO(ClassifySpecifications classifySpecifications){
		String json = Json.toJson(classifySpecifications);
		return Json.toObject(json, ClassifySpecificationsVO.class, true);

	}

	@Override
	public List<AdminClassifySpecificationsVO> toVOList4Admin(List<ClassifySpecifications> list){
		List<AdminClassifySpecificationsVO> voList = new ArrayList<AdminClassifySpecificationsVO>();
		for (ClassifySpecifications adminClassifySpecifications : list) {
			voList.add(toVO4Admin(adminClassifySpecifications));
		}
		return voList;
	}

	@Override
	public AdminClassifySpecificationsVO toVO4Admin(ClassifySpecifications classifySpecifications){
		String json = Json.toJson(classifySpecifications);
		return Json.toObject(json, AdminClassifySpecificationsVO.class, true);
	}
}
