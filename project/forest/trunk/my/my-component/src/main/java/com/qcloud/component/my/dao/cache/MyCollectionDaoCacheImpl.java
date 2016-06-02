package com.qcloud.component.my.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyCollectionDao;
import com.qcloud.component.my.model.MyCollection;
import com.qcloud.component.my.model.key.TypeEnum.CollectionType;
import com.qcloud.component.my.model.query.MyCollectionQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class MyCollectionDaoCacheImpl implements MyCollectionDao {

    @Autowired
    private MyCollectionDao myCollectionDaoMysqlImpl;

    @Autowired
    private MyCollectionDao myCollectionDaoRedisImpl;

    @Override
    public boolean add(MyCollection myCollection) {

        return myCollectionDaoMysqlImpl.add(myCollection);
    }

    @Override
    public MyCollection get(Long id) {

        return CacheLoader.get(myCollectionDaoRedisImpl, myCollectionDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return myCollectionDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MyCollection myCollection) {

        return myCollectionDaoMysqlImpl.update(myCollection);
    }

    @Override
    public List<MyCollection> list(List<Long> idList) {

        return CacheLoader.list(myCollectionDaoRedisImpl, myCollectionDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MyCollection> map(Set<Long> idSet) {

        return CacheLoader.map(myCollectionDaoRedisImpl, myCollectionDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MyCollection> page(int start, int count) {

        return myCollectionDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MyCollection> page(MyCollectionQuery query, int start, int count) {

        return myCollectionDaoMysqlImpl.page(query, start, count);
    }

    public List<MyCollection> listAll() {

        return myCollectionDaoMysqlImpl.listAll();
    }

    @Override
    public MyCollection get(Long id, Long userId) {

        return myCollectionDaoMysqlImpl.get(id, userId);
    }

    @Override
    public boolean delete(Long id, Long userId) {

        return myCollectionDaoMysqlImpl.delete(id, userId);
    }

    @Override
    public MyCollection getByObject(Long objId, Long userId, CollectionType type) {

        return myCollectionDaoMysqlImpl.getByObject(objId, userId, type);
    }

    @Override
    public List<MyCollection> list(Long userId, CollectionType type, Long classifyId, int start, int count) {

        return myCollectionDaoMysqlImpl.list(userId, type, classifyId, start, count);
    }
}
