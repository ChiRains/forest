package com.qcloud.project.forest.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ForestOrderDao;
import com.qcloud.project.forest.model.ForestOrder;
import com.qcloud.project.forest.model.query.ForestOrderQuery;

@Repository
public class ForestOrderDaoCacheImpl implements ForestOrderDao {

    @Autowired
    private ForestOrderDao forestOrderDaoMysqlImpl;

    @Autowired
    private ForestOrderDao forestOrderDaoRedisImpl;

    @Override
    public boolean add(ForestOrder forestOrder) {

        return forestOrderDaoMysqlImpl.add(forestOrder);
    }

    @Override
    public ForestOrder get(Long id) {

        return forestOrderDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return forestOrderDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(ForestOrder forestOrder) {

        return forestOrderDaoMysqlImpl.update(forestOrder);
    }

    @Override
    public List<ForestOrder> list(List<Long> idList) {

        return CacheLoader.list(forestOrderDaoRedisImpl, forestOrderDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, ForestOrder> map(Set<Long> idSet) {

        return CacheLoader.map(forestOrderDaoRedisImpl, forestOrderDaoMysqlImpl, idSet);
    }

    @Override
    public Page<ForestOrder> page(int start, int count) {

        return forestOrderDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<ForestOrder> page(ForestOrderQuery query, int start, int count) {

        return forestOrderDaoMysqlImpl.page(query, start, count);
    }

    public List<ForestOrder> listAll() {

        return forestOrderDaoMysqlImpl.listAll();
    }
}
