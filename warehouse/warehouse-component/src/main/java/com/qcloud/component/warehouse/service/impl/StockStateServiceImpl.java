package com.qcloud.component.warehouse.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.warehouse.dao.StockStateDao;
import com.qcloud.component.warehouse.model.StockState;
import com.qcloud.component.warehouse.service.StockStateService;
import com.qcloud.component.warehouse.model.query.StockStateQuery;

@Service
public class StockStateServiceImpl implements StockStateService {

    @Autowired
    private StockStateDao       stockStateDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "warehouse_stock_state";

    @Override
    public boolean add(StockState stockState) {

        long id = autoIdGenerator.get(ID_KEY);
        stockState.setId(id);
        return stockStateDao.add(stockState);
    }

    @Override
    public StockState get(Long id) {

        return stockStateDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return stockStateDao.delete(id);
    }

    @Override
    public boolean update(StockState stockState) {

        return stockStateDao.update(stockState);
    }

    @Override
    public Page<StockState> page(StockStateQuery query, int start, int count) {

        return stockStateDao.page(query, start, count);
    }

    public List<StockState> listAll() {

        return stockStateDao.listAll();
    }

    @Override
    public List<StockState> listAll(Map<String, Object> map) {

        return stockStateDao.listAll(map);
    }
}
