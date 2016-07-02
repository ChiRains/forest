package com.qcloud.component.commoditycenter.service;

import java.util.List;
import com.qcloud.component.commoditycenter.model.ClassifyAttribute;
import com.qcloud.component.commoditycenter.model.query.ClassifyAttributeQuery;
import com.qcloud.pirates.data.Page;

public interface ClassifyAttributeService {
    
    public boolean add(ClassifyAttribute classifyAttribute);

    public ClassifyAttribute get(Long id);

    public ClassifyAttribute get(Long classifyId, Long attributeId);

    public boolean delete(Long id);

    public boolean update(ClassifyAttribute classifyAttribute);

    public Page<ClassifyAttribute> page(ClassifyAttributeQuery query, int start, int count);

    public List<ClassifyAttribute> listAll();

    public List<ClassifyAttribute> listByClassify(Long classifyId);
}
