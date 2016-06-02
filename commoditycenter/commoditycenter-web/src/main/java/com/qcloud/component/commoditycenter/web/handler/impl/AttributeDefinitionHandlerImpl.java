package com.qcloud.component.commoditycenter.web.handler.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.commoditycenter.model.AttributeDefinition;
import com.qcloud.component.commoditycenter.model.key.TypeEnum;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.AttrValueType;
import com.qcloud.component.commoditycenter.web.handler.AttributeDefinitionHandler;
import com.qcloud.component.commoditycenter.web.vo.AttributeDefinitionVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminAttributeDefinitionVO;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.pirates.core.json.Json;
@Component
public class AttributeDefinitionHandlerImpl implements AttributeDefinitionHandler {
    @Override
    public List<AttributeDefinitionVO> toVOList(List<AttributeDefinition> list) {
        List<AttributeDefinitionVO> voList = new ArrayList<AttributeDefinitionVO>();
        for (AttributeDefinition attributeDefinition : list) {
            voList.add(toVO(attributeDefinition));
        }
        return voList;
    }

    @Override
    public AttributeDefinitionVO toVO(AttributeDefinition attributeDefinition) {
        String json = Json.toJson(attributeDefinition);
        return Json.toObject(json, AttributeDefinitionVO.class, true);
    }

    @Override
    public List<AdminAttributeDefinitionVO> toVOList4Admin(List<AttributeDefinition> list) {
        List<AdminAttributeDefinitionVO> voList = new ArrayList<AdminAttributeDefinitionVO>();
        for (AttributeDefinition adminAttributeDefinition : list) {
            AdminAttributeDefinitionVO vo=toVO4Admin(adminAttributeDefinition);
            AttrValueType type[]= TypeEnum.AttrValueType.values();
            for (int i = 0; i < type.length; i++) {
                if(vo.getValueType().equals(String.valueOf(type[i].getKey()))){
                    vo.setValueType(type[i].getName());
                }
            }
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public AdminAttributeDefinitionVO toVO4Admin(AttributeDefinition attributeDefinition) {
        String json = Json.toJson(attributeDefinition);
        AdminAttributeDefinitionVO vo=Json.toObject(json, AdminAttributeDefinitionVO.class, true);
        return vo;
    }

    @Override
    public List<KeyValueVO> toVOList4Select(List<AttributeDefinition> list, long key) {
        List<KeyValueVO> voList = new ArrayList<KeyValueVO>();
        for (AttributeDefinition ad : list) {
            KeyValueVO kv = new KeyValueVO();
            kv.setKey(String.valueOf(ad.getId()));
            kv.setValue(ad.getName());
            kv.setMessage(key == ad.getId() ? "selected" : "");
            voList.add(kv);
        }
        return voList;
    }
}
