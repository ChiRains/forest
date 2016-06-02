package com.qcloud.component.sellercenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.sellercenter.dao.MerchantOrderFormDao;
import com.qcloud.component.sellercenter.model.MerchantOrderForm;
import com.qcloud.component.sellercenter.model.query.MerchantOrderFormQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MerchantOrderFormDaoRedisImpl implements MerchantOrderFormDao {

    // @Resource(name = "redis-sellercenter")
    // private Redis redis;
    @Override
    public boolean add(MerchantOrderForm merchantOrderForm) {

        throw new NotImplementedException();
    }

    @Override
    public MerchantOrderForm get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MerchantOrderForm merchantOrderForm) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchantOrderForm> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchantOrderForm> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchantOrderForm> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchantOrderForm> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchantOrderForm> page(MerchantOrderFormQuery query, long merchantId, int start, int size) {

        throw new NotImplementedException();
    }

    @Override
    public MerchantOrderForm getBySubOrder(Long merchantId, Long subOrderId) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchantOrderForm> page(MerchantOrderFormQuery query, long merchantId, long storeId, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchantOrderForm> reportForm4merchant(Long merchantId, String startDate, String endDate) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchantOrderForm> reportForm4store(Long merchantId, Long storeId, String startDate, String endDate) {

        throw new NotImplementedException();
    }

    @Override
    public MerchantOrderForm get(long orderId, long merchantId, long storeId) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchantOrderForm> list4Store(Long merchantId, Long storeId, int state, int start, int count) {
       
        throw new NotImplementedException();
    }
}
