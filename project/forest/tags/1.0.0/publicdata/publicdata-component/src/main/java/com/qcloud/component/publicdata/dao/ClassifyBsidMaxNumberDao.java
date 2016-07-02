package com.qcloud.component.publicdata.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.publicdata.model.ClassifyBsidMaxNumber;
import com.qcloud.component.publicdata.model.query.ClassifyBsidMaxNumberQuery;

public interface ClassifyBsidMaxNumberDao extends ISimpleDao<ClassifyBsidMaxNumber, Long> {

    public boolean add(ClassifyBsidMaxNumber classifyBsidMaxNumber);

    public ClassifyBsidMaxNumber get(Long id);

    public boolean delete(Long id);

    public boolean update(ClassifyBsidMaxNumber classifyBsidMaxNumber);

    public List<ClassifyBsidMaxNumber> list(List<Long> idList);

    public Map<Long, ClassifyBsidMaxNumber> map(Set<Long> idSet);

    public Page<ClassifyBsidMaxNumber> page(ClassifyBsidMaxNumberQuery query, int start, int size);

    public List<ClassifyBsidMaxNumber> listAll();

    ClassifyBsidMaxNumber getByParentClassify(long parentClassifyId, long type);

    void lockNextBsid(ClassifyBsidMaxNumber classifyBsidMaxNumber);
}
