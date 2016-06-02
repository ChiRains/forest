package com.qcloud.component.sellercenter.dao.redis;

import com.qcloud.component.sellercenter.dao.StoreMemberDao;
import com.qcloud.component.sellercenter.model.StoreMember;
import com.qcloud.component.sellercenter.model.query.StoreMemberQuery;
import com.qcloud.pirates.data.Page;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class StoreMemberDaoRedisImpl implements StoreMemberDao {

    //@Resource(name = "redis-sellercenter")
    //private Redis redis;

    @Override
    public boolean add(StoreMember storeMember) {
        throw new NotImplementedException();
    }

    @Override
    public StoreMember get(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public boolean update(StoreMember storeMember) {
        throw new NotImplementedException();
    }

    @Override
    public List<StoreMember> list(List<Long> idList) {
        throw new NotImplementedException();
    }

    @Override
    public Map<Long, StoreMember> map(Set<Long> idSet) {
        throw new NotImplementedException();
    }

    @Override
    public Page<StoreMember> page(int start, int count) {
        throw new NotImplementedException();
    }

    @Override
    public Page<StoreMember> page(StoreMemberQuery query, int start, int count) {
        throw new NotImplementedException();
    }

    @Override
    public List<StoreMember> listAll() {
        throw new NotImplementedException();
    }

    @Override
    public StoreMember get(HashMap where) {
        throw new NotImplementedException();
    }

    @Override
    public List<StoreMember> list(HashMap where) {
        throw new NotImplementedException();
    }

    @Override
    public Page<StoreMember> page(HashMap where, int start, int size) {
        throw new NotImplementedException();
    }
}

