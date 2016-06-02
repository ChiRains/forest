package com.qcloud.component.sellercenter.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.sellercenter.model.MerchantSort;
import com.qcloud.component.sellercenter.model.query.MerchantSortQuery;
import com.qcloud.pirates.data.Page;


public interface MerchantSortService {
    
    public boolean add(MerchantSort merchantSort);
    
    public MerchantSort get(Long id);

    public boolean delete(Long id);
    
    public boolean update(MerchantSort merchantSort);
    
    public List<MerchantSort> list(List<Long> idList);
    
    public Map<Long, MerchantSort> map(Set<Long> idSet);
    
    public Page<MerchantSort> page(MerchantSortQuery query, int start, int count);

    public List<MerchantSort> listAll();
    
    public List<MerchantSort> list(int start, int count);
    
}
