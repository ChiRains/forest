package com.qcloud.component.goods.service;

import com.qcloud.component.goods.model.ClassifySpecifications;
import com.qcloud.component.goods.model.query.ClassifySpecificationsQuery;
import com.qcloud.pirates.data.Page;

import java.util.HashMap;
import java.util.List;

public interface ClassifySpecificationsService {

    public boolean add(ClassifySpecifications classifySpecifications);

    public ClassifySpecifications get(Long id);

    public boolean delete(Long id);

    public boolean update(ClassifySpecifications classifySpecifications);

    public Page<ClassifySpecifications> page(ClassifySpecificationsQuery query, int start, int count);

    public List<ClassifySpecifications> listAll();

    public List<ClassifySpecifications> listByClassify(Long classifyId);

    public ClassifySpecifications get(HashMap where);

    public List<ClassifySpecifications> list(HashMap where);

    public Page<ClassifySpecifications> page(HashMap where, int start, int size);
}

