package com.qcloud.component.warehouse.core;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.warehouse.WarehouseClient;
import com.qcloud.component.warehouse.exception.WarehouseException;
import com.qcloud.component.warehouse.model.MerchandiseStock;
import com.qcloud.component.warehouse.service.MerchandiseStockService;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class WarehouseClientImpl implements WarehouseClient {

    @Autowired
    MerchandiseStockService merchandiseStockService;

    @Override
    public boolean reduce(Long merchantId, Long storeId, Long unifiedMerchandiseId, int number) {

        HashMap where = new HashMap();
        where.put("unifiedMerchandiseId", unifiedMerchandiseId);
        where.put("storeId", storeId);
        where.put("merchantId", merchantId);
        MerchandiseStock merchandiseStock = merchandiseStockService.get(where);
        AssertUtil.assertNotNull(merchandiseStock, "请添加门店对应库存.");
        return merchandiseStockService.reduceStock(merchandiseStock.getId(), number);
    }

    @Override
    public int getStock(Long merchantId, Long storeId, Long unifiedMerchandiseId) {

        int stock = 0;
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("merchantId", merchantId);
        map.put("storeId", storeId);
        map.put("unifiedMerchandiseId", unifiedMerchandiseId);
        MerchandiseStock merchandiseStock = merchandiseStockService.get(map);
        if (merchandiseStock != null) {
            stock = merchandiseStock.getStock();
        }
        return stock;
    }
}