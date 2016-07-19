package com.qcloud.component.my.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.my.dao.MyToAppendEvaluationDao;
import com.qcloud.component.my.model.MyToAppendEvaluation;
import com.qcloud.component.my.service.MyToAppendEvaluationService;
import com.qcloud.component.my.model.query.MyToAppendEvaluationQuery;

@Service
public class MyToAppendEvaluationServiceImpl implements MyToAppendEvaluationService {

    @Autowired
    private MyToAppendEvaluationDao myToAppendEvaluationDao;

    @Autowired
    private AutoIdGenerator         autoIdGenerator;

    private static final String     ID_KEY = "my_my_to_append_evaluation";

    @Override
    public boolean add(MyToAppendEvaluation myToAppendEvaluation) {

        long id = autoIdGenerator.get(ID_KEY);
        myToAppendEvaluation.setId(id);
        return myToAppendEvaluationDao.add(myToAppendEvaluation);
    }

    @Override
    public MyToAppendEvaluation get(Long id) {

        return myToAppendEvaluationDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return myToAppendEvaluationDao.delete(id);
    }

    @Override
    public boolean update(MyToAppendEvaluation myToAppendEvaluation) {

        return myToAppendEvaluationDao.update(myToAppendEvaluation);
    }

    public List<MyToAppendEvaluation> listByOrderId(Long orderId) {

        return myToAppendEvaluationDao.listByOrderId(orderId);
    }

    public List<MyToAppendEvaluation> listByUserId(Long userId) {

        return myToAppendEvaluationDao.listByUserId(userId);
    }

    public List<MyToAppendEvaluation> listByUnifiedMerchandiseId(Long unifiedMerchandiseId) {

        return myToAppendEvaluationDao.listByUnifiedMerchandiseId(unifiedMerchandiseId);
    }

    public List<MyToAppendEvaluation> listBySubOrderId(Long subOrderId) {

        return myToAppendEvaluationDao.listBySubOrderId(subOrderId);
    }

    @Override
    public Page<MyToAppendEvaluation> page(MyToAppendEvaluationQuery query, int start, int count) {

        return myToAppendEvaluationDao.page(query, start, count);
    }

    public List<MyToAppendEvaluation> listAll() {

        return myToAppendEvaluationDao.listAll();
    }

    @Override
    public List<MyToAppendEvaluation> listAppendEvaluation(Long userId, Long orderId) {

        return myToAppendEvaluationDao.listAppendEvaluation(userId, orderId);
    }
}
