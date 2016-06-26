package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.goods.web.handler.AttributeDefinitionHandler;
import com.qcloud.component.goods.model.AttributeDefinition;
import com.qcloud.component.goods.web.vo.AttributeDefinitionVO;
import com.qcloud.component.goods.web.vo.admin.AdminAttributeDefinitionVO;

@Component
public class AttributeDefinitionHandlerImpl implements AttributeDefinitionHandler {

	@Override
	public List<AttributeDefinitionVO> toVOList(List<AttributeDefinition> list){
		List<AttributeDefinitionVO> voList = new ArrayList<AttributeDefinitionVO>();
		for (AttributeDefinition attributeDefinition : list) {
			voList.add(toVO(attributeDefinition));
		}
		return voList;
	}

	@Override
	public AttributeDefinitionVO toVO(AttributeDefinition attributeDefinition){
		String json = Json.toJson(attributeDefinition);
		return Json.toObject(json, AttributeDefinitionVO.class, true);

	}

	@Override
	public List<AdminAttributeDefinitionVO> toVOList4Admin(List<AttributeDefinition> list){
		List<AdminAttributeDefinitionVO> voList = new ArrayList<AdminAttributeDefinitionVO>();
		for (AttributeDefinition adminAttributeDefinition : list) {
			voList.add(toVO4Admin(adminAttributeDefinition));
		}
		return voList;
	}

	@Override
	public AdminAttributeDefinitionVO toVO4Admin(AttributeDefinition attributeDefinition){
		String json = Json.toJson(attributeDefinition);
		return Json.toObject(json, AdminAttributeDefinitionVO.class, true);
	}
}
