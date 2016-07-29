package com.qcloud.component.marketing.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.marketing.dao.FullReducesDao;
import com.qcloud.component.marketing.model.FullReduces;
import com.qcloud.component.marketing.service.FullReducesService;
import com.qcloud.component.marketing.model.query.FullReducesQuery;

@Service
public class FullReducesServiceImpl implements FullReducesService {

    @Autowired
    private FullReducesDao      fullReducesDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "marketing_full_reduces";

    @Override
    public boolean add(FullReduces fullReduces) {

        long id = autoIdGenerator.get(ID_KEY);
        fullReduces.setId(id);
        return fullReducesDao.add(fullReduces);
    }

    @Override
    public FullReduces get(Long id) {

        return fullReducesDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return fullReducesDao.delete(id);
    }

    @Override
    public boolean update(FullReduces fullReduces) {

        return fullReducesDao.update(fullReduces);
    }

    @Override
    public Page<FullReduces> page(FullReducesQuery query, int start, int count) {

        return fullReducesDao.page(query, start, count);
    }

    public List<FullReduces> listAll() {

        return fullReducesDao.listAll();
    }

    @Override
    public List<FullReduces> listCurrentReduces() {

        return fullReducesDao.listCurrentReduces();
    }
}
