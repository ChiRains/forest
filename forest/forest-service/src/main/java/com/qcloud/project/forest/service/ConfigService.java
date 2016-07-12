package com.qcloud.project.forest.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.Config;
import com.qcloud.project.forest.model.query.ConfigQuery;

public interface ConfigService {

    public boolean add(Config config);

    public Config get(Long id);

    public boolean delete(Long id);

    public boolean update(Config config);

    public List<Config> listByType(Integer type);

    public Page<Config> page(ConfigQuery query, int start, int count);

    public List<Config> listAll();

    public Config getByCode(String code);
}
