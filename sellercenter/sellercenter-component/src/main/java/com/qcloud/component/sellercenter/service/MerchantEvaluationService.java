package com.qcloud.component.sellercenter.service;

import java.util.List;
import com.qcloud.component.sellercenter.model.MerchantEvaluation;
import com.qcloud.component.sellercenter.model.query.MerchantEvaluationQuery;
import com.qcloud.pirates.data.Page;

public interface MerchantEvaluationService {

    public boolean add(MerchantEvaluation merchantEvaluation);

    public MerchantEvaluation get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchantEvaluation merchantEvaluation);

    public Page<MerchantEvaluation> page(MerchantEvaluationQuery query, int start, int count);

    public List<MerchantEvaluation> listAll();

    public MerchantEvaluation get(long evaluationId, long merchantId);

    public boolean delete(long evaluationId, long merchantId);
}
