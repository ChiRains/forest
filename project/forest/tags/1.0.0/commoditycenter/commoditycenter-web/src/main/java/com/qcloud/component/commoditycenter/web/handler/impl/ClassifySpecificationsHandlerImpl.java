package com.qcloud.component.commoditycenter.web.handler.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.commoditycenter.model.AttributeDefinition;
import com.qcloud.component.commoditycenter.model.ClassifySpecifications;
import com.qcloud.component.commoditycenter.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.ClassifySpecificationsUploadImageType;
import com.qcloud.component.commoditycenter.service.AttributeDefinitionService;
import com.qcloud.component.commoditycenter.service.ClassifySpecificationsService;
import com.qcloud.component.commoditycenter.service.EnumerationService;
import com.qcloud.component.commoditycenter.web.handler.ClassifySpecificationsHandler;
import com.qcloud.component.commoditycenter.web.vo.AttributeSpecificationsVO;
import com.qcloud.component.commoditycenter.web.vo.ClassifySpecificationsVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminClassifyCompVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminClassifySpecificationsVO;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.publicdata.util.ClassifyUtils;
import com.qcloud.pirates.core.json.Json;

@Component
public class ClassifySpecificationsHandlerImpl implements ClassifySpecificationsHandler {

    @Autowired
    AttributeDefinitionService    attributeDefinitionService;

    @Autowired
    ClassifySpecificationsService classifySpecificationsService;

    @Autowired
    EnumerationService            enumerationService;

    @Override
    public List<ClassifySpecificationsVO> toVOList(List<ClassifySpecifications> list) {

        List<ClassifySpecificationsVO> voList = new ArrayList<ClassifySpecificationsVO>();
        for (ClassifySpecifications classifySpecifications : list) {
            voList.add(toVO(classifySpecifications));
        }
        return voList;
    }

    @Override
    public ClassifySpecificationsVO toVO(ClassifySpecifications classifySpecifications) {

        String json = Json.toJson(classifySpecifications);
        return Json.toObject(json, ClassifySpecificationsVO.class, true);
    }

    @Override
    public List<AdminClassifyCompVO> toVOList4Admin(List<ClassifySpecifications> list) {

        List<AdminClassifyCompVO> voList = new ArrayList<AdminClassifyCompVO>();
        for (ClassifySpecifications adminClassifySpecifications : list) {
            voList.add(toCompVO4Admin(adminClassifySpecifications));
        }
        return voList;
    }

    @Override
    public AdminClassifyCompVO toCompVO4Admin(ClassifySpecifications classifySpecifications) {

        String json = Json.toJson(classifySpecifications);
        return Json.toObject(json, AdminClassifyCompVO.class, true);
    }

    @Override
    public List<AdminClassifyCompVO> toVOList4Admin(List<Classify> cList, List<ClassifySpecifications> csList) {

        List<AdminClassifyCompVO> voList = new ArrayList<AdminClassifyCompVO>();
        for (Classify classify : cList) {
            AdminClassifyCompVO vo = toVO4Admin(classify, csList);
            String path = ClassifyUtils.calculationPath(cList, classify);
            vo.setPath(path);
            voList.add(vo);
        }
        return voList;
    }

    public AdminClassifyCompVO toVO4Admin(Classify classify, List<ClassifySpecifications> csList) {

        AdminClassifyCompVO vo = new AdminClassifyCompVO();
        vo.setClassifyId(classify.getId());
        vo.setClassifyStr(classify.getName());
        List<AdminClassifySpecificationsVO> detailList = new ArrayList<AdminClassifySpecificationsVO>();
        for (ClassifySpecifications classifySpecifications : csList) {
            if (classify.getId() == classifySpecifications.getClassifyId()) {
                detailList.add(toVO4Admin(classifySpecifications));
            }
        }
        vo.setDetailList(detailList);
        return vo;
    }

    @Override
    public AdminClassifySpecificationsVO toVO4Admin(ClassifySpecifications classifySpecifications) {

        AdminClassifySpecificationsVO vo = new AdminClassifySpecificationsVO();
        vo.setId(classifySpecifications.getId());
        vo.setAttributeId(classifySpecifications.getAttributeId());
        AttributeDefinition ad = attributeDefinitionService.get(classifySpecifications.getAttributeId());
        vo.setAttributeStr(ad == null ? "" : ad.getName());
        vo.setUploadImage(classifySpecifications.getUploadImage());
        vo.setUploadImageStr(ClassifySpecificationsUploadImageType.YES.getName());
        vo.setUploadImageChecked("checked");
        if (classifySpecifications.getUploadImage() == ClassifySpecificationsUploadImageType.NO.getKey()) {
            vo.setUploadImageStr(ClassifySpecificationsUploadImageType.NO.getName());
            vo.setUploadImageChecked("");
        }
        vo.setClassifyId(classifySpecifications.getClassifyId());
        return vo;
    }

    public List<AdminClassifySpecificationsVO> toDetailVOList4Admin(List<ClassifySpecifications> classifySpecificationses) {

        List<AdminClassifySpecificationsVO> adminClassifySpecificationsVOs = new ArrayList<AdminClassifySpecificationsVO>();
        AdminClassifySpecificationsVO adminClassifySpecificationsVO = new AdminClassifySpecificationsVO();
        for (ClassifySpecifications classifySpecifications : classifySpecificationses) {
            adminClassifySpecificationsVO = toVO4Admin(classifySpecifications);
            if (adminClassifySpecificationsVO != null) {
                adminClassifySpecificationsVOs.add(adminClassifySpecificationsVO);
            }
        }
        return adminClassifySpecificationsVOs;
    }

    // @Override
    // public AttributeSpecificationsVO toAttributeSpecificationsVO(ClassifySpecifications classifySpecifications) {
    //
    // AttributeDefinition attributeDefinition = attributeDefinitionService.get(classifySpecifications.getAttributeId());
    // List<Enumeration> enumerations = enumerationService.listByName(attributeDefinition.getEnumeration());
    // if (enumerations.size() == 0) {
    // return null;
    // }
    // List<String> stringList = new ArrayList<String>(enumerations.size());
    // for (int i = 0, length = enumerations.size(); i < length; i++) {
    // stringList.add(enumerations.get(i).getValue());
    // }
    // AttributeSpecificationsVO vo = new AttributeSpecificationsVO();
    // vo.setId(attributeDefinition.getId());
    // vo.setName(attributeDefinition.getName());
    // vo.setSpecificationsList(stringList);
    // return vo;
    // }
    // @Override
    // public List<AttributeSpecificationsVO> toAttributeSpecificationsVOList(List<ClassifySpecifications> list) {
    //
    // List<AttributeSpecificationsVO> attributeSpecificationsVOList = new ArrayList<AttributeSpecificationsVO>();
    // for (ClassifySpecifications classifySpecifications : list) {
    // AttributeSpecificationsVO attributeSpecificationsVO = toAttributeSpecificationsVO(classifySpecifications);
    // if (attributeSpecificationsVO != null) {
    // attributeSpecificationsVOList.add(toAttributeSpecificationsVO(classifySpecifications));
    // }
    // }
    // return attributeSpecificationsVOList;
    // }
    @Override
    public List<AttributeSpecificationsVO> toAttributeSpecificationsVOList(List<MerchandiseSpecificationsRelation> list) {

        List<AttributeSpecificationsVO> attributeSpecificationsVOList = new ArrayList<AttributeSpecificationsVO>();
        Set<Long> attrSet = new HashSet<Long>();
        for (MerchandiseSpecificationsRelation merchandiseSpecificationsRelation : list) {
            attrSet.add(merchandiseSpecificationsRelation.getAttributeId());
        }
        for (Long attributeId : attrSet) {
            AttributeSpecificationsVO vo = new AttributeSpecificationsVO();
            vo.setId(attributeId);
            AttributeDefinition attributeDefinition = attributeDefinitionService.get(attributeId);
            vo.setName(attributeDefinition.getName());
            List<String> stringList = new ArrayList<String>(list.size());
            for (MerchandiseSpecificationsRelation merchandiseSpecificationsRelation : list) {
                if (attributeId == merchandiseSpecificationsRelation.getAttributeId()) {
                    stringList.add(merchandiseSpecificationsRelation.getAlias());
                }
            }
            vo.setSpecificationsList(stringList);
            attributeSpecificationsVOList.add(vo);
        }
        return attributeSpecificationsVOList;
    }
}
