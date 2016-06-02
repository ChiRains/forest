package com.qcloud.component.warehouse.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.warehouse.model.MerchandiseStockState;
import com.qcloud.component.warehouse.model.query.MerchandiseStockStateQuery;

public interface MerchandiseStockStateDao extends ISimpleDao<MerchandiseStockState, Long> {

    public boolean add(MerchandiseStockState merchandiseStockState);

    public MerchandiseStockState get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseStockState merchandiseStockState);

    public List<MerchandiseStockState> list(List<Long> idList);

    public Map<Long, MerchandiseStockState> map(Set<Long> idSet);

    public Page<MerchandiseStockState> page(MerchandiseStockStateQuery query, int start, int size);

    public List<MerchandiseStockState> listAll();

    public List<MerchandiseStockState> listAll(Map<String, Object> map);
}
