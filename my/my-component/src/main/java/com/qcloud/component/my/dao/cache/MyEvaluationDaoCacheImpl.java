package com.qcloud.component.my.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyEvaluationDao;
import com.qcloud.component.my.model.MyEvaluation;
import com.qcloud.component.my.model.query.MyEvaluationQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class MyEvaluationDaoCacheImpl implements MyEvaluationDao {

    @Autowired
    private MyEvaluationDao myEvaluationDaoMysqlImpl;

//    @Autowired
//    private MyEvaluationDao MyEvaluationDaoRedisImpl;

    @Override
    public boolean add(MyEvaluation myEvaluation) {

        return myEvaluationDaoMysqlImpl.add(myEvaluation);
    }

    @Override
    public MyEvaluation get(Long id) {
        return myEvaluationDaoMysqlImpl.get(id);
//        return CacheLoader.get(MyEvaluationDaoRedisImpl, MyEvaluationDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return myEvaluationDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MyEvaluation myEvaluation) {

        return myEvaluationDaoMysqlImpl.update(myEvaluation);
    }

    @Override
    public List<MyEvaluation> list(List<Long> idList) {

        return CacheLoader.list(myEvaluationDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MyEvaluation> map(Set<Long> idSet) {

        return CacheLoader.map(myEvaluationDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MyEvaluation> page(int start, int count) {

        return myEvaluationDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MyEvaluation> page(MyEvaluationQuery query, int start, int count) {

        return myEvaluationDaoMysqlImpl.page(query, start, count);
    }

    public List<MyEvaluation> listAll() {

        return myEvaluationDaoMysqlImpl.listAll();
    }

    @Override
    public MyEvaluation getByOrderItemDetailId(long userId, long orderItemDetailId) {

        return myEvaluationDaoMysqlImpl.getByOrderItemDetailId(userId, orderItemDetailId);
    }

//    @Override
//    public List<MyEvaluation> listByMap(Map<String, Object> map) {
//
//        return myEvaluationDaoMysqlImpl.listByMap(map);
//    }
}
