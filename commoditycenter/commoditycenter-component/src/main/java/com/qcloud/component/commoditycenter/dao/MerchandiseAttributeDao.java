package com.qcloud.component.commoditycenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.commoditycenter.model.MerchandiseAttribute;
import com.qcloud.component.commoditycenter.model.query.MerchandiseAttributeQuery;

/**
 * 商品属性
 * 
 * @author Zoro
 *
 */
public interface MerchandiseAttributeDao extends ISimpleDao<MerchandiseAttribute, Long> {

    public boolean add(MerchandiseAttribute merchandiseAttribute);

    public MerchandiseAttribute get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseAttribute merchandiseAttribute);

    public List<MerchandiseAttribute> list(List<Long> idList);

    public Map<Long, MerchandiseAttribute> map(Set<Long> idSet);

    public Page<MerchandiseAttribute> page(MerchandiseAttributeQuery query, int start, int size);

    public List<MerchandiseAttribute> listAll();

    List<MerchandiseAttribute> listByMerchandise(long merchandiseId);
}
