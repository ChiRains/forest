package com.qcloud.component.my.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyToEvaluationDao;
import com.qcloud.component.my.model.MyToEvaluation;
import com.qcloud.component.my.model.query.MyToEvaluationQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MyToEvaluationDaoRedisImpl implements MyToEvaluationDao {

    // @Resource(name = "redis-my")
    // private Redis redis;
    @Override
    public boolean add(MyToEvaluation myToEvaluation) {

        throw new NotImplementedException();
    }

    @Override
    public MyToEvaluation get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MyToEvaluation myToEvaluation) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyToEvaluation> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyToEvaluation> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyToEvaluation> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyToEvaluation> page(MyToEvaluationQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyToEvaluation> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<MyToEvaluation> listByUser(long userId, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyToEvaluation> listByUserAndOrder(long userId, long subOrderId, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public int countByUserAndOrder(long userId, long subOrderId) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyToEvaluation> listByUserAndOrderId(long userId, long orderId) {

        throw new NotImplementedException();
    }
}
