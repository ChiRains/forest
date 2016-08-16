package com.qcloud.component.goods.dao;

import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.model.key.TypeEnum.StarLevelType;
import com.qcloud.component.goods.model.query.MerchandiseEvaluationQuery;
import com.qcloud.pirates.data.Page;

public interface MerchandiseEvaluationDao {

    public boolean add(MerchandiseEvaluation merchandiseEvaluation);

    public MerchandiseEvaluation get(Long evaluationId, Long merchandiseId);

    public boolean delete(Long id, Long merchandiseId);

    public boolean update(MerchandiseEvaluation merchandiseEvaluation);

    public Page<MerchandiseEvaluation> page(MerchandiseEvaluationQuery query, int start, int size);

    public Page<MerchandiseEvaluation> page(long merchandiseId, StarLevelType starLevelType, int start, int count);

    public int getEvaluationCount(Long merchandiseId, StarLevelType starLevelType);
}
