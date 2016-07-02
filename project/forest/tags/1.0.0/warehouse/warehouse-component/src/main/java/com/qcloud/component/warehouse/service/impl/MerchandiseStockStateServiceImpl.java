package com.qcloud.component.warehouse.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.warehouse.dao.MerchandiseStockStateDao;
import com.qcloud.component.warehouse.dao.StockStateDao;
import com.qcloud.component.warehouse.model.MerchandiseStockState;
import com.qcloud.component.warehouse.model.StockState;
import com.qcloud.component.warehouse.model.query.MerchandiseStockStateQuery;
import com.qcloud.component.warehouse.service.MerchandiseStockStateService;
import com.qcloud.component.warehouse.util.PageListUtil;
import com.qcloud.pirates.data.Page;

@Service
public class MerchandiseStockStateServiceImpl implements MerchandiseStockStateService {

    @Autowired
    private MerchandiseStockStateDao merchandiseStockStateDao;

    @Autowired
    private StockStateDao            stockStateDao;

    @Autowired
    private AutoIdGenerator          autoIdGenerator;

    private static final String      ID_KEY = "warehouse_merchandise_stock_state";

    @Override
    public boolean add(MerchandiseStockState merchandiseStockState) {

        long id = autoIdGenerator.get(ID_KEY);
        merchandiseStockState.setId(id);
        return merchandiseStockStateDao.add(merchandiseStockState);
    }

    @Override
    public MerchandiseStockState get(Long id) {

        return merchandiseStockStateDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseStockStateDao.delete(id);
    }

    @Override
    public boolean update(MerchandiseStockState merchandiseStockState) {

        return merchandiseStockStateDao.update(merchandiseStockState);
    }

    @Override
    public Page<MerchandiseStockState> page(MerchandiseStockStateQuery query, int start, int count) {

        return merchandiseStockStateDao.page(query, start, count);
    }

    public List<MerchandiseStockState> listAll() {

        return merchandiseStockStateDao.listAll();
    }

    @Override
    public Page<MerchandiseStockState> page(MerchandiseStockStateQuery query, int pageNum, int count, boolean isInput) {

        List<MerchandiseStockState> allList = new ArrayList<MerchandiseStockState>();
        Map<String, Object> map = new HashMap<String, Object>();
        if (isInput) {
            map.put("toStoreId", query.getStoreId());
        } else {
            map.put("formStoreId", query.getStoreId());
        }
        List<StockState> stockStateList = stockStateDao.listAll(map);
        for (StockState stockState : stockStateList) {
            map.clear();
            map.put("stockStateId", stockState.getId());
            map.put("state", query.getState());
            List<MerchandiseStockState> merchandiseStockStateList = merchandiseStockStateDao.listAll(map);
            allList.addAll(merchandiseStockStateList);
        }
        return PageListUtil.getPager(pageNum, count, allList);
    }
}
