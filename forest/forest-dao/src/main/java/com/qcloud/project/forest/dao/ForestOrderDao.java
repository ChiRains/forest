package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.ForestOrder;
import com.qcloud.project.forest.model.query.ForestOrderQuery;

public interface ForestOrderDao extends ISimpleDao<ForestOrder, Long> {

    public boolean add(ForestOrder forestOrder);

    public ForestOrder get(Long id);

    public boolean delete(Long id);

    public boolean update(ForestOrder forestOrder);

    public List<ForestOrder> list(List<Long> idList);

    public Map<Long, ForestOrder> map(Set<Long> idSet);

    public Page<ForestOrder> page(ForestOrderQuery query, int start, int size);

    public List<ForestOrder> listAll();

    public ForestOrder getByOrder(long orderId);
}
