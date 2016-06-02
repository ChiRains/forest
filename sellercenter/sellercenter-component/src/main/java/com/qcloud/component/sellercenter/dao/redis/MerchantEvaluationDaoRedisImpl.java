package com.qcloud.component.sellercenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.sellercenter.dao.MerchantEvaluationDao;
import com.qcloud.component.sellercenter.model.MerchantEvaluation;
import com.qcloud.component.sellercenter.model.query.MerchantEvaluationQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MerchantEvaluationDaoRedisImpl implements MerchantEvaluationDao {

    // @Resource(name = "redis-evaluationcenter")
    // private Redis redis;
    @Override
    public boolean add(MerchantEvaluation merchantEvaluation) {

        throw new NotImplementedException();
    }

    @Override
    public MerchantEvaluation get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MerchantEvaluation merchantEvaluation) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchantEvaluation> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchantEvaluation> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchantEvaluation> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchantEvaluation> page(MerchantEvaluationQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchantEvaluation> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public MerchantEvaluation get(long evaluationId, long merchantId) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(long evaluationId, long merchantId) {

        throw new NotImplementedException();
    }
}
