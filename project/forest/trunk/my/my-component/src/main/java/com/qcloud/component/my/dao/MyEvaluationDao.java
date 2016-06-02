package com.qcloud.component.my.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.my.model.MyEvaluation;
import com.qcloud.component.my.model.query.MyEvaluationQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface MyEvaluationDao extends ISimpleDao<MyEvaluation, Long> {

    public boolean add(MyEvaluation myEvaluation);

    public MyEvaluation get(Long id);

    public boolean delete(Long id);

    public boolean update(MyEvaluation myEvaluation);

    public List<MyEvaluation> list(List<Long> idList);

    public Map<Long, MyEvaluation> map(Set<Long> idSet);

    public Page<MyEvaluation> page(MyEvaluationQuery query, int start, int size);

    public List<MyEvaluation> listAll();

    public MyEvaluation getByOrderItemDetailId(long userId, long orderItemDetailId);

//    public List<MyEvaluation> listByMap(Map<String, Object> map);
}
