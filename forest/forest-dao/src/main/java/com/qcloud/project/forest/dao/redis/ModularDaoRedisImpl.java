package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ModularDao;
import com.qcloud.project.forest.model.Modular;
import com.qcloud.project.forest.model.query.ModularQuery;

@Repository
public class ModularDaoRedisImpl implements ModularDao {

    // @Resource(name = "redis-forest")
    // private Redis redis;
    @Override
    public boolean add(Modular modular) {

        throw new NotImplementedException();
    }

    @Override
    public Modular get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(Modular modular) {

        throw new NotImplementedException();
    }

    @Override
    public List<Modular> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Modular> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Modular> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Modular> page(ModularQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<Modular> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public Modular getByCode(String code) {

        throw new NotImplementedException();
    }
}
