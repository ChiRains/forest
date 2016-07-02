package com.qcloud.component.sellercenter.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.sellercenter.dao.MerchantEvaluationDao;
import com.qcloud.component.sellercenter.model.MerchantEvaluation;
import com.qcloud.component.sellercenter.model.query.MerchantEvaluationQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class MerchantEvaluationDaoCacheImpl implements MerchantEvaluationDao {

    @Autowired
    private MerchantEvaluationDao merchantEvaluationDaoMysqlImpl;

    // @Autowired
    // private MerchantEvaluationDao merchantEvaluationDaoRedisImpl;
    @Override
    public boolean add(MerchantEvaluation merchantEvaluation) {

        return merchantEvaluationDaoMysqlImpl.add(merchantEvaluation);
    }

    @Override
    public MerchantEvaluation get(Long id) {

        return merchantEvaluationDaoMysqlImpl.get(id);
        // return CacheLoader.get(merchantEvaluationDaoRedisImpl, merchantEvaluationDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return merchantEvaluationDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MerchantEvaluation merchantEvaluation) {

        return merchantEvaluationDaoMysqlImpl.update(merchantEvaluation);
    }

    @Override
    public List<MerchantEvaluation> list(List<Long> idList) {

        return CacheLoader.list(merchantEvaluationDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MerchantEvaluation> map(Set<Long> idSet) {

        return CacheLoader.map(merchantEvaluationDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MerchantEvaluation> page(int start, int count) {

        return merchantEvaluationDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MerchantEvaluation> page(MerchantEvaluationQuery query, int start, int count) {

        return merchantEvaluationDaoMysqlImpl.page(query, start, count);
    }

    public List<MerchantEvaluation> listAll() {

        return merchantEvaluationDaoMysqlImpl.listAll();
    }

    @Override
    public MerchantEvaluation get(long evaluationId, long merchantId) {

        return merchantEvaluationDaoMysqlImpl.get(evaluationId, merchantId);
    }

    @Override
    public boolean delete(long evaluationId, long merchantId) {

        return merchantEvaluationDaoMysqlImpl.delete(evaluationId, merchantId);
    }
}
