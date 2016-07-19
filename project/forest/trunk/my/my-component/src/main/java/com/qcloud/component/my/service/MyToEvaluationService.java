package com.qcloud.component.my.service;

import java.util.List;
import com.qcloud.component.my.model.MyToEvaluation;
import com.qcloud.component.my.model.query.MyToEvaluationQuery;
import com.qcloud.pirates.data.Page;

public interface MyToEvaluationService {

    public boolean add(MyToEvaluation myToEvaluation);

    public MyToEvaluation get(Long id);

    public boolean synAppendEvaluation(Long toEvaluationId, Long evaluationId);

    public boolean update(MyToEvaluation myToEvaluation);

    public Page<MyToEvaluation> page(MyToEvaluationQuery query, int start, int count);

    public List<MyToEvaluation> listAll();

    public List<MyToEvaluation> listByUser(long userId, int start, int count);

    public List<MyToEvaluation> listByUserAndOrder(long userId, long subOrderId, int start, int count);

    public int countByUserAndOrder(long userId, long subOrderId);

    public List<MyToEvaluation> listByUserAndOrderId(long userId, long orderId);
}
