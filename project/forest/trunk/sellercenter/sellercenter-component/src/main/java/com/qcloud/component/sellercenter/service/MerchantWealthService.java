package com.qcloud.component.sellercenter.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.model.MerchantWealth;
import com.qcloud.component.sellercenter.model.query.MerchantWealthQuery;

public interface MerchantWealthService {

    public boolean add(MerchantWealth merchantWealth);

    public MerchantWealth get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchantWealth merchantWealth);

    public Page<MerchantWealth> page(MerchantWealthQuery query, int start, int count);

    public List<MerchantWealth> listAll();

    public boolean calculateWealth(long merchantId, double wealth, int type, String desc);

    public MerchantWealth getByMerchant(long merchantId);
}
