package com.qcloud.component.my.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.ConsigneeDao;
import com.qcloud.component.my.model.Consignee;
import com.qcloud.component.my.model.query.ConsigneeQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class ConsigneeDaoCacheImpl implements ConsigneeDao {

    @Autowired
    private ConsigneeDao consigneeDaoMysqlImpl;

//    @Autowired
//    private ConsigneeDao consigneeDaoRedisImpl;

    @Override
    public boolean add(Consignee consignee) {

        return consigneeDaoMysqlImpl.add(consignee);
    }

    @Override
    public Consignee get(Long id) {
        return consigneeDaoMysqlImpl.get(id);
//        return CacheLoader.get(consigneeDaoRedisImpl, consigneeDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return consigneeDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(Consignee consignee) {

        return consigneeDaoMysqlImpl.update(consignee);
    }

    @Override
    public List<Consignee> list(List<Long> idList) {

        return CacheLoader.list(consigneeDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, Consignee> map(Set<Long> idSet) {

        return CacheLoader.map(consigneeDaoMysqlImpl, idSet);
    }

    @Override
    public Page<Consignee> page(int start, int count) {

        return consigneeDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<Consignee> page(ConsigneeQuery query, int start, int count) {

        return consigneeDaoMysqlImpl.page(query, start, count);
    }

    public List<Consignee> listAll() {

        return consigneeDaoMysqlImpl.listAll();
    }

    @Override
    public List<Consignee> listByUser(Long userId) {

        return consigneeDaoMysqlImpl.listByUser(userId);
    }
}
