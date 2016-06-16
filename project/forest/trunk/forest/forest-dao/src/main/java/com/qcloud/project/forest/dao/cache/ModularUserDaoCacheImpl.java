package com.qcloud.project.forest.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ModularUserDao;
import com.qcloud.project.forest.model.ModularUser;
import com.qcloud.project.forest.model.query.ModularUserQuery;

@Repository
public class ModularUserDaoCacheImpl implements ModularUserDao {

    @Autowired
    private ModularUserDao modularUserDaoMysqlImpl;

    @Autowired
    private ModularUserDao modularUserDaoRedisImpl;

    @Override
    public boolean add(ModularUser modularUser) {

        return modularUserDaoMysqlImpl.add(modularUser);
    }

    @Override
    public ModularUser get(Long id) {

        return CacheLoader.get(modularUserDaoRedisImpl, modularUserDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return modularUserDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(ModularUser modularUser) {

        return modularUserDaoMysqlImpl.update(modularUser);
    }

    @Override
    public List<ModularUser> list(List<Long> idList) {

        return CacheLoader.list(modularUserDaoRedisImpl, modularUserDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, ModularUser> map(Set<Long> idSet) {

        return CacheLoader.map(modularUserDaoRedisImpl, modularUserDaoMysqlImpl, idSet);
    }

    public List<ModularUser> listByUserId(Long userId) {

        return modularUserDaoMysqlImpl.listByUserId(userId);
    }

    @Override
    public Page<ModularUser> page(int start, int count) {

        return modularUserDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<ModularUser> page(ModularUserQuery query, int start, int count) {

        return modularUserDaoMysqlImpl.page(query, start, count);
    }

    public List<ModularUser> listAll() {

        return modularUserDaoMysqlImpl.listAll();
    }

    @Override
    public Boolean deleteByUserId(Long userId) {

        return modularUserDaoMysqlImpl.deleteByUserId(userId);
    }
}
