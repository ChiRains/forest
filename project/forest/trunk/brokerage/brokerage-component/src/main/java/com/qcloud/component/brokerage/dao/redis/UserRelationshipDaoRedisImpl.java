package com.qcloud.component.brokerage.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.brokerage.dao.UserRelationshipDao;
import com.qcloud.component.brokerage.model.UserRelationship;
import com.qcloud.component.brokerage.model.key.TypeEnum.UserAllocationType;
import com.qcloud.component.brokerage.model.query.UserRelationshipQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class UserRelationshipDaoRedisImpl implements UserRelationshipDao {

    // @Resource(name = "redis-distribution")
    // private Redis redis;
    @Override
    public boolean add(UserRelationship userRelationship) {

        throw new NotImplementedException();
    }

    @Override
    public UserRelationship get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(UserRelationship userRelationship) {

        throw new NotImplementedException();
    }

    @Override
    public List<UserRelationship> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, UserRelationship> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<UserRelationship> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<UserRelationship> page(UserRelationshipQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<UserRelationship> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public UserRelationship getByUserId(Long userId) {

        throw new NotImplementedException();
    }

    @Override
    public Page<UserRelationship> pageRecommed(UserRelationshipQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<UserRelationship> listChildren(long recommedId, UserAllocationType allocation) {

        throw new NotImplementedException();
    }

    @Override
    public int countChildren(long recommedId, UserAllocationType type) {

        throw new NotImplementedException();
    }

    @Override
    public Page<UserRelationship> pageOneChildren(long recommedId, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<UserRelationship> pageTwoChildren(long recommedId, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<UserRelationship> pageThreeChildren(long recommedId, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public int getCountByOneChildren(long recommedId) {

        throw new NotImplementedException();
    }

    @Override
    public int getCountByTwoChildren(long recommedId) {

        throw new NotImplementedException();
    }

    @Override
    public int getCountByThreeChildren(long recommedId) {

        throw new NotImplementedException();
    }
}
