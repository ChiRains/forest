package com.qcloud.project.forest.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ExpressQueryHistoryDao;
import com.qcloud.project.forest.model.ExpressQueryHistory;
import com.qcloud.project.forest.model.query.ExpressQueryHistoryQuery;

@Repository
public class ExpressQueryHistoryDaoCacheImpl implements ExpressQueryHistoryDao {

    @Autowired
    private ExpressQueryHistoryDao expressQueryHistoryDaoMysqlImpl;

    @Autowired
    private ExpressQueryHistoryDao expressQueryHistoryDaoRedisImpl;

    @Override
    public boolean add(ExpressQueryHistory expressQueryHistory) {

        return expressQueryHistoryDaoMysqlImpl.add(expressQueryHistory);
    }

    @Override
    public ExpressQueryHistory get(Long id) {

        return CacheLoader.get(expressQueryHistoryDaoRedisImpl, expressQueryHistoryDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return expressQueryHistoryDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(ExpressQueryHistory expressQueryHistory) {

        return expressQueryHistoryDaoMysqlImpl.update(expressQueryHistory);
    }

    @Override
    public List<ExpressQueryHistory> list(List<Long> idList) {

        return CacheLoader.list(expressQueryHistoryDaoRedisImpl, expressQueryHistoryDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, ExpressQueryHistory> map(Set<Long> idSet) {

        return CacheLoader.map(expressQueryHistoryDaoRedisImpl, expressQueryHistoryDaoMysqlImpl, idSet);
    }

    public List<ExpressQueryHistory> listByUserId(String userId) {

        return expressQueryHistoryDaoMysqlImpl.listByUserId(userId);
    }

    @Override
    public Page<ExpressQueryHistory> page(int start, int count) {

        return expressQueryHistoryDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<ExpressQueryHistory> page(ExpressQueryHistoryQuery query, int start, int count) {

        return expressQueryHistoryDaoMysqlImpl.page(query, start, count);
    }

    public List<ExpressQueryHistory> listAll() {

        return expressQueryHistoryDaoMysqlImpl.listAll();
    }

    @Override
    public ExpressQueryHistory getByUserIdAndExpressNum(ExpressQueryHistoryQuery query) {

        return expressQueryHistoryDaoMysqlImpl.getByUserIdAndExpressNum(query);
    }
}
