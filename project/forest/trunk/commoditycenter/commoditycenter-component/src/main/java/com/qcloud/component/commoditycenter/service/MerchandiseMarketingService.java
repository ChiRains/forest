package com.qcloud.component.commoditycenter.service;

import java.util.List;
import com.qcloud.component.commoditycenter.model.MerchandiseMarketing;
import com.qcloud.component.commoditycenter.model.query.MerchandiseMarketingQuery;
import com.qcloud.pirates.data.Page;

public interface MerchandiseMarketingService {

    public boolean add(MerchandiseMarketing merchandiseMarketing);

    public MerchandiseMarketing get(Long id);

    MerchandiseMarketing getByUnifiedMerchandise(Long unifiedMerchandiseId);

    public boolean delete(Long id);

    public boolean update(MerchandiseMarketing merchandiseMarketing);

    public Page<MerchandiseMarketing> page(MerchandiseMarketingQuery query, int start, int count);

    public List<MerchandiseMarketing> listAll();

    public boolean lockStock(long unifiedMerchandiseId, int stock);

    // int countStock4MerchandiseItem(long merchandiseItemId);
    public boolean setEnable(Long id, int enable);

    public List<MerchandiseMarketing> list(int sence, String keywords, double discountRange, int start, int count);
}
