package com.qcloud.component.publicdata.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.publicdata.model.ClassifyBsidMaxNumber;
import com.qcloud.component.publicdata.model.query.ClassifyBsidMaxNumberQuery;

public interface ClassifyBsidMaxNumberService {

    public boolean add(ClassifyBsidMaxNumber classifyBsidMaxNumber);

    public ClassifyBsidMaxNumber get(Long id);

    public boolean delete(Long id);

    public boolean update(ClassifyBsidMaxNumber classifyBsidMaxNumber);

    public Page<ClassifyBsidMaxNumber> page(ClassifyBsidMaxNumberQuery query, int start, int count);

    public List<ClassifyBsidMaxNumber> listAll();

    public int calculateNextBsid(long parentClassifyId, long type);
}
