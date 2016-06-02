package com.qcloud.component.commoditycenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.commoditycenter.model.AttributeDefinition;
import com.qcloud.component.commoditycenter.model.ClassifyAttribute;
import com.qcloud.component.commoditycenter.service.AttributeDefinitionService;
import com.qcloud.component.commoditycenter.web.handler.ClassifyAttributeHandler;
import com.qcloud.component.commoditycenter.web.vo.ClassifyAttributeVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminClassifyAttributeVO;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.publicdata.util.ClassifyUtils;
import com.qcloud.pirates.core.json.Json;

@Component
public class ClassifyAttributeHandlerImpl implements ClassifyAttributeHandler {

    @Autowired
    AttributeDefinitionService attributeDefinitionService;

    @Override
    public List<ClassifyAttributeVO> toVOList(List<ClassifyAttribute> list) {

        List<ClassifyAttributeVO> voList = new ArrayList<ClassifyAttributeVO>();
        for (ClassifyAttribute classifyAttribute : list) {
            voList.add(toVO(classifyAttribute));
        }
        return voList;
    }

    @Override
    public ClassifyAttributeVO toVO(ClassifyAttribute classifyAttribute) {

        String json = Json.toJson(classifyAttribute);
        return Json.toObject(json, ClassifyAttributeVO.class, true);
    }

    @Override
    public List<AdminClassifyAttributeVO> toVOList4Admin(List<ClassifyAttribute> list) {

        List<AdminClassifyAttributeVO> voList = new ArrayList<AdminClassifyAttributeVO>();
        for (ClassifyAttribute adminClassifyAttribute : list) {
            voList.add(toVO4Admin(adminClassifyAttribute));
        }
        return voList;
    }

    @Override
    public List<AdminClassifyAttributeVO> toVOList4Admin(List<Classify> cList, List<ClassifyAttribute> list, int isParent) {

        List<AdminClassifyAttributeVO> voList = new ArrayList<AdminClassifyAttributeVO>();
        for (ClassifyAttribute adminClassifyAttribute : list) {
            AdminClassifyAttributeVO vo = toVO4Admin(adminClassifyAttribute);
            vo.setIsParent(isParent);
            for (Classify classify : cList) {
                if (classify.getId() == adminClassifyAttribute.getClassifyId()) {
                    vo.setClassify(classify.getName());
                    break;
                }
            }
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public AdminClassifyAttributeVO toVO4Admin(ClassifyAttribute classifyAttribute) {

        String json = Json.toJson(classifyAttribute);
        AdminClassifyAttributeVO vo = Json.toObject(json, AdminClassifyAttributeVO.class, true);
        AttributeDefinition definition = attributeDefinitionService.get(vo.getAttributeId());
        vo.setAttributeStr(definition.getName());
        return vo;
    }

    @Override
    public List<AdminClassifyAttributeVO> toVOList4Admin(List<Classify> cList, List<ClassifyAttribute> list) {

        List<AdminClassifyAttributeVO> voList = new ArrayList<AdminClassifyAttributeVO>();
        for (Classify classify : cList) {
            voList.add(toVO4Admin(cList, classify, list));
        }
        return voList;
    }

    public AdminClassifyAttributeVO toVO4Admin(List<Classify> cList, Classify classify, List<ClassifyAttribute> list) {

        AdminClassifyAttributeVO vo = new AdminClassifyAttributeVO();
        vo.setClassifyId(classify.getId());
        vo.setClassify(classify.getName());
        vo.setParentId(classify.getParentId());
        String path = ClassifyUtils.calculationPath(cList, classify);
        vo.setPath(path);
        List<Long> keys = new ArrayList<Long>();
        for (ClassifyAttribute ca : list) {
            if (ca.getClassifyId() == classify.getId()) {
                keys.add(ca.getAttributeId());
            }
        }
        List<AttributeDefinition> adList = attributeDefinitionService.list(keys);
        StringBuffer sb = new StringBuffer();
        for (AttributeDefinition attributeDefinition : adList) {
            sb.append(attributeDefinition.getName());
            if (adList.indexOf(attributeDefinition) != adList.size() - 1) {
                sb.append(";");
            }
        }
        vo.setAttributeStr(sb.toString());
        return vo;
    }

    @Override
    public List<AdminClassifyAttributeVO> toVOList4SetAttribute(List<AttributeDefinition> list, List<ClassifyAttribute> caList) {

        List<AdminClassifyAttributeVO> voList = new ArrayList<AdminClassifyAttributeVO>();
        for (AttributeDefinition attributeDefinition : list) {
            AdminClassifyAttributeVO vo = new AdminClassifyAttributeVO();
            vo.setAttributeId(attributeDefinition.getId());
            vo.setAttributeStr(attributeDefinition.getName());
            vo.setChecked("");
            for (ClassifyAttribute classifyAttribute : caList) {
                if (classifyAttribute.getAttributeId() == attributeDefinition.getId()) {
                    vo.setChecked("checked");
                    break;
                }
            }
            voList.add(vo);
        }
        return voList;
    }
}
