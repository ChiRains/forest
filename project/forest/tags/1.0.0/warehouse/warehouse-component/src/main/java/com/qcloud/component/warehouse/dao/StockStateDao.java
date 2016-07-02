package com.qcloud.component.warehouse.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.warehouse.model.StockState;
import com.qcloud.component.warehouse.model.query.StockStateQuery;

public interface StockStateDao extends ISimpleDao<StockState, Long> {

    public boolean add(StockState stockState);

    public StockState get(Long id);

    public boolean delete(Long id);

    public boolean update(StockState stockState);

    public List<StockState> list(List<Long> idList);

    public Map<Long, StockState> map(Set<Long> idSet);

    public Page<StockState> page(StockStateQuery query, int start, int size);

    public List<StockState> listAll();

    public List<StockState> listAll(Map<String, Object> map);
}
