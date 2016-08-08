package com.qcloud.project.forest.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.IntegralOrder;
import com.qcloud.project.forest.model.query.IntegralOrderQuery;

public interface IntegralOrderService {

    public boolean add(IntegralOrder integralOrder);

    public IntegralOrder get(Long id);

    public boolean delete(Long id);

    public boolean update(IntegralOrder integralOrder);

    public Page<IntegralOrder> page(IntegralOrderQuery query, int start, int count);

    public List<IntegralOrder> listAll();

    public IntegralOrder order(Long unifiedMerchandiseId, Long userId, Long consigneeId);

    public List<IntegralOrder> listByUser(long userId, int state, int start, int size);

    public List<IntegralOrder> listByUserAndFront(long userId, int state, int start, int size);

    public int countByUser(long userId, int state);

    public int countByUserAndFront(long userId, int state);

    public IntegralOrder getByOrder(long orderId);
}
