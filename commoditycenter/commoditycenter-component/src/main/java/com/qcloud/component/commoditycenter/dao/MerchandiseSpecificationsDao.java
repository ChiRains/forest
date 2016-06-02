package com.qcloud.component.commoditycenter.dao;

import com.qcloud.component.commoditycenter.model.MerchandiseSpecifications;
import com.qcloud.component.commoditycenter.model.query.MerchandiseSpecificationsQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 商品规格
 *
 * @author Zoro
 */
public interface MerchandiseSpecificationsDao extends ISimpleDao<MerchandiseSpecifications, Long> {

    public boolean add(MerchandiseSpecifications merchandiseSpecifications);

    public MerchandiseSpecifications get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseSpecifications merchandiseSpecifications);

    public List<MerchandiseSpecifications> list(List<Long> idList);

    public Map<Long, MerchandiseSpecifications> map(Set<Long> idSet);

    public Page<MerchandiseSpecifications> page(MerchandiseSpecificationsQuery query, int start, int size);

    public List<MerchandiseSpecifications> listAll();

    public List<MerchandiseSpecifications> listByMerchandise(Long merchandiseId);

    public MerchandiseSpecifications get(HashMap where);

    public List<MerchandiseSpecifications> list(HashMap where);

    public Page<MerchandiseSpecifications> page(HashMap where, int start, int size);
}
