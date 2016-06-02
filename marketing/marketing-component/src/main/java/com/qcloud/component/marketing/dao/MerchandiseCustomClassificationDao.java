package com.qcloud.component.marketing.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.marketing.model.MerchandiseCustomClassification;
import com.qcloud.component.marketing.model.query.MerchandiseCustomClassificationQuery;

public interface MerchandiseCustomClassificationDao extends ISimpleDao<MerchandiseCustomClassification, Long> {

    public boolean add(MerchandiseCustomClassification merchandiseCustomClassification);

    public MerchandiseCustomClassification get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseCustomClassification merchandiseCustomClassification);

    public List<MerchandiseCustomClassification> list(List<Long> idList);

    public Map<Long, MerchandiseCustomClassification> map(Set<Long> idSet);

    public Page<MerchandiseCustomClassification> page(MerchandiseCustomClassificationQuery query, int start, int size);

    public List<MerchandiseCustomClassification> listAll();

    public List<MerchandiseCustomClassification> listAll(Map<String, Object> map);

    public boolean delete(Map<String, Object> map);

    List<MerchandiseCustomClassification> list(long merchantId, long customClassifyId, int start, int size);
}
