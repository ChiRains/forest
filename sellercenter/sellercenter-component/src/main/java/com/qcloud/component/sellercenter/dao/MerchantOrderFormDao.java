package com.qcloud.component.sellercenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.sellercenter.model.MerchantOrderForm;
import com.qcloud.component.sellercenter.model.query.MerchantOrderFormQuery;

public interface MerchantOrderFormDao extends ISimpleDao<MerchantOrderForm, Long> {

    public boolean add(MerchantOrderForm merchantOrderForm);

    public MerchantOrderForm get(Long id);

    MerchantOrderForm getBySubOrder(Long merchantId, Long subOrderId);

    public boolean delete(Long id);

    public boolean update(MerchantOrderForm merchantOrderForm);

    public List<MerchantOrderForm> list(List<Long> idList);

    public Map<Long, MerchantOrderForm> map(Set<Long> idSet);

    public Page<MerchantOrderForm> page(MerchantOrderFormQuery query, long merchantId, int start, int size);

    public List<MerchantOrderForm> listAll();

    public Page<MerchantOrderForm> page(MerchantOrderFormQuery query, long merchantId, long storeId, int start, int count);

    public List<MerchantOrderForm> reportForm4merchant(Long merchantId, String startDate, String endDate);

    public List<MerchantOrderForm> reportForm4store(Long merchantId, Long storeId, String startDate, String endDate);

    public MerchantOrderForm get(long orderId, long merchantId, long storeId);

    List<MerchantOrderForm> list4Store(Long merchantId, Long storeId, int state, int start, int count);
}
