package com.qcloud.component.sellercenter.service;

import java.util.List;
import com.qcloud.component.sellercenter.model.Store;
import com.qcloud.component.sellercenter.model.query.StoreQuery;
import com.qcloud.pirates.data.Page;

public interface StoreService {

    public boolean add(Store store);

    public Store get(Long id);

    public boolean delete(Long id);

    public boolean update(Store store);

    public Page<Store> page(StoreQuery query, int start, int count);

    public List<Store> listAll();

    List<Store> listByMerchant(Long merchantId);

    public List<Store> listByAddress(String province, String city, String district);
    
    public List<Store> listByParentId(Long parentId);
}
