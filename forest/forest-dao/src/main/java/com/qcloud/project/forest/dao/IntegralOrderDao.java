package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.IntegralOrder;
import com.qcloud.project.forest.model.query.IntegralOrderQuery;

public interface IntegralOrderDao extends ISimpleDao<IntegralOrder, Long> {

    public boolean add(IntegralOrder integralOrder);

    public IntegralOrder get(Long id);

    public boolean delete(Long id);

    public boolean update(IntegralOrder integralOrder);

    public List<IntegralOrder> list(List<Long> idList);

    public Map<Long, IntegralOrder> map(Set<Long> idSet);

    public Page<IntegralOrder> page(IntegralOrderQuery query, int start, int size);

    public List<IntegralOrder> listAll();

    public List<IntegralOrder> listByUser(long userId, int type, int start, int size);

    public int countByUser(long userId, int type);

    public IntegralOrder getByOrder(long orderId);
}
