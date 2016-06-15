package com.qcloud.component.publicdata.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.publicdata.dao.ClassifyBsidMaxNumberDao;
import com.qcloud.component.publicdata.model.ClassifyBsidMaxNumber;
import com.qcloud.component.publicdata.model.query.ClassifyBsidMaxNumberQuery;

@Repository
public class ClassifyBsidMaxNumberDaoCacheImpl implements ClassifyBsidMaxNumberDao {

    @Autowired
    private ClassifyBsidMaxNumberDao classifyBsidMaxNumberDaoMysqlImpl;

    // @Autowired
    // private ClassifyBsidMaxNumberDao classifyBsidMaxNumberDaoRedisImpl;
    @Override
    public boolean add(ClassifyBsidMaxNumber classifyBsidMaxNumber) {

        return classifyBsidMaxNumberDaoMysqlImpl.add(classifyBsidMaxNumber);
    }

    @Override
    public ClassifyBsidMaxNumber get(Long id) {

        return classifyBsidMaxNumberDaoMysqlImpl.get(id);
        // return CacheLoader.get(classifyBsidMaxNumberDaoRedisImpl, classifyBsidMaxNumberDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return classifyBsidMaxNumberDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(ClassifyBsidMaxNumber classifyBsidMaxNumber) {

        return classifyBsidMaxNumberDaoMysqlImpl.update(classifyBsidMaxNumber);
    }

    @Override
    public List<ClassifyBsidMaxNumber> list(List<Long> idList) {

        return CacheLoader.list(classifyBsidMaxNumberDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, ClassifyBsidMaxNumber> map(Set<Long> idSet) {

        return CacheLoader.map(classifyBsidMaxNumberDaoMysqlImpl, idSet);
    }

    @Override
    public Page<ClassifyBsidMaxNumber> page(int start, int count) {

        return classifyBsidMaxNumberDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<ClassifyBsidMaxNumber> page(ClassifyBsidMaxNumberQuery query, int start, int count) {

        return classifyBsidMaxNumberDaoMysqlImpl.page(query, start, count);
    }

    public List<ClassifyBsidMaxNumber> listAll() {

        return classifyBsidMaxNumberDaoMysqlImpl.listAll();
    }

    @Override
    public ClassifyBsidMaxNumber getByParentClassify(long parentClassifyId, long type) {

        return classifyBsidMaxNumberDaoMysqlImpl.getByParentClassify(parentClassifyId, type);
    }

    @Override
    public void lockNextBsid(ClassifyBsidMaxNumber classifyBsidMaxNumber) {

        classifyBsidMaxNumberDaoMysqlImpl.lockNextBsid(classifyBsidMaxNumber);
    }
}
