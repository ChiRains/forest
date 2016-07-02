package com.qcloud.component.marketing.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.marketing.dao.MerchandiseCustomClassificationDao;
import com.qcloud.component.marketing.model.MerchandiseCustomClassification;
import com.qcloud.component.marketing.service.MerchandiseCustomClassificationService;
import com.qcloud.component.marketing.model.query.MerchandiseCustomClassificationQuery;

@Service
public class MerchandiseCustomClassificationServiceImpl implements MerchandiseCustomClassificationService {

    @Autowired
    private MerchandiseCustomClassificationDao merchandiseCustomClassificationDao;

    @Autowired
    private AutoIdGenerator                    autoIdGenerator;

    private static final String                ID_KEY = "marketing_merchandise_custom_classification";

    @Override
    public boolean add(MerchandiseCustomClassification merchandiseCustomClassification) {

        long id = autoIdGenerator.get(ID_KEY);
        merchandiseCustomClassification.setId(id);
        return merchandiseCustomClassificationDao.add(merchandiseCustomClassification);
    }

    @Override
    public MerchandiseCustomClassification get(Long id) {

        return merchandiseCustomClassificationDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseCustomClassificationDao.delete(id);
    }

    @Override
    public boolean update(MerchandiseCustomClassification merchandiseCustomClassification) {

        return merchandiseCustomClassificationDao.update(merchandiseCustomClassification);
    }

    @Override
    public Page<MerchandiseCustomClassification> page(MerchandiseCustomClassificationQuery query, int start, int count) {

        return merchandiseCustomClassificationDao.page(query, start, count);
    }

    public List<MerchandiseCustomClassification> listAll() {

        return merchandiseCustomClassificationDao.listAll();
    }

    @Override
    public List<MerchandiseCustomClassification> listAll(Map<String, Object> map) {

        return merchandiseCustomClassificationDao.listAll(map);
    }

    @Override
    public boolean delete(Map<String, Object> map) {

        return merchandiseCustomClassificationDao.delete(map);
    }

    @Override
    public List<MerchandiseCustomClassification> list(long merchantId, long customClassifyId, int start, int size) {

        return merchandiseCustomClassificationDao.list(merchantId, customClassifyId,start,size);
    }
}
