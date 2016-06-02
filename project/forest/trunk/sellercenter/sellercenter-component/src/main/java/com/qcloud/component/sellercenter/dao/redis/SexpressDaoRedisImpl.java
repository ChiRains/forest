package com.qcloud.component.sellercenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.sellercenter.dao.SexpressDao;
import com.qcloud.component.sellercenter.model.Sexpress;
import com.qcloud.component.sellercenter.model.query.SexpressQuery;

@Repository
public class SexpressDaoRedisImpl implements SexpressDao {

    // @Resource(name = "redis-sellercenter")
    // private Redis redis;
    @Override
    public boolean add(Sexpress sexpress) {

        throw new NotImplementedException();
    }

    @Override
    public Sexpress get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(Sexpress sexpress) {

        throw new NotImplementedException();
    }

    @Override
    public List<Sexpress> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Sexpress> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Sexpress> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Sexpress> page(SexpressQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<Sexpress> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<Sexpress> listByMerchant(Long merchantId) {

        throw new NotImplementedException();
    }
}
