package com.qcloud.component.my.service;

import java.util.List;
import com.qcloud.component.my.model.Consignee;
import com.qcloud.component.my.model.query.ConsigneeQuery;
import com.qcloud.pirates.data.Page;

public interface ConsigneeService {

    public boolean add(Consignee consignee);

    public Consignee get(Long id);

    public boolean delete(Long id);

    public boolean update(Consignee consignee);

    public Page<Consignee> page(ConsigneeQuery query, int start, int count);

    public List<Consignee> listAll();

    public List<Consignee> listByUser(Long userId);

    Consignee getDefault(Long userId);

    boolean setDefault(Long id);
}
