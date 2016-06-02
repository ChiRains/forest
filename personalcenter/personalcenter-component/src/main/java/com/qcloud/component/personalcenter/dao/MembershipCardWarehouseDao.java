package com.qcloud.component.personalcenter.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.personalcenter.model.MembershipCardWarehouse;
import com.qcloud.component.personalcenter.model.query.MembershipCardWarehouseQuery;

public interface MembershipCardWarehouseDao extends ISimpleDao<MembershipCardWarehouse, Long> {

    public boolean add(MembershipCardWarehouse membershipCardWarehouse);

    public MembershipCardWarehouse get(Long id);

    MembershipCardWarehouse getByCardNumber(String number);

    public boolean delete(Long id);

    public boolean update(MembershipCardWarehouse membershipCardWarehouse);

    public List<MembershipCardWarehouse> list(List<Long> idList);

    public Map<Long, MembershipCardWarehouse> map(Set<Long> idSet);

    public Page<MembershipCardWarehouse> page(MembershipCardWarehouseQuery query, int start, int size);

    public List<MembershipCardWarehouse> listAll();
}
