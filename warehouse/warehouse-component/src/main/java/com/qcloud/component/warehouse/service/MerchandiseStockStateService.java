package com.qcloud.component.warehouse.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.warehouse.model.MerchandiseStockState;
import com.qcloud.component.warehouse.model.query.MerchandiseStockStateQuery;

public interface MerchandiseStockStateService {

    public boolean add(MerchandiseStockState merchandiseStockState);

    public MerchandiseStockState get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseStockState merchandiseStockState);

    public Page<MerchandiseStockState> page(MerchandiseStockStateQuery query, int start, int count);

    public List<MerchandiseStockState> listAll();

    public Page<MerchandiseStockState> page(MerchandiseStockStateQuery query, int pageNum, int count, boolean isInput);
}
