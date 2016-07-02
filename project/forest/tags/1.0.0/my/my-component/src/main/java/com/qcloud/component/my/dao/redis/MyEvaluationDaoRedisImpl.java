package com.qcloud.component.my.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.MyEvaluationDao;
import com.qcloud.component.my.model.MyEvaluation;
import com.qcloud.component.my.model.query.MyEvaluationQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MyEvaluationDaoRedisImpl implements MyEvaluationDao {

    // @Resource(name = "redis-my")
    // private Redis redis;
    @Override
    public boolean add(MyEvaluation myEvaluation) {

        throw new NotImplementedException();
    }

    @Override
    public MyEvaluation get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MyEvaluation myEvaluation) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyEvaluation> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyEvaluation> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyEvaluation> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyEvaluation> page(MyEvaluationQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyEvaluation> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public MyEvaluation getByOrderItemDetailId(long userId, long orderItemDetailId) {

        throw new NotImplementedException();
    }

//    @Override
//    public List<MyEvaluation> listByMap(Map<String, Object> map) {
//
//        throw new NotImplementedException();
//    }
}
