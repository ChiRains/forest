package com.qcloud.component.sellercenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.sellercenter.model.MerchantMerchandiseClassify;
import com.qcloud.component.sellercenter.model.query.MerchantMerchandiseClassifyQuery;

public interface MerchantMerchandiseClassifyDao extends ISimpleDao<MerchantMerchandiseClassify, Long> {

    public boolean add(MerchantMerchandiseClassify merchantMerchandiseClassify);

    public MerchantMerchandiseClassify get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchantMerchandiseClassify merchantMerchandiseClassify);

    public List<MerchantMerchandiseClassify> list(List<Long> idList);

    public Map<Long, MerchantMerchandiseClassify> map(Set<Long> idSet);

    public Page<MerchantMerchandiseClassify> page(MerchantMerchandiseClassifyQuery query, int start, int size);

    public List<MerchantMerchandiseClassify> listAll();

    public List<MerchantMerchandiseClassify> listByMerchantId(Long merchantId);

    public boolean deleteByMerchantId(Long merchantId);
    
}
