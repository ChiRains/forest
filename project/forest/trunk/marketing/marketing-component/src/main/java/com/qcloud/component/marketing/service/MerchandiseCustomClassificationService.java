package com.qcloud.component.marketing.service;

import java.util.List;
import java.util.Map;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.marketing.model.MerchandiseCustomClassification;
import com.qcloud.component.marketing.model.query.MerchandiseCustomClassificationQuery;

public interface MerchandiseCustomClassificationService {

    public boolean add(MerchandiseCustomClassification merchandiseCustomClassification);

    public MerchandiseCustomClassification get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseCustomClassification merchandiseCustomClassification);

    public Page<MerchandiseCustomClassification> page(MerchandiseCustomClassificationQuery query, int start, int count);

    public List<MerchandiseCustomClassification> listAll();

    public List<MerchandiseCustomClassification> listAll(Map<String, Object> map);

    public boolean delete(Map<String, Object> map);

    public List<MerchandiseCustomClassification> list(long merchantId, long customClassifyId, int start, int size);
}
