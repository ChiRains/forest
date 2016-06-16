package com.qcloud.project.forest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ModularUserDao;
import com.qcloud.project.forest.model.ModularUser;
import com.qcloud.project.forest.model.query.ModularUserQuery;
import com.qcloud.project.forest.service.ModularUserService;

@Service
public class ModularUserServiceImpl implements ModularUserService {

    @Autowired
    private ModularUserDao      modularUserDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "forest_modular_user";

    @Override
    public boolean add(ModularUser modularUser) {

        long id = autoIdGenerator.get(ID_KEY);
        modularUser.setId(id);
        return modularUserDao.add(modularUser);
    }

    @Override
    public ModularUser get(Long id) {

        return modularUserDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return modularUserDao.delete(id);
    }

    @Override
    public boolean update(ModularUser modularUser) {

        return modularUserDao.update(modularUser);
    }

    public List<ModularUser> listByUserId(Long userId) {

        return modularUserDao.listByUserId(userId);
    }

    @Override
    public Page<ModularUser> page(ModularUserQuery query, int start, int count) {

        return modularUserDao.page(query, start, count);
    }

    public List<ModularUser> listAll() {

        return modularUserDao.listAll();
    }

    @Override
    public Boolean deleteByUserId(Long userId) {

        return modularUserDao.deleteByUserId(userId);
    }
}
