package com.qcloud.component.warehouse.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.warehouse.dao.MerchandiseStockDao;
import com.qcloud.component.warehouse.exception.WarehouseException;
import com.qcloud.component.warehouse.model.MerchandiseStock;
import com.qcloud.component.warehouse.model.query.MerchandiseStockQuery;
import com.qcloud.component.warehouse.service.MerchandiseStockService;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class MerchandiseStockServiceImpl implements MerchandiseStockService {

    @Autowired
    private MerchandiseStockDao   merchandiseStockDao;

    @Autowired
    private AutoIdGenerator       autoIdGenerator;

    private static final String   ID_KEY = "warehouse_merchandise_stock";

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Transactional
    @Override
    public boolean add(MerchandiseStock merchandiseStock) {

        long id = autoIdGenerator.get(ID_KEY);
        merchandiseStock.setId(id);
        boolean result = merchandiseStockDao.add(merchandiseStock);
        AssertUtil.assertTrue(result, "更新库存失败1.");
        if (result) {
            result = commoditycenterClient.updateOnlineStock(merchandiseStock.getUnifiedMerchandiseId(), merchandiseStock.getStock());
            AssertUtil.assertTrue(result, "更新库存失败2.");
        }
        return result;
    }

    @Override
    public MerchandiseStock get(Long id) {

        return merchandiseStockDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchandiseStockDao.delete(id);
    }

    // @Transactional
    // @Override
    // public boolean update(MerchandiseStock merchandiseStock) {
    //
    // MerchandiseStock ms = get(merchandiseStock.getId());
    // int stock = merchandiseStock.getStock() - ms.getStock();
    // boolean result = merchandiseStockDao.update(merchandiseStock);
    // AssertUtil.assertTrue(result, "更新库存失败3.");
    // if (result) {
    // result = commoditycenterClient.updateOnlineStock(merchandiseStock.getUnifiedMerchandiseId(), stock, merchandiseStock.getPurchase(), merchandiseStock.getDiscount(), merchandiseStock.getPrice());
    // AssertUtil.assertTrue(result, "更新库存失败4.");
    // }
    // return result;
    // }
    @Override
    public Page<MerchandiseStock> page(MerchandiseStockQuery query, int start, int count) {

        return merchandiseStockDao.page(query, start, count);
    }

    @Override
    public List<MerchandiseStock> listAll() {

        return merchandiseStockDao.listAll();
    }

    @Override
    public MerchandiseStock get(HashMap where) {

        return merchandiseStockDao.get(where);
    }

    @Override
    public List<MerchandiseStock> list(HashMap where) {

        return merchandiseStockDao.list(where);
    }

    @Override
    public Page<MerchandiseStock> page(HashMap where, int start, int size) {

        return merchandiseStockDao.page(where, start, size);
    }

    @Override
    public boolean addStock(Long id, int number) {

        MerchandiseStock ms = get(id);
        ms.setStock(ms.getStock() + number);
        AssertUtil.greatZero(ms.getStock(), "库存调整后不能小于零.");
        boolean result = merchandiseStockDao.update(ms);
        AssertUtil.assertTrue(result, "更新库存失败3.");
        if (result) {
            result = commoditycenterClient.updateOnlineStock(ms.getUnifiedMerchandiseId(), number);
            AssertUtil.assertTrue(result, "更新库存失败4.");
        }
        return result;
    }

    @Override
    public boolean reduceStock(Long id, int number) {

        MerchandiseStock ms = get(id);
        if (ms.getStock() > number) {
            ms.setStock(ms.getStock() - number);
            boolean result = merchandiseStockDao.update(ms);
            AssertUtil.assertTrue(result, "更新库存失败5.");
            return result;
        } else {
            throw new WarehouseException("商品库存不足." + ms.getStoreId() + " " + id);
        }
    }
}
