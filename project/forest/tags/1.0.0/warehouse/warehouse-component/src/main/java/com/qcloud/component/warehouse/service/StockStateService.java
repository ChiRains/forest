package com.qcloud.component.warehouse.service;

import java.util.List;
import java.util.Map;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.warehouse.model.StockState;
import com.qcloud.component.warehouse.model.query.StockStateQuery;

public interface StockStateService {

    public boolean add(StockState stockState);

    public StockState get(Long id);

    public boolean delete(Long id);

    public boolean update(StockState stockState);

    public Page<StockState> page(StockStateQuery query, int start, int count);

    public List<StockState> listAll();

    public List<StockState> listAll(Map<String, Object> map);
}
