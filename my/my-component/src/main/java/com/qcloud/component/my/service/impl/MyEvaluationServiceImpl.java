package com.qcloud.component.my.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.my.dao.MyEvaluationDao;
import com.qcloud.component.my.model.MyEvaluation;
import com.qcloud.component.my.model.query.MyEvaluationQuery;
import com.qcloud.component.my.service.MyEvaluationService;
import com.qcloud.pirates.data.Page;

@Service
public class MyEvaluationServiceImpl implements MyEvaluationService {

    @Autowired
    private MyEvaluationDao   myEvaluationDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "personalcenter_my_evaluation";

    @Override
    public boolean add(MyEvaluation myEvaluation) {

        long id = autoIdGenerator.get(ID_KEY);
        myEvaluation.setId(id);
        return myEvaluationDao.add(myEvaluation);
    }

    @Override
    public MyEvaluation get(Long id) {

        return myEvaluationDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return myEvaluationDao.delete(id);
    }

    @Override
    public boolean update(MyEvaluation myEvaluation) {

        return myEvaluationDao.update(myEvaluation);
    }

    @Override
    public Page<MyEvaluation> page(MyEvaluationQuery query, int start, int count) {

        return myEvaluationDao.page(query, start, count);
    }

    public List<MyEvaluation> listAll() {

        return myEvaluationDao.listAll();
    }

    @Override
    public MyEvaluation getByOrderItemDetailId(long userId, long orderItemDetailId) {

        return myEvaluationDao.getByOrderItemDetailId(userId, orderItemDetailId);
    }

//    @Override
//    public List<MyEvaluation> listByMap(Map<String, Object> map) {
//
//        return myEvaluationDao.listByMap(map);
//    }
}
