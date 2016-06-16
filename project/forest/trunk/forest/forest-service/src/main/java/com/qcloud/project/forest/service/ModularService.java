package com.qcloud.project.forest.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.Modular;
import com.qcloud.project.forest.model.query.ModularQuery;

public interface ModularService {

    public boolean add(Modular modular);

    public Modular get(Long id);

    public boolean delete(Long id);

    public boolean update(Modular modular);

    public Page<Modular> page(ModularQuery query, int start, int count);

    public List<Modular> listAll();

    public Modular getByCode(String code);
}
