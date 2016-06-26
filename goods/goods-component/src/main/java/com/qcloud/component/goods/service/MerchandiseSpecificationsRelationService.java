package com.qcloud.component.goods.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.goods.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.goods.model.query.MerchandiseSpecificationsRelationQuery;

public interface MerchandiseSpecificationsRelationService {

    public boolean add(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation);

    public MerchandiseSpecificationsRelation get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation);

    public Page<MerchandiseSpecificationsRelation> page(MerchandiseSpecificationsRelationQuery query, int start, int count);

    public List<MerchandiseSpecificationsRelation> listAll();

    public boolean updateSpecByMap(MerchandiseSpecificationsRelation merchandiseSpecificationsRelation);

    public List<MerchandiseSpecificationsRelation> listByMerchandiseId(Long merchandiseId);
    
    public boolean deleteByMerchandiseId(Long merchandiseIds);
    
    public List<MerchandiseSpecificationsRelation> listByMap(Long merchandiseId,Long attributeId);
}
