package com.qcloud.project.forest.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.ModularUser;
import com.qcloud.project.forest.model.query.ModularUserQuery;

public interface ModularUserService {

    public boolean add(ModularUser modularUser);

    public ModularUser get(Long id);

    public boolean delete(Long id);

    public boolean update(ModularUser modularUser);

    public List<ModularUser> listByUserId(Long userId);

    public Page<ModularUser> page(ModularUserQuery query, int start, int count);

    public List<ModularUser> listAll();

    public Boolean deleteByUserId(Long userId);
}
