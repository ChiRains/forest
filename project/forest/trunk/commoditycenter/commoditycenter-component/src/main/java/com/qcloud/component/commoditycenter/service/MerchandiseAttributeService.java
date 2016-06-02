package com.qcloud.component.commoditycenter.service;
import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.commoditycenter.model.MerchandiseAttribute;
import com.qcloud.component.commoditycenter.model.query.MerchandiseAttributeQuery;
public interface MerchandiseAttributeService {
    
    public boolean add(MerchandiseAttribute merchandiseAttribute);

    public MerchandiseAttribute get(Long id);

    public boolean delete(Long id);

    public boolean update(MerchandiseAttribute merchandiseAttribute);

    public Page<MerchandiseAttribute> page(MerchandiseAttributeQuery query, int start, int count);

    public List<MerchandiseAttribute> listAll();

    public List<MerchandiseAttribute> listByMerchandise(long merchandiseId);

    public void set(Long merchandiseId, List<MerchandiseAttribute> list);
}
