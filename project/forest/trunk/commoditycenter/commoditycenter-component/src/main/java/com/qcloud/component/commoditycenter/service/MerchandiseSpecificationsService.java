package com.qcloud.component.commoditycenter.service;

import java.util.HashMap;
import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.commoditycenter.model.MerchandiseSpecifications;
import com.qcloud.component.commoditycenter.model.query.MerchandiseSpecificationsQuery;

public interface MerchandiseSpecificationsService {

    public boolean add(MerchandiseSpecifications merchandiseSpecifications);

    public MerchandiseSpecifications get(Long id);

    public MerchandiseSpecifications get(long merchandiseId, long attributeId0, String value0, long attributeId1, String value1, long attributeId2, String value2);

    public boolean delete(Long id);

    public boolean update(MerchandiseSpecifications merchandiseSpecifications);

    public Page<MerchandiseSpecifications> page(MerchandiseSpecificationsQuery query, int start, int count);

    public List<MerchandiseSpecifications> listAll();

    public boolean setMerchandiseSpecifications(Long merchandiseId, List<MerchandiseSpecifications> list);

    public List<MerchandiseSpecifications> listByMerchandise(Long merchandiseId);

    public MerchandiseSpecifications get(HashMap where);

    public List<MerchandiseSpecifications> list(HashMap where);

    public Page<MerchandiseSpecifications> page(HashMap where, int start, int size);
}
