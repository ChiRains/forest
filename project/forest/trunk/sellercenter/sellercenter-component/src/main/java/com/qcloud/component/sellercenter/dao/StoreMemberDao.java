package com.qcloud.component.sellercenter.dao;

import com.qcloud.component.sellercenter.model.StoreMember;
import com.qcloud.component.sellercenter.model.query.StoreMemberQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StoreMemberDao extends ISimpleDao<StoreMember, Long> {

    public boolean add(StoreMember storeMember);

    public StoreMember get(Long id);

    public boolean delete(Long id);

    public boolean update(StoreMember storeMember);

    public List<StoreMember> list(List<Long> idList);

    public Map<Long, StoreMember> map(Set<Long> idSet);

    public Page<StoreMember> page(StoreMemberQuery query, int start, int size);

    public List<StoreMember> listAll();

    public StoreMember get(HashMap where);

    public List<StoreMember> list(HashMap where);

    public Page<StoreMember> page(HashMap where, int start, int size);

}
