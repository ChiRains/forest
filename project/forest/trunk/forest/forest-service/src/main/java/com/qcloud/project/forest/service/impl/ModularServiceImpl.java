package com.qcloud.project.forest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ModularDao;
import com.qcloud.project.forest.model.Modular;
import com.qcloud.project.forest.model.query.ModularQuery;
import com.qcloud.project.forest.service.ModularService;

@Service
public class ModularServiceImpl implements ModularService {

    @Autowired
    private ModularDao          modularDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "forest_modular";

    @Override
    public boolean add(Modular modular) {

        long id = autoIdGenerator.get(ID_KEY);
        modular.setId(id);
        return modularDao.add(modular);
    }

    @Override
    public Modular get(Long id) {

        return modularDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return modularDao.delete(id);
    }

    @Override
    public boolean update(Modular modular) {

        return modularDao.update(modular);
    }

    @Override
    public Page<Modular> page(ModularQuery query, int start, int count) {

        return modularDao.page(query, start, count);
    }

    public List<Modular> listAll() {

        return modularDao.listAll();
    }

    @Override
    public Modular getByCode(String code) {

        return modularDao.getByCode(code);
    }
}
