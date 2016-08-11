package com.qcloud.component.commoditycenter.dao.cache;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.commoditycenter.dao.MerchandiseEvaluationDao;
import com.qcloud.component.commoditycenter.model.MerchandiseEvaluation;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.StarLevelType;
import com.qcloud.component.commoditycenter.model.query.MerchandiseEvaluationQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MerchandiseEvaluationDaoCacheImpl implements MerchandiseEvaluationDao {

    @Autowired
    private MerchandiseEvaluationDao merchandiseEvaluationDaoMysqlImpl;

    @Override
    public boolean add(MerchandiseEvaluation merchandiseEvaluation) {

        return merchandiseEvaluationDaoMysqlImpl.add(merchandiseEvaluation);
    }

    @Override
    public MerchandiseEvaluation get(Long evaluationId, Long merchandiseId) {

        return merchandiseEvaluationDaoMysqlImpl.get(evaluationId, merchandiseId);
        // return CacheLoader.get(merchandiseEvaluationDaoRedisImpl, merchandiseEvaluationDaoMysqlImpl, merchandiseId);
    }

    @Override
    public boolean delete(Long id, Long merchandiseId)  {

        return merchandiseEvaluationDaoMysqlImpl.delete(id, merchandiseId);
    }

    @Override
    public boolean update(MerchandiseEvaluation merchandiseEvaluation) {

        return merchandiseEvaluationDaoMysqlImpl.update(merchandiseEvaluation);
    }

    @Override
    public Page<MerchandiseEvaluation> page(MerchandiseEvaluationQuery query, int start, int count) {

        return merchandiseEvaluationDaoMysqlImpl.page(query, start, count);
    }

    @Override
    public Page<MerchandiseEvaluation> page(long merchandiseId, StarLevelType starLevelType, int start, int count) {

        return merchandiseEvaluationDaoMysqlImpl.page(merchandiseId, starLevelType, start, count);
    }
}