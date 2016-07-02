package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.Modular;
import com.qcloud.project.forest.model.query.ModularQuery;

public interface ModularDao extends ISimpleDao<Modular, Long> {

    public boolean add(Modular modular);

    public Modular get(Long id);

    public boolean delete(Long id);

    public boolean update(Modular modular);

    public List<Modular> list(List<Long> idList);

    public Map<Long, Modular> map(Set<Long> idSet);

    public Page<Modular> page(ModularQuery query, int start, int size);

    public List<Modular> listAll();

    public Modular getByCode(String code);
}
