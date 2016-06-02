package com.qcloud.component.commoditycenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.commoditycenter.dao.ClassifyAttributeDao;
import com.qcloud.component.commoditycenter.model.ClassifyAttribute;
import com.qcloud.component.commoditycenter.model.query.ClassifyAttributeQuery;

@Repository
public class ClassifyAttributeDaoCacheImpl implements ClassifyAttributeDao {

    @Autowired
    private ClassifyAttributeDao classifyAttributeDaoMysqlImpl;

    // @Autowired
    // private ClassifyAttributeDao classifyAttributeDaoRedisImpl;
    @Override
    public boolean add(ClassifyAttribute classifyAttribute) {

        return classifyAttributeDaoMysqlImpl.add(classifyAttribute);
    }

    @Override
    public ClassifyAttribute get(Long id) {

        return classifyAttributeDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return classifyAttributeDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(ClassifyAttribute classifyAttribute) {

        return classifyAttributeDaoMysqlImpl.update(classifyAttribute);
    }

    @Override
    public List<ClassifyAttribute> list(List<Long> idList) {

        return CacheLoader.list(classifyAttributeDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, ClassifyAttribute> map(Set<Long> idSet) {

        return CacheLoader.map(classifyAttributeDaoMysqlImpl, idSet);
    }

    @Override
    public Page<ClassifyAttribute> page(int start, int count) {

        return classifyAttributeDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<ClassifyAttribute> page(ClassifyAttributeQuery query, int start, int count) {

        return classifyAttributeDaoMysqlImpl.page(query, start, count);
    }

    public List<ClassifyAttribute> listAll() {

        return classifyAttributeDaoMysqlImpl.listAll();
    }

    @Override
    public ClassifyAttribute get(Long classifyId, Long attributeId) {

        return classifyAttributeDaoMysqlImpl.get(classifyId, attributeId);
    }

    @Override
    public List<ClassifyAttribute> listByClassify(Long classifyId) {

        return classifyAttributeDaoMysqlImpl.listByClassify(classifyId);
    }
}
