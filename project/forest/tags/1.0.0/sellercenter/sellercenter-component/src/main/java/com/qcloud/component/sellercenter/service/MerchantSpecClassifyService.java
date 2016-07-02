package com.qcloud.component.sellercenter.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.sellercenter.model.MerchantSpecClassify;
import com.qcloud.component.sellercenter.model.query.MerchantSpecClassifyQuery;

public interface MerchantSpecClassifyService {

    public boolean add(MerchantSpecClassify merchantSpecClassify);

    public MerchantSpecClassify get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchantSpecClassify merchantSpecClassify);

    public Page<MerchantSpecClassify> page(MerchantSpecClassifyQuery query, int start, int count);

    public List<MerchantSpecClassify> listAll();

    public List<MerchantSpecClassify> listByMerchant(long merchantId);
    
    public boolean deleteByMerchant(long merchantId);
}
