package com.qcloud.component.goods.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.goods.model.MerchandiseMarketing;
import com.qcloud.component.goods.model.query.MerchandiseMarketingQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

/**
 * 促销商品信息（价格、库存）
 * 
 * @author Zoro
 *
 */
public interface MerchandiseMarketingDao extends ISimpleDao<MerchandiseMarketing, Long> {

    public boolean add(MerchandiseMarketing merchandiseMarketing);

    public MerchandiseMarketing get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseMarketing merchandiseMarketing);

    public boolean update(MerchandiseMarketing merchandiseMarketing, Date lastUpdateTime);

    public List<MerchandiseMarketing> list(List<Long> idList);

    public Map<Long, MerchandiseMarketing> map(Set<Long> idSet);

    public Page<MerchandiseMarketing> page(MerchandiseMarketingQuery query, int start, int size);

    public List<MerchandiseMarketing> listAll();

    public MerchandiseMarketing getByUnifiedMerchandise(Long unifiedMerchandiseId);
    
    public boolean setEnable (Long id,int enable);
    
    List<MerchandiseMarketing> list(int sence, double discountRange, int start, int count);    
}
