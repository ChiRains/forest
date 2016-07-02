package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ModularUserDao;
import com.qcloud.project.forest.model.ModularUser;
import com.qcloud.project.forest.model.query.ModularUserQuery;

@Repository
public class ModularUserDaoRedisImpl implements ModularUserDao {

    // @Resource(name = "redis-forest")
    // private Redis redis;
    @Override
    public boolean add(ModularUser modularUser) {

        throw new NotImplementedException();
    }

    @Override
    public ModularUser get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(ModularUser modularUser) {

        throw new NotImplementedException();
    }

    @Override
    public List<ModularUser> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ModularUser> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<ModularUser> listByUserId(Long userId) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ModularUser> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ModularUser> page(ModularUserQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<ModularUser> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public Boolean deleteByUserId(Long userId) {

        throw new NotImplementedException();
    }
}
