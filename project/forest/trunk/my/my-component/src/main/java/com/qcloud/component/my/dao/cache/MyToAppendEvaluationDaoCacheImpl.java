package com.qcloud.component.my.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.my.dao.MyToAppendEvaluationDao;
import com.qcloud.component.my.model.MyToAppendEvaluation;
import com.qcloud.component.my.model.query.MyToAppendEvaluationQuery;

@Repository
public class MyToAppendEvaluationDaoCacheImpl implements MyToAppendEvaluationDao {

    @Autowired
    private MyToAppendEvaluationDao myToAppendEvaluationDaoMysqlImpl;

    @Autowired
    private MyToAppendEvaluationDao myToAppendEvaluationDaoRedisImpl;

    @Override
    public boolean add(MyToAppendEvaluation myToAppendEvaluation) {

        return myToAppendEvaluationDaoMysqlImpl.add(myToAppendEvaluation);
    }

    @Override
    public MyToAppendEvaluation get(Long id) {

        return myToAppendEvaluationDaoMysqlImpl.get(id);
        // return CacheLoader.get(myToAppendEvaluationDaoRedisImpl, myToAppendEvaluationDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return myToAppendEvaluationDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MyToAppendEvaluation myToAppendEvaluation) {

        return myToAppendEvaluationDaoMysqlImpl.update(myToAppendEvaluation);
    }

    @Override
    public List<MyToAppendEvaluation> list(List<Long> idList) {

        return CacheLoader.list(myToAppendEvaluationDaoRedisImpl, myToAppendEvaluationDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MyToAppendEvaluation> map(Set<Long> idSet) {

        return CacheLoader.map(myToAppendEvaluationDaoRedisImpl, myToAppendEvaluationDaoMysqlImpl, idSet);
    }

    public List<MyToAppendEvaluation> listByOrderId(Long orderId) {

        return myToAppendEvaluationDaoMysqlImpl.listByOrderId(orderId);
    }

    public List<MyToAppendEvaluation> listByUserId(Long userId) {

        return myToAppendEvaluationDaoMysqlImpl.listByUserId(userId);
    }

    public List<MyToAppendEvaluation> listByUnifiedMerchandiseId(Long unifiedMerchandiseId) {

        return myToAppendEvaluationDaoMysqlImpl.listByUnifiedMerchandiseId(unifiedMerchandiseId);
    }

    public List<MyToAppendEvaluation> listBySubOrderId(Long subOrderId) {

        return myToAppendEvaluationDaoMysqlImpl.listBySubOrderId(subOrderId);
    }

    @Override
    public Page<MyToAppendEvaluation> page(int start, int count) {

        return myToAppendEvaluationDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MyToAppendEvaluation> page(MyToAppendEvaluationQuery query, int start, int count) {

        return myToAppendEvaluationDaoMysqlImpl.page(query, start, count);
    }

    public List<MyToAppendEvaluation> listAll() {

        return myToAppendEvaluationDaoMysqlImpl.listAll();
    }

    @Override
    public List<MyToAppendEvaluation> listAppendEvaluation(Long userId, Long orderId) {

        return myToAppendEvaluationDaoMysqlImpl.listAppendEvaluation(userId, orderId);
    }
}
