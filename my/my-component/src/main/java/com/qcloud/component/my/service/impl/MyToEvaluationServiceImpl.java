package com.qcloud.component.my.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.my.dao.MyToAppendEvaluationDao;
import com.qcloud.component.my.dao.MyToEvaluationDao;
import com.qcloud.component.my.model.MyToAppendEvaluation;
import com.qcloud.component.my.model.MyToEvaluation;
import com.qcloud.component.my.model.query.MyToEvaluationQuery;
import com.qcloud.component.my.service.MyToAppendEvaluationService;
import com.qcloud.component.my.service.MyToEvaluationService;
import com.qcloud.pirates.data.Page;

@Service
public class MyToEvaluationServiceImpl implements MyToEvaluationService {

    @Autowired
    private MyToEvaluationDao           myToEvaluationDao;

    @Autowired
    private AutoIdGenerator             autoIdGenerator;

    @Autowired
    private MyToAppendEvaluationService myToAppendEvaluationService;

    private static final String         ID_KEY = "personalcenter_my_to_evaluation";

    @Override
    public boolean add(MyToEvaluation myToEvaluation) {

        long id = autoIdGenerator.get(ID_KEY);
        myToEvaluation.setId(id);
        return myToEvaluationDao.add(myToEvaluation);
    }

    @Override
    public MyToEvaluation get(Long id) {

        return myToEvaluationDao.get(id);
    }

    @Override
    @Transactional
    public boolean synAppendEvaluation(Long toEvaluationId, Long evaluationId) {

        MyToAppendEvaluation myToAppendEvaluation = new MyToAppendEvaluation();
        MyToEvaluation mt = get(toEvaluationId);
        myToAppendEvaluation.setUserId(mt.getUserId());
        myToAppendEvaluation.setUnifiedMerchandiseId(mt.getUnifiedMerchandiseId());
        myToAppendEvaluation.setMerchandiseId(mt.getMerchandiseId());
        myToAppendEvaluation.setName(mt.getName());
        myToAppendEvaluation.setImage(mt.getImage());
        myToAppendEvaluation.setDiscount(mt.getDiscount());
        myToAppendEvaluation.setMerchantId(mt.getMerchantId());
        myToAppendEvaluation.setOrderId(mt.getOrderId());
        myToAppendEvaluation.setSubOrderId(mt.getSubOrderId());
        myToAppendEvaluation.setOrderItemId(mt.getOrderItemId());
        myToAppendEvaluation.setOrderDate(mt.getOrderDate());
        myToAppendEvaluation.setOrderNumber(mt.getOrderNumber());
        myToAppendEvaluation.setSignDate(mt.getSignDate());
        myToAppendEvaluation.setOrderItemDetailId(mt.getOrderItemDetailId());
        myToAppendEvaluation.setSpecifications(mt.getSpecifications());
        myToAppendEvaluation.setEvaluationId(evaluationId);
        // 添加待追加评论
        myToAppendEvaluationService.add(myToAppendEvaluation);
        return myToEvaluationDao.delete(toEvaluationId);
    }

    @Override
    public boolean update(MyToEvaluation myToEvaluation) {

        return myToEvaluationDao.update(myToEvaluation);
    }

    @Override
    public Page<MyToEvaluation> page(MyToEvaluationQuery query, int start, int count) {

        return myToEvaluationDao.page(query, start, count);
    }

    public List<MyToEvaluation> listAll() {

        return myToEvaluationDao.listAll();
    }

    @Override
    public List<MyToEvaluation> listByUser(long userId, int start, int count) {

        return myToEvaluationDao.listByUser(userId, start, count);
    }

    @Override
    public List<MyToEvaluation> listByUserAndOrder(long userId, long subOrderId, int start, int count) {

        return myToEvaluationDao.listByUserAndOrder(userId, subOrderId, start, count);
    }

    @Override
    public int countByUserAndOrder(long userId, long subOrderId) {

        return myToEvaluationDao.countByUserAndOrder(userId, subOrderId);
    }

    @Override
    public List<MyToEvaluation> listByUserAndOrderId(long userId, long orderId) {

        return myToEvaluationDao.listByUserAndOrderId(userId, orderId);
    }
}
