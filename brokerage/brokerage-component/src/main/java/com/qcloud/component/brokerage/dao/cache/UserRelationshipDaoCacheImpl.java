package com.qcloud.component.brokerage.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.brokerage.dao.UserRelationshipDao;
import com.qcloud.component.brokerage.model.UserRelationship;
import com.qcloud.component.brokerage.model.key.TypeEnum.UserAllocationType;
import com.qcloud.component.brokerage.model.query.UserRelationshipQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class UserRelationshipDaoCacheImpl implements UserRelationshipDao {

    @Autowired
    private UserRelationshipDao userRelationshipDaoMysqlImpl;

    // @Autowired
    // private UserRelationshipDao userRelationshipDaoRedisImpl;
    @Override
    public boolean add(UserRelationship userRelationship) {

        return userRelationshipDaoMysqlImpl.add(userRelationship);
    }

    @Override
    public UserRelationship get(Long id) {

        return userRelationshipDaoMysqlImpl.get(id);
        // return CacheLoader.get(userRelationshipDaoRedisImpl, userRelationshipDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return userRelationshipDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(UserRelationship userRelationship) {

        return userRelationshipDaoMysqlImpl.update(userRelationship);
    }

    @Override
    public List<UserRelationship> list(List<Long> idList) {

        return CacheLoader.list(userRelationshipDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, UserRelationship> map(Set<Long> idSet) {

        return CacheLoader.map(userRelationshipDaoMysqlImpl, idSet);
    }

    @Override
    public Page<UserRelationship> page(int start, int count) {

        return userRelationshipDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<UserRelationship> page(UserRelationshipQuery query, int start, int count) {

        return userRelationshipDaoMysqlImpl.page(query, start, count);
    }

    public List<UserRelationship> listAll() {

        return userRelationshipDaoMysqlImpl.listAll();
    }

    @Override
    public UserRelationship getByUserId(Long userId) {

        return userRelationshipDaoMysqlImpl.getByUserId(userId);
    }

    @Override
    public List<UserRelationship> listChildren(long recommedId, UserAllocationType allocation) {

        return userRelationshipDaoMysqlImpl.listChildren(recommedId, allocation);
    }

    @Override
    public int countChildren(long recommedId, UserAllocationType allocation) {

        return userRelationshipDaoMysqlImpl.countChildren(recommedId, allocation);
    }

    @Override
    public Page<UserRelationship> pageRecommed(UserRelationshipQuery query, int start, int count) {

        return userRelationshipDaoMysqlImpl.pageRecommed(query, start, count);
    }

    @Override
    public Page<UserRelationship> pageOneChildren(long recommedId, int start, int count) {

        return userRelationshipDaoMysqlImpl.pageOneChildren(recommedId, start, count);
    }

    @Override
    public Page<UserRelationship> pageTwoChildren(long recommedId, int start, int count) {

        return userRelationshipDaoMysqlImpl.pageTwoChildren(recommedId, start, count);
    }

    @Override
    public Page<UserRelationship> pageThreeChildren(long recommedId, int start, int count) {

        return userRelationshipDaoMysqlImpl.pageThreeChildren(recommedId, start, count);
    }

    @Override
    public int getCountByOneChildren(long recommedId) {

        return userRelationshipDaoMysqlImpl.getCountByOneChildren(recommedId);
    }

    @Override
    public int getCountByTwoChildren(long recommedId) {

        return userRelationshipDaoMysqlImpl.getCountByTwoChildren(recommedId);
    }

    @Override
    public int getCountByThreeChildren(long recommedId) {

        return userRelationshipDaoMysqlImpl.getCountByThreeChildren(recommedId);
    }
}
