package com.qcloud.component.my.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.my.model.Consignee;
import com.qcloud.component.my.model.query.ConsigneeQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface ConsigneeDao extends ISimpleDao<Consignee, Long> {

    public boolean add(Consignee consignee);

    public Consignee get(Long id);

    public boolean delete(Long id);

    public boolean update(Consignee consignee);

    public List<Consignee> list(List<Long> idList);

    public Map<Long, Consignee> map(Set<Long> idSet);

    public Page<Consignee> page(ConsigneeQuery query, int start, int size);

    public List<Consignee> listAll();

    List<Consignee> listByUser(Long userId);
}
