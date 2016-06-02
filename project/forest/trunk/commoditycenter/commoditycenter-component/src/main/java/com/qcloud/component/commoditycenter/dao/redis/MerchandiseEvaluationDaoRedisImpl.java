package com.qcloud.component.commoditycenter.dao.redis;

import java.util.List;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.commoditycenter.dao.MerchandiseEvaluationDao;
import com.qcloud.component.commoditycenter.model.MerchandiseEvaluation;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.StarLevelType;
import com.qcloud.component.commoditycenter.model.query.MerchandiseEvaluationQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MerchandiseEvaluationDaoRedisImpl implements MerchandiseEvaluationDao {

    // @Resource(name = "redis-evaluationcenter")
    // private Redis redis;
    @Override
    public boolean add(MerchandiseEvaluation merchandiseEvaluation) {

        throw new NotImplementedException();
    }

    @Override
    public MerchandiseEvaluation get(Long evaluationId, Long merchandiseId) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id, Long merchandiseId) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MerchandiseEvaluation merchandiseEvaluation) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseEvaluation> page(MerchandiseEvaluationQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseEvaluation> page(long merchandiseId, StarLevelType starLevelType, int start, int count) {

        throw new NotImplementedException();
    }
}
