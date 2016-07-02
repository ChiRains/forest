package com.qcloud.component.sellercenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.sellercenter.model.MerchantEvaluation;
import com.qcloud.component.sellercenter.model.query.MerchantEvaluationQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface MerchantEvaluationDao extends ISimpleDao<MerchantEvaluation, Long> {

    public boolean add(MerchantEvaluation merchantEvaluation);

    public MerchantEvaluation get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchantEvaluation merchantEvaluation);

    public List<MerchantEvaluation> list(List<Long> idList);

    public Map<Long, MerchantEvaluation> map(Set<Long> idSet);

    public Page<MerchantEvaluation> page(MerchantEvaluationQuery query, int start, int size);

    public List<MerchantEvaluation> listAll();

    public MerchantEvaluation get(long evaluationId, long merchantId);

    public boolean delete(long evaluationId, long merchantId);
}
