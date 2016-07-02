package com.qcloud.component.sellercenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.sellercenter.model.MerchantSpecClassify;
import com.qcloud.component.sellercenter.model.query.MerchantSpecClassifyQuery;

public interface MerchantSpecClassifyDao extends ISimpleDao<MerchantSpecClassify, Long> {

    public boolean add(MerchantSpecClassify merchantSpecClassify);

    public MerchantSpecClassify get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchantSpecClassify merchantSpecClassify);

    public List<MerchantSpecClassify> list(List<Long> idList);

    public Map<Long, MerchantSpecClassify> map(Set<Long> idSet);

    public Page<MerchantSpecClassify> page(MerchantSpecClassifyQuery query, int start, int size);

    public List<MerchantSpecClassify> listAll();

    public List<MerchantSpecClassify> listByMerchant(long merchantId);
}
