package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.ModularUser;
import com.qcloud.project.forest.model.query.ModularUserQuery;

public interface ModularUserDao extends ISimpleDao<ModularUser, Long> {

    public boolean add(ModularUser modularUser);

    public ModularUser get(Long id);

    public boolean delete(Long id);

    public boolean update(ModularUser modularUser);

    public List<ModularUser> list(List<Long> idList);

    public Map<Long, ModularUser> map(Set<Long> idSet);

    public Page<ModularUser> page(ModularUserQuery query, int start, int size);

    public List<ModularUser> listAll();

    public List<ModularUser> listByUserId(Long userId);

    public Boolean deleteByUserId(Long userId);
}
