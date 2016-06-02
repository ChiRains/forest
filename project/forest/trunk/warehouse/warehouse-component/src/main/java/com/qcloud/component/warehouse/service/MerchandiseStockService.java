package com.qcloud.component.warehouse.service;

import com.qcloud.component.warehouse.model.MerchandiseStock;
import com.qcloud.component.warehouse.model.query.MerchandiseStockQuery;
import com.qcloud.pirates.data.Page;
import java.util.HashMap;
import java.util.List;

public interface MerchandiseStockService {

    public boolean add(MerchandiseStock merchandiseStock);

    public MerchandiseStock get(Long id);

    public boolean delete(Long id);

    public boolean addStock(Long id, int number);

    public boolean reduceStock(Long id, int number);

    public Page<MerchandiseStock> page(MerchandiseStockQuery query, int start, int count);

    public List<MerchandiseStock> listAll();

    public MerchandiseStock get(HashMap where);

    public List<MerchandiseStock> list(HashMap where);

    public Page<MerchandiseStock> page(HashMap where, int start, int size);
}
