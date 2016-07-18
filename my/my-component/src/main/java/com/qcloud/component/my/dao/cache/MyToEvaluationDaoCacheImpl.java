package com.qcloud.component.my.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyToEvaluationDao;
import com.qcloud.component.my.model.MyToEvaluation;
import com.qcloud.component.my.model.query.MyToEvaluationQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class MyToEvaluationDaoCacheImpl implements MyToEvaluationDao {

    @Autowired
    private MyToEvaluationDao myToEvaluationDaoMysqlImpl;

    // @Autowired
    // private MyToEvaluationDao myToEvaluationDaoRedisImpl;
    @Override
    public boolean add(MyToEvaluation myToEvaluation) {

        return myToEvaluationDaoMysqlImpl.add(myToEvaluation);
    }

    @Override
    public MyToEvaluation get(Long id) {

        return myToEvaluationDaoMysqlImpl.get(id);
        // return CacheLoader.get(myToEvaluationDaoRedisImpl, myToEvaluationDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return myToEvaluationDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MyToEvaluation myToEvaluation) {

        return myToEvaluationDaoMysqlImpl.update(myToEvaluation);
    }

    @Override
    public List<MyToEvaluation> list(List<Long> idList) {

        return CacheLoader.list(myToEvaluationDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MyToEvaluation> map(Set<Long> idSet) {

        return CacheLoader.map(myToEvaluationDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MyToEvaluation> page(int start, int count) {

        return myToEvaluationDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MyToEvaluation> page(MyToEvaluationQuery query, int start, int count) {

        return myToEvaluationDaoMysqlImpl.page(query, start, count);
    }

    public List<MyToEvaluation> listAll() {

        return myToEvaluationDaoMysqlImpl.listAll();
    }

    @Override
    public List<MyToEvaluation> listByUser(long userId, int start, int count) {

        return myToEvaluationDaoMysqlImpl.listByUser(userId, start, count);
    }

    @Override
    public List<MyToEvaluation> listByUserAndOrder(long userId, long subOrderId, int start, int count) {

        return myToEvaluationDaoMysqlImpl.listByUserAndOrder(userId, subOrderId, start, count);
    }

    @Override
    public int countByUserAndOrder(long userId, long subOrderId) {

        return myToEvaluationDaoMysqlImpl.countByUserAndOrder(userId, subOrderId);
    }

    @Override
    public List<MyToEvaluation> listByUserAndOrderId(long userId, long orderId) {

        return myToEvaluationDaoMysqlImpl.listByUserAndOrderId(userId, orderId);
    }
}
