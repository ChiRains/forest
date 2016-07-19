package com.qcloud.component.my.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.my.model.MyToAppendEvaluation;
import com.qcloud.component.my.model.query.MyToAppendEvaluationQuery;

public interface MyToAppendEvaluationService {

    public boolean add(MyToAppendEvaluation myToAppendEvaluation);

    public MyToAppendEvaluation get(Long id);

    public boolean delete(Long id);

    public boolean update(MyToAppendEvaluation myToAppendEvaluation);

    public List<MyToAppendEvaluation> listByOrderId(Long orderId);

    public List<MyToAppendEvaluation> listByUserId(Long userId);

    public List<MyToAppendEvaluation> listByUnifiedMerchandiseId(Long unifiedMerchandiseId);

    public List<MyToAppendEvaluation> listBySubOrderId(Long subOrderId);

    public Page<MyToAppendEvaluation> page(MyToAppendEvaluationQuery query, int start, int count);

    public List<MyToAppendEvaluation> listAll();

    public List<MyToAppendEvaluation> listAppendEvaluation(Long userId, Long orderId);
}
