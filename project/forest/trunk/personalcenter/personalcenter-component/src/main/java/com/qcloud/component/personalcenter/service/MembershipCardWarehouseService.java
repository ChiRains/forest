package com.qcloud.component.personalcenter.service;

import java.util.List;
import com.qcloud.component.personalcenter.model.CardNumberConfig;
import com.qcloud.component.personalcenter.model.MembershipCardWarehouse;
import com.qcloud.component.personalcenter.model.query.MembershipCardWarehouseQuery;
import com.qcloud.pirates.data.Page;

public interface MembershipCardWarehouseService {

    public boolean add(MembershipCardWarehouse membershipCardWarehouse);

    public MembershipCardWarehouse get(Long id);

    public MembershipCardWarehouse getByCardNumber(String number);

    public boolean delete(Long id);

    public boolean update(MembershipCardWarehouse membershipCardWarehouse);

    public Page<MembershipCardWarehouse> page(MembershipCardWarehouseQuery query, int start, int count);

    public List<MembershipCardWarehouse> listAll();

    void init(String startNumStr, String endNumStr);
    
    CardNumberConfig getConfig();
}
