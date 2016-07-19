package com.qcloud.component.my.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.my.model.MyToAppendEvaluation;
import com.qcloud.component.my.model.query.MyToAppendEvaluationQuery;

public interface MyToAppendEvaluationDao extends ISimpleDao<MyToAppendEvaluation, Long> {

    public boolean add(MyToAppendEvaluation myToAppendEvaluation);

    public MyToAppendEvaluation get(Long id);

    public boolean delete(Long id);

    public boolean update(MyToAppendEvaluation myToAppendEvaluation);

    public List<MyToAppendEvaluation> list(List<Long> idList);

    public Map<Long, MyToAppendEvaluation> map(Set<Long> idSet);

    public Page<MyToAppendEvaluation> page(MyToAppendEvaluationQuery query, int start, int size);

    public List<MyToAppendEvaluation> listAll();

    public List<MyToAppendEvaluation> listByOrderId(Long orderId);

    public List<MyToAppendEvaluation> listByUserId(Long userId);

    public List<MyToAppendEvaluation> listByUnifiedMerchandiseId(Long unifiedMerchandiseId);

    public List<MyToAppendEvaluation> listBySubOrderId(Long subOrderId);

    public List<MyToAppendEvaluation> listAppendEvaluation(Long userId, Long orderId);
}
