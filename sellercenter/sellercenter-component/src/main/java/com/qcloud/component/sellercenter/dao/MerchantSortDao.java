package com.qcloud.component.sellercenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.sellercenter.model.MerchantSort;
import com.qcloud.component.sellercenter.model.query.MerchantSortQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface MerchantSortDao extends ISimpleDao<MerchantSort,  Long>{
    
    public boolean add(MerchantSort merchantSort);
    
    public MerchantSort get(Long id);

    public boolean delete(Long id);
    
    public boolean update(MerchantSort merchantSort);
    
    public List<MerchantSort> list(List<Long> idList);
    
    public Map<Long, MerchantSort> map(Set<Long> idSet);
    
    public Page<MerchantSort> page(MerchantSortQuery query, int start, int count);

    public List<MerchantSort> listAll();
    //获取优质商家列表
    public List<MerchantSort> list(int start,int count);
    
    
}
