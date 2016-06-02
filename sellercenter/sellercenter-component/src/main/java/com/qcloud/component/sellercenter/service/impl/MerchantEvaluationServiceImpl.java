package com.qcloud.component.sellercenter.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.sellercenter.dao.MerchantEvaluationDao;
import com.qcloud.component.sellercenter.model.MerchantEvaluation;
import com.qcloud.component.sellercenter.model.query.MerchantEvaluationQuery;
import com.qcloud.component.sellercenter.service.MerchantEvaluationService;
import com.qcloud.pirates.data.Page;

@Service
public class MerchantEvaluationServiceImpl implements MerchantEvaluationService {

    @Autowired
    private MerchantEvaluationDao merchantEvaluationDao;

    @Autowired
    private AutoIdGenerator       autoIdGenerator;

    private static final String   ID_KEY = "sellercenter_merchant_evaluation";

    @Override
    public boolean add(MerchantEvaluation merchantEvaluation) {

        long id = autoIdGenerator.get(ID_KEY);
        merchantEvaluation.setId(id);
        return merchantEvaluationDao.add(merchantEvaluation);
    }

    @Override
    public MerchantEvaluation get(Long id) {

        return merchantEvaluationDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchantEvaluationDao.delete(id);
    }

    @Override
    public boolean update(MerchantEvaluation merchantEvaluation) {

        return merchantEvaluationDao.update(merchantEvaluation);
    }

    @Override
    public Page<MerchantEvaluation> page(MerchantEvaluationQuery query, int start, int count) {

        return merchantEvaluationDao.page(query, start, count);
    }

    public List<MerchantEvaluation> listAll() {

        return merchantEvaluationDao.listAll();
    }

    @Override
    public MerchantEvaluation get(long evaluationId, long merchantId) {

        return merchantEvaluationDao.get(evaluationId, merchantId);
    }

    @Override
    public boolean delete(long evaluationId, long merchantId) {

        return merchantEvaluationDao.delete(evaluationId, merchantId);
    }
}
