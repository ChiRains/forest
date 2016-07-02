package com.qcloud.component.my.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.ConsigneeDao;
import com.qcloud.component.my.model.Consignee;
import com.qcloud.component.my.model.query.ConsigneeQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class ConsigneeDaoRedisImpl implements ConsigneeDao {

    // @Resource(name = "redis-my")
    // private Redis redis;

    @Override
    public boolean add(Consignee consignee) {

        throw new NotImplementedException();
    }

    @Override
    public Consignee get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(Consignee consignee) {

        throw new NotImplementedException();
    }

    @Override
    public List<Consignee> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Consignee> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Consignee> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Consignee> page(ConsigneeQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<Consignee> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<Consignee> listByUser(Long userId) {

        throw new NotImplementedException();
    }
}
