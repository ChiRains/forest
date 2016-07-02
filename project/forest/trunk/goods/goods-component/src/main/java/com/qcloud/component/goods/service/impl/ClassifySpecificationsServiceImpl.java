package com.qcloud.component.goods.service.impl;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.goods.dao.ClassifySpecificationsDao;
import com.qcloud.component.goods.exception.CommoditycenterException;
import com.qcloud.component.goods.model.AttributeDefinition;
import com.qcloud.component.goods.model.ClassifySpecifications;
import com.qcloud.component.goods.model.query.ClassifySpecificationsQuery;
import com.qcloud.component.goods.service.AttributeDefinitionService;
import com.qcloud.component.goods.service.ClassifySpecificationsService;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Service
public class ClassifySpecificationsServiceImpl implements ClassifySpecificationsService {
    @Autowired
    private ClassifySpecificationsDao classifySpecificationsDao;
    @Autowired
    private AutoIdGenerator autoIdGenerator;
    @Autowired
    private PublicdataClient publicdataClient;
    @Autowired
    private ParameterClient parameterClient;
    //
    private static final String CLASSIFY_SPECIFICATIONS_NUMBER = "merchandise-classify-specifications-number";
    @Autowired
    private AttributeDefinitionService attributeDefinitionService;
    //
    private static final String ID_KEY = "goods_classify_specifications";

    @PostConstruct
    public void init() {    
        final Integer number = parameterClient.get(CLASSIFY_SPECIFICATIONS_NUMBER);
        if (number == null) {
            throw new CommoditycenterException("请初始化参数：" + CLASSIFY_SPECIFICATIONS_NUMBER);
        }
        if (number > 5 || number <= 0) {
            throw new CommoditycenterException("初始化参数值只能为0,1或者2：" + CLASSIFY_SPECIFICATIONS_NUMBER);
        }
    }

    @Override
    public boolean add(ClassifySpecifications classifySpecifications) {
        Classify classify = publicdataClient.getClassify(classifySpecifications.getClassifyId());
        AssertUtil.assertNotNull(classify, "分类不存在.");
        AttributeDefinition attributeDefinition = attributeDefinitionService.get(classifySpecifications.getAttributeId());
        AssertUtil.assertNotNull(attributeDefinition, "属性不存在.");
        List<ClassifySpecifications> csList = classifySpecificationsDao.listByClassify(classifySpecifications.getClassifyId());
        for (ClassifySpecifications cs : csList) {
            if (cs.getAttributeId() == classifySpecifications.getAttributeId()) {
                throw new CommoditycenterException("商品分类规格已经设置过,不需要重复设置.分类:" + classify.getName() + " 规格属性:" + attributeDefinition.getName());
            }
        }
        final Integer number = parameterClient.get(CLASSIFY_SPECIFICATIONS_NUMBER);
        if (csList.size() >= number) {
            throw new CommoditycenterException("商品分类规格已经达到" + number + "个,不允许再添加.");
        }
        long id = autoIdGenerator.get(ID_KEY);
        classifySpecifications.setId(id);
        return classifySpecificationsDao.add(classifySpecifications);
    }

    @Override
    public ClassifySpecifications get(Long id) {
        return classifySpecificationsDao.get(id);
    }

    @Override
    public boolean delete(Long id) {
        return classifySpecificationsDao.delete(id);
    }

    @Override
    public boolean update(ClassifySpecifications classifySpecifications) {
        Classify classify = publicdataClient.getClassify(classifySpecifications.getClassifyId());
        AssertUtil.assertNotNull(classify, "分类不存在.");
        AttributeDefinition attributeDefinition = attributeDefinitionService.get(classifySpecifications.getAttributeId());
        AssertUtil.assertNotNull(attributeDefinition, "属性不存在.");
        return classifySpecificationsDao.update(classifySpecifications);
    }

    @Override
    public Page<ClassifySpecifications> page(ClassifySpecificationsQuery query, int start, int count) {
        return classifySpecificationsDao.page(query, start, count);
    }

    public List<ClassifySpecifications> listAll() {
        return classifySpecificationsDao.listAll();
    }

    @Override
    public List<ClassifySpecifications> listByClassify(Long classifyId) {
        return classifySpecificationsDao.listByClassify(classifyId);
    }

    @Override
    public ClassifySpecifications get(HashMap where) {
        return classifySpecificationsDao.get(where);
    }

    @Override
    public List<ClassifySpecifications> list(HashMap where) {
        return classifySpecificationsDao.list(where);
    }

    @Override
    public Page<ClassifySpecifications> page(HashMap where, int start, int size) {
        return classifySpecificationsDao.page(where, start, size);
    }
}
