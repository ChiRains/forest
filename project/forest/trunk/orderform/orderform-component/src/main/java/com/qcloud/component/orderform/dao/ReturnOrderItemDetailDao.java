package com.qcloud.component.orderform.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.orderform.model.ReturnOrderItemDetail;
import com.qcloud.component.orderform.model.query.ReturnOrderItemDetailQuery;

public interface ReturnOrderItemDetailDao extends ISimpleDao<ReturnOrderItemDetail, Long> {

    public boolean add(ReturnOrderItemDetail returnOrderItemDetail);

    public ReturnOrderItemDetail get(Long id);

    public boolean delete(Long id);

    public boolean update(ReturnOrderItemDetail returnOrderItemDetail);

    public List<ReturnOrderItemDetail> list(List<Long> idList);

    public Map<Long, ReturnOrderItemDetail> map(Set<Long> idSet);

    public Page<ReturnOrderItemDetail> page(ReturnOrderItemDetailQuery query, int start, int size);

    public List<ReturnOrderItemDetail> listAll();

    public List<ReturnOrderItemDetail> listByReturn(long returnId);
}
