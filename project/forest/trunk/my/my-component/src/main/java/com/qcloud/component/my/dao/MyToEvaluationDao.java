package com.qcloud.component.my.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.my.model.MyToEvaluation;
import com.qcloud.component.my.model.query.MyToEvaluationQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface MyToEvaluationDao extends ISimpleDao<MyToEvaluation, Long> {

    public boolean add(MyToEvaluation myToEvaluation);

    public MyToEvaluation get(Long id);

    public boolean delete(Long id);

    public boolean update(MyToEvaluation myToEvaluation);

    public List<MyToEvaluation> list(List<Long> idList);

    public Map<Long, MyToEvaluation> map(Set<Long> idSet);

    public Page<MyToEvaluation> page(MyToEvaluationQuery query, int start, int size);

    public List<MyToEvaluation> listAll();

    public List<MyToEvaluation> listByUser(long userId, int start, int count);

    public List<MyToEvaluation> listByUserAndOrder(long userId, long subOrderId, int start, int count);

    int countByUserAndOrder(long userId, long subOrderId);
}
