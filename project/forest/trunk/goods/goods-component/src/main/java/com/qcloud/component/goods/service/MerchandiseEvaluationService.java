package com.qcloud.component.goods.service;

import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.model.key.TypeEnum.StarLevelType;
import com.qcloud.component.goods.model.query.MerchandiseEvaluationQuery;
import com.qcloud.pirates.data.Page;

public interface MerchandiseEvaluationService {

    public MerchandiseEvaluation get(long evaluationId, long merchandiseId);

    public boolean add(MerchandiseEvaluation merchandiseEvaluation);

    public boolean evaluate(long toEvaluationId, long userId, String content, int star);

    public boolean delete(Long id, Long merchandiseId);

    public boolean update(MerchandiseEvaluation merchandiseEvaluation);

    public Page<MerchandiseEvaluation> page(MerchandiseEvaluationQuery query, int start, int count);

    public Page<MerchandiseEvaluation> page(long merchandiseId, StarLevelType starLevelType, int start, int count);
    // /**
    // * 获取评论表数据
    // * @param idList obj[0]=评价表id,obj[1]=商品档案id
    // * @return
    // */
    // public List<MerchandiseEvaluation> getListByMerchandiseIds(List<Long[]> idList);
    // /**
    // * 获得商品差评、中评、好评或者全部评价数
    // * @param merchandiseId
    // * @param starLevelType
    // * @return
    // */
    // public EvaluationcentMerchandiseEntity getQEvaluationcentMerchandise(Long merchandiseId);
}
