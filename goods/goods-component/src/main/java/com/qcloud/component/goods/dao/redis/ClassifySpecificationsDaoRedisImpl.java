package com.qcloud.component.goods.dao.redis;

import com.qcloud.component.goods.dao.ClassifySpecificationsDao;
import com.qcloud.component.goods.model.ClassifySpecifications;
import com.qcloud.component.goods.model.query.ClassifySpecificationsQuery;
import com.qcloud.pirates.data.Page;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class ClassifySpecificationsDaoRedisImpl implements ClassifySpecificationsDao {

    //@Resource(name = "redis-commoditycenter")
    //private Redis redis;

    @Override
    public boolean add(ClassifySpecifications classifySpecifications) {
        throw new NotImplementedException();
    }

    @Override
    public ClassifySpecifications get(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public boolean update(ClassifySpecifications classifySpecifications) {
        throw new NotImplementedException();
    }

    @Override
    public List<ClassifySpecifications> list(List<Long> idList) {
        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ClassifySpecifications> map(Set<Long> idSet) {
        throw new NotImplementedException();
    }

    @Override
    public Page<ClassifySpecifications> page(int start, int count) {
        throw new NotImplementedException();
    }

    @Override
    public Page<ClassifySpecifications> page(ClassifySpecificationsQuery query, int start, int count) {
        throw new NotImplementedException();
    }

    @Override
    public List<ClassifySpecifications> listAll() {
        throw new NotImplementedException();
    }

    @Override
    public List<ClassifySpecifications> listByClassify(Long classifyId) {
        throw new NotImplementedException();
    }

    @Override
    public ClassifySpecifications get(HashMap where) {
        throw new NotImplementedException();
    }

    @Override
    public List<ClassifySpecifications> list(HashMap where) {
        throw new NotImplementedException();
    }

    @Override
    public Page<ClassifySpecifications> page(HashMap where, int start, int size) {
        throw new NotImplementedException();
    }
}

