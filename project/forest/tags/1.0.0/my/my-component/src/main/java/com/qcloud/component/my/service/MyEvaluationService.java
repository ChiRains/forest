package com.qcloud.component.my.service;

import java.util.List;
import com.qcloud.component.my.model.MyEvaluation;
import com.qcloud.component.my.model.query.MyEvaluationQuery;
import com.qcloud.pirates.data.Page;

public interface MyEvaluationService {

    public boolean add(MyEvaluation myEvaluation);

    public MyEvaluation get(Long id);

    public boolean delete(Long id);

    public boolean update(MyEvaluation myEvaluation);

    public Page<MyEvaluation> page(MyEvaluationQuery query, int start, int count);

    public List<MyEvaluation> listAll();

//    /**
//     * 评价商品,添加评价表、我的评价、商家入口三个表的内容
//     * @param merchandiseEvaluation
//     */
//    public boolean add(MerchandiseEvaluation merchandiseEvaluation, OrderItemDetail orderItemDetail, Date orderTime);

    public MyEvaluation getByOrderItemDetailId(long userId, long orderItemDetailId);

//    public List<MyEvaluation> listByMap(Map<String, Object> map);
}
