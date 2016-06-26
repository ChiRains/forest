package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.ClassifyAttributeHandler;
import com.qcloud.component.goods.model.ClassifyAttribute;
import com.qcloud.component.goods.web.vo.ClassifyAttributeVO;
import com.qcloud.component.goods.web.vo.admin.AdminClassifyAttributeVO;

@Component
public class ClassifyAttributeHandlerImpl implements ClassifyAttributeHandler {

	@Override
	public List<ClassifyAttributeVO> toVOList(List<ClassifyAttribute> list){
		List<ClassifyAttributeVO> voList = new ArrayList<ClassifyAttributeVO>();
		for (ClassifyAttribute classifyAttribute : list) {
			voList.add(toVO(classifyAttribute));
		}
		return voList;
	}

	@Override
	public ClassifyAttributeVO toVO(ClassifyAttribute classifyAttribute){
		String json = Json.toJson(classifyAttribute);
		return Json.toObject(json, ClassifyAttributeVO.class, true);

	}

	@Override
	public List<AdminClassifyAttributeVO> toVOList4Admin(List<ClassifyAttribute> list){
		List<AdminClassifyAttributeVO> voList = new ArrayList<AdminClassifyAttributeVO>();
		for (ClassifyAttribute adminClassifyAttribute : list) {
			voList.add(toVO4Admin(adminClassifyAttribute));
		}
		return voList;
	}

	@Override
	public AdminClassifyAttributeVO toVO4Admin(ClassifyAttribute classifyAttribute){
		String json = Json.toJson(classifyAttribute);
		return Json.toObject(json, AdminClassifyAttributeVO.class, true);
	}
}
