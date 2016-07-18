package com.qcloud.component.my.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyCollectionDao;
import com.qcloud.component.my.model.MyCollection;
import com.qcloud.component.my.model.key.TypeEnum.CollectionType;
import com.qcloud.component.my.model.query.MyCollectionQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MyCollectionDaoRedisImpl implements MyCollectionDao {

    // @Resource(name = "redis-my")
    // private Redis redis;
    @Override
    public boolean add(MyCollection myCollection) {

        throw new NotImplementedException();
    }

    @Override
    public MyCollection get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MyCollection myCollection) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyCollection> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyCollection> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyCollection> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyCollection> page(MyCollectionQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyCollection> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public MyCollection get(Long id, Long userId) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id, Long userId) {

        throw new NotImplementedException();
    }

    @Override
    public MyCollection getByObject(Long objId, Long userId, CollectionType type) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyCollection> list(Long userId, CollectionType type, Long classifyId, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public int countByUserId(Long userId) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyCollection> listByUser(Long userId, CollectionType type, Long classifyId) {

        throw new NotImplementedException();
    }
}
