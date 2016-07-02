package com.qcloud.component.commoditycenter.web.handler;

import java.util.List;

import com.qcloud.component.commoditycenter.model.AttributeDefinition;
import com.qcloud.component.commoditycenter.web.vo.AttributeDefinitionVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminAttributeDefinitionVO;
import com.qcloud.component.publicdata.KeyValueVO;

public interface AttributeDefinitionHandler {

	List<AttributeDefinitionVO> toVOList(List<AttributeDefinition> list);

	AttributeDefinitionVO toVO(AttributeDefinition attributeDefinition);

	List<AdminAttributeDefinitionVO> toVOList4Admin(List<AttributeDefinition> list);

	AdminAttributeDefinitionVO toVO4Admin(AttributeDefinition attributeDefinition);
	
	List<KeyValueVO> toVOList4Select(List<AttributeDefinition> list, long key);
}
