package com.qcloud.project.forest.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ModularDao;
import com.qcloud.project.forest.model.Modular;
import com.qcloud.project.forest.model.query.ModularQuery;

@Repository
public class ModularDaoCacheImpl implements ModularDao {

    @Autowired
    private ModularDao modularDaoMysqlImpl;

    @Autowired
    private ModularDao modularDaoRedisImpl;

    @Override
    public boolean add(Modular modular) {

        return modularDaoMysqlImpl.add(modular);
    }

    @Override
    public Modular get(Long id) {

        return modularDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return modularDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(Modular modular) {

        return modularDaoMysqlImpl.update(modular);
    }

    @Override
    public List<Modular> list(List<Long> idList) {

        return CacheLoader.list(modularDaoRedisImpl, modularDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, Modular> map(Set<Long> idSet) {

        return CacheLoader.map(modularDaoRedisImpl, modularDaoMysqlImpl, idSet);
    }

    @Override
    public Page<Modular> page(int start, int count) {

        return modularDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<Modular> page(ModularQuery query, int start, int count) {

        return modularDaoMysqlImpl.page(query, start, count);
    }

    public List<Modular> listAll() {

        return modularDaoMysqlImpl.listAll();
    }

    @Override
    public Modular getByCode(String code) {

        return modularDaoMysqlImpl.getByCode(code);
    }
}
