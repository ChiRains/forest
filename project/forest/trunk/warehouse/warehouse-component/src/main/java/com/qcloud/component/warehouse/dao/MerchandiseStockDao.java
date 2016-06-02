package com.qcloud.component.warehouse.dao;

import com.qcloud.component.warehouse.model.MerchandiseStock;
import com.qcloud.component.warehouse.model.query.MerchandiseStockQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MerchandiseStockDao extends ISimpleDao<MerchandiseStock, Long> {

    public boolean add(MerchandiseStock merchandiseStock);

    public MerchandiseStock get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseStock merchandiseStock);

    public List<MerchandiseStock> list(List<Long> idList);

    public Map<Long, MerchandiseStock> map(Set<Long> idSet);

    public Page<MerchandiseStock> page(MerchandiseStockQuery query, int start, int size);

    public List<MerchandiseStock> listAll();

    public MerchandiseStock get(HashMap where);

    public List<MerchandiseStock> list(HashMap where);

    public Page<MerchandiseStock> page(HashMap where, int start, int size);
}
