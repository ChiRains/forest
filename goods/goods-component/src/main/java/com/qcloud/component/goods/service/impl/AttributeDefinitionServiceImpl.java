package com.qcloud.component.goods.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.goods.dao.AttributeDefinitionDao;
import com.qcloud.component.goods.exception.CommoditycenterException;
import com.qcloud.component.goods.model.AttributeDefinition;
import com.qcloud.component.goods.model.Enumeration;
import com.qcloud.component.goods.model.key.TypeEnum;
import com.qcloud.component.goods.model.query.AttributeDefinitionQuery;
import com.qcloud.component.goods.service.AttributeDefinitionService;
import com.qcloud.component.goods.service.EnumerationService;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;

@Service
public class AttributeDefinitionServiceImpl implements AttributeDefinitionService {

    @Autowired
    private AttributeDefinitionDao attributeDefinitionDao;

    @Autowired
    private EnumerationService     enumerationService;

    @Autowired
    private AutoIdGenerator        autoIdGenerator;

    private static final String    ID_KEY = "goods_attribute_definition";

    @Transactional
    @Override
    public Long add(AttributeDefinition attributeDefinition) {

        AttributeDefinition a = attributeDefinitionDao.getByCode(attributeDefinition.getCode());
        if (a != null) {
            throw new CommoditycenterException("属性编码已经使用." + attributeDefinition.getCode());
        }
        long id = autoIdGenerator.get(ID_KEY);
        attributeDefinition.setId(id);
        String uuidStr = StringUtil.uuid();
        attributeDefinition.setEnumeration(uuidStr);
        if (Integer.valueOf(attributeDefinition.getType()) == TypeEnum.AttrType.spec.getKey()) {
            attributeDefinition.setCode(uuidStr);
        }
        attributeDefinitionDao.add(attributeDefinition);
        
        if (Integer.valueOf(attributeDefinition.getType()) == TypeEnum.AttrType.spec.getKey()) {
            Enumeration enumeration = new Enumeration();
            enumeration.setName(attributeDefinition.getName().trim());
            enumeration.setName(uuidStr);
            enumeration.setValue(attributeDefinition.getValue().trim());
            enumerationService.add(enumeration);
        }
        return id;
    }

    @Override
    public AttributeDefinition get(Long id) {

        return attributeDefinitionDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return attributeDefinitionDao.delete(id);
    }

    @Transactional
    @Override
    public boolean update(AttributeDefinition attributeDefinition) {

        AttributeDefinition a = attributeDefinitionDao.getByCode(attributeDefinition.getCode());
        if (a != null && attributeDefinition.getId() != a.getId()) {
            throw new CommoditycenterException("属性编码已经使用." + attributeDefinition.getCode());
        }
        AttributeDefinition attr = attributeDefinitionDao.get(attributeDefinition.getId());
        AssertUtil.assertNotNull(attr, "该商品属性不存在！");
        attributeDefinition.setType(attr.getType());
        attributeDefinition.setEnumeration(attr.getEnumeration());
        boolean flag = attributeDefinitionDao.update(attributeDefinition);
        if (Integer.valueOf(attributeDefinition.getType()) == TypeEnum.AttrType.spec.getKey()) {
            Enumeration enumeration = new Enumeration();
            enumeration.setName(attributeDefinition.getEnumeration());
            enumeration.setValue(attributeDefinition.getValue().trim());
            enumerationService.update(enumeration);
        }
        return flag;
    }

    @Override
    public Page<AttributeDefinition> page(AttributeDefinitionQuery query, int start, int count) {

        return attributeDefinitionDao.page(query, start, count);
    }

    public List<AttributeDefinition> listAll() {

        return attributeDefinitionDao.listAll();
    }

    @Override
    public List<AttributeDefinition> list(List<Long> idList) {

        return attributeDefinitionDao.list(idList);
    }

    @Override
    public Page<AttributeDefinition> list4Select(AttributeDefinitionQuery query, int start, int size) {

        return attributeDefinitionDao.list4Select(query, start, size);
    }
}
