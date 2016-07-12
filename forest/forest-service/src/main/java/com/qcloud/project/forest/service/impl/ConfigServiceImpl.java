package com.qcloud.project.forest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.ConfigDao;
import com.qcloud.project.forest.model.Config;
import com.qcloud.project.forest.model.query.ConfigQuery;
import com.qcloud.project.forest.service.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigDao           configDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "forest_config";

    @Override
    public boolean add(Config config) {

        long id = autoIdGenerator.get(ID_KEY);
        config.setId(id);
        return configDao.add(config);
    }

    @Override
    public Config get(Long id) {

        return configDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return configDao.delete(id);
    }

    @Override
    public boolean update(Config config) {

        return configDao.update(config);
    }

    public List<Config> listByType(Integer type) {

        return configDao.listByType(type);
    }

    @Override
    public Page<Config> page(ConfigQuery query, int start, int count) {

        return configDao.page(query, start, count);
    }

    public List<Config> listAll() {

        return configDao.listAll();
    }

    @Override
    public Config getByCode(String code) {

        return configDao.getByCode(code);
    }
}
