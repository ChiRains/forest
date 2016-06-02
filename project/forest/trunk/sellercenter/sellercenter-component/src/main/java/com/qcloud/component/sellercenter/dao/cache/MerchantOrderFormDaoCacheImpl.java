package com.qcloud.component.sellercenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.MerchantOrderFormDao;
import com.qcloud.component.sellercenter.model.MerchantOrderForm;
import com.qcloud.component.sellercenter.model.query.MerchantOrderFormQuery;

@Repository
public class MerchantOrderFormDaoCacheImpl implements MerchantOrderFormDao {

    @Autowired
    private MerchantOrderFormDao merchantOrderFormDaoMysqlImpl;

//    @Autowired
//    private MerchantOrderFormDao merchantOrderFormDaoRedisImpl;

    @Override
    public boolean add(MerchantOrderForm merchantOrderForm) {

        return merchantOrderFormDaoMysqlImpl.add(merchantOrderForm);
    }

    @Override
    public MerchantOrderForm get(Long id) {

        return merchantOrderFormDaoMysqlImpl.get(id);
//        return CacheLoader.get(merchantOrderFormDaoRedisImpl, merchantOrderFormDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return merchantOrderFormDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MerchantOrderForm merchantOrderForm) {

        return merchantOrderFormDaoMysqlImpl.update(merchantOrderForm);
    }

    @Override
    public List<MerchantOrderForm> list(List<Long> idList) {

        return CacheLoader.list(merchantOrderFormDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, MerchantOrderForm> map(Set<Long> idSet) {

        return CacheLoader.map(merchantOrderFormDaoMysqlImpl, idSet);
    }

    @Override
    public Page<MerchantOrderForm> page(int start, int count) {

        return merchantOrderFormDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MerchantOrderForm> page(MerchantOrderFormQuery query, long merchantId, int start, int count) {

        return merchantOrderFormDaoMysqlImpl.page(query, merchantId, start, count);
    }

    public List<MerchantOrderForm> listAll() {

        return merchantOrderFormDaoMysqlImpl.listAll();
    }

    @Override
    public MerchantOrderForm getBySubOrder(Long merchantId, Long subOrderId) {

        return merchantOrderFormDaoMysqlImpl.getBySubOrder(merchantId, subOrderId);
    }

    @Override
    public Page<MerchantOrderForm> page(MerchantOrderFormQuery query, long merchantId, long storeId, int start, int count) {

        return merchantOrderFormDaoMysqlImpl.page(query, merchantId, storeId, start, count);
    }

    @Override
    public List<MerchantOrderForm> reportForm4merchant(Long merchantId, String startDate, String endDate) {

        return merchantOrderFormDaoMysqlImpl.reportForm4merchant(merchantId, startDate, endDate);
    }

    @Override
    public List<MerchantOrderForm> reportForm4store(Long merchantId, Long storeId, String startDate, String endDate) {

        return merchantOrderFormDaoMysqlImpl.reportForm4store(merchantId, storeId, startDate, endDate);
    }

    @Override
    public MerchantOrderForm get(long orderId, long merchantId, long storeId) {

        return merchantOrderFormDaoMysqlImpl.get(orderId, merchantId, storeId);
    }

    @Override
    public List<MerchantOrderForm> list4Store(Long merchantId, Long storeId, int state, int start, int count) {

        return merchantOrderFormDaoMysqlImpl.list4Store(merchantId, storeId, state, start, count);
    }
}
