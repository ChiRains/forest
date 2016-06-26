package com.qcloud.component.goods.web.handler;

import java.util.List;

import com.qcloud.component.goods.model.AttributeDefinition;
import com.qcloud.component.goods.web.vo.AttributeDefinitionVO;
import com.qcloud.component.goods.web.vo.admin.AdminAttributeDefinitionVO;

public interface AttributeDefinitionHandler {

	List<AttributeDefinitionVO> toVOList(List<AttributeDefinition> list);

	AttributeDefinitionVO toVO(AttributeDefinition attributeDefinition);

	List<AdminAttributeDefinitionVO> toVOList4Admin(List<AttributeDefinition> list);

	AdminAttributeDefinitionVO toVO4Admin(AttributeDefinition attributeDefinition);
}
