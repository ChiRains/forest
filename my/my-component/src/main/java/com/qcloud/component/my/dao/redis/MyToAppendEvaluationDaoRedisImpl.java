package com.qcloud.component.my.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.my.dao.MyToAppendEvaluationDao;
import com.qcloud.component.my.model.MyToAppendEvaluation;
import com.qcloud.component.my.model.query.MyToAppendEvaluationQuery;

@Repository
public class MyToAppendEvaluationDaoRedisImpl implements MyToAppendEvaluationDao {

    // @Resource(name = "redis-my")
    // private Redis redis;
    @Override
    public boolean add(MyToAppendEvaluation myToAppendEvaluation) {

        throw new NotImplementedException();
    }

    @Override
    public MyToAppendEvaluation get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MyToAppendEvaluation myToAppendEvaluation) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyToAppendEvaluation> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MyToAppendEvaluation> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    public List<MyToAppendEvaluation> listByOrderId(Long orderId) {

        throw new NotImplementedException();
    }

    public List<MyToAppendEvaluation> listByUserId(Long userId) {

        throw new NotImplementedException();
    }

    public List<MyToAppendEvaluation> listByUnifiedMerchandiseId(Long unifiedMerchandiseId) {

        throw new NotImplementedException();
    }

    public List<MyToAppendEvaluation> listBySubOrderId(Long subOrderId) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyToAppendEvaluation> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MyToAppendEvaluation> page(MyToAppendEvaluationQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MyToAppendEvaluation> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<MyToAppendEvaluation> listAppendEvaluation(Long userId, Long orderId) {

        throw new NotImplementedException();
    }
}
