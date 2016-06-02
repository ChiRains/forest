package com.qcloud.component.sellercenter.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.dao.MerchantOrderFormDao;
import com.qcloud.component.sellercenter.model.MerchantOrderForm;
import com.qcloud.component.sellercenter.service.MerchantOrderFormService;
import com.qcloud.component.sellercenter.model.query.MerchantOrderFormQuery;

@Service
public class MerchantOrderFormServiceImpl implements MerchantOrderFormService {

    @Autowired
    private MerchantOrderFormDao merchantOrderFormDao;

    @Autowired
    private AutoIdGenerator      autoIdGenerator;

    private static final String  ID_KEY = "sellercenter_merchant_order_form";

    @Override
    public boolean add(MerchantOrderForm merchantOrderForm) {

        long id = autoIdGenerator.get(ID_KEY);
        merchantOrderForm.setId(id);
        return merchantOrderFormDao.add(merchantOrderForm);
    }

    @Override
    public MerchantOrderForm get(Long id) {

        return merchantOrderFormDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchantOrderFormDao.delete(id);
    }

    @Override
    public boolean update(MerchantOrderForm merchantOrderForm) {

        return merchantOrderFormDao.update(merchantOrderForm);
    }

    @Override
    public Page<MerchantOrderForm> page(MerchantOrderFormQuery query, long merchantId, int start, int count) {

        return merchantOrderFormDao.page(query, merchantId, start, count);
    }

    public List<MerchantOrderForm> listAll() {

        return merchantOrderFormDao.listAll();
    }

    @Override
    public MerchantOrderForm getBySubOrder(Long merchantId, Long subOrderId) {

        return merchantOrderFormDao.getBySubOrder(merchantId, subOrderId);
    }

    @Override
    public Page<MerchantOrderForm> page(MerchantOrderFormQuery query, long merchantId, long storeId, int start, int count) {

        return merchantOrderFormDao.page(query, merchantId, storeId, start, count);
    }

    @Override
    public List<MerchantOrderForm> reportForm4merchant(Long merchantId, String startDate, String endDate) {

        return merchantOrderFormDao.reportForm4merchant(merchantId, startDate, endDate);
    }

    @Override
    public List<MerchantOrderForm> reportForm4store(Long merchantId, Long storeId, String startDate, String endDate) {

        return merchantOrderFormDao.reportForm4store(merchantId, storeId, startDate, endDate);
    }

    @Override
    public MerchantOrderForm get(long orderId, long merchantId, long storeId) {

        return merchantOrderFormDao.get(orderId, merchantId, storeId);
    }

    @Override
    public List<MerchantOrderForm> list4Store(Long merchantId, Long storeId, int state, int start, int count) {

        return merchantOrderFormDao.list4Store(merchantId, storeId, state, start, count);
    }
}
