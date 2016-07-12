package com.qcloud.project.forest.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ExpressQueryHistoryDao;
import com.qcloud.project.forest.model.ExpressQueryHistory;
import com.qcloud.project.forest.model.query.ExpressQueryHistoryQuery;

@Repository
public class ExpressQueryHistoryDaoRedisImpl implements ExpressQueryHistoryDao {

    // @Resource(name = "redis-forest")
    // private Redis redis;
    @Override
    public boolean add(ExpressQueryHistory expressQueryHistory) {

        throw new NotImplementedException();
    }

    @Override
    public ExpressQueryHistory get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(ExpressQueryHistory expressQueryHistory) {

        throw new NotImplementedException();
    }

    @Override
    public List<ExpressQueryHistory> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, ExpressQueryHistory> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<ExpressQueryHistory> listByUserId(String userId) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ExpressQueryHistory> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<ExpressQueryHistory> page(ExpressQueryHistoryQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<ExpressQueryHistory> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public ExpressQueryHistory getByUserIdAndExpressNum(ExpressQueryHistoryQuery query) {

        throw new NotImplementedException();
    }
}
