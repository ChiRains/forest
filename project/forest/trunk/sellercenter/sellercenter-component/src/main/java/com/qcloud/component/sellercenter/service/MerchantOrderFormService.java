package com.qcloud.component.sellercenter.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.model.MerchantOrderForm;
import com.qcloud.component.sellercenter.model.query.MerchantOrderFormQuery;

public interface MerchantOrderFormService {

    public boolean add(MerchantOrderForm merchantOrderForm);

    public MerchantOrderForm get(Long id);

    MerchantOrderForm getBySubOrder(Long merchantId, Long subOrderId);

    public boolean delete(Long id);

    public boolean update(MerchantOrderForm merchantOrderForm);

    public Page<MerchantOrderForm> page(MerchantOrderFormQuery query, long merchantId, int start, int count);

    public Page<MerchantOrderForm> page(MerchantOrderFormQuery query, long merchantId, long storeId, int start, int count);

    public List<MerchantOrderForm> listAll();

    public List<MerchantOrderForm> reportForm4merchant(Long merchantId, String startDate, String endDate);

    public List<MerchantOrderForm> reportForm4store(Long merchantId, Long storeId, String startDate, String endDate);

    public List<MerchantOrderForm> list4Store(Long merchantId, Long storeId, int state, int start, int count);

    public MerchantOrderForm get(long orderId, long merchantId, long storeId);
}
