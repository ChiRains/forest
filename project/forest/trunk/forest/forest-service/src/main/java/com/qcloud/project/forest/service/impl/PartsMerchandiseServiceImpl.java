package com.qcloud.project.forest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.PartsMerchandiseDao;
import com.qcloud.project.forest.model.PartsMerchandise;
import com.qcloud.project.forest.service.PartsMerchandiseService;
import com.qcloud.project.forest.model.query.PartsMerchandiseQuery;

@Service
public class PartsMerchandiseServiceImpl implements PartsMerchandiseService {

    @Autowired
    private PartsMerchandiseDao partsMerchandiseDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "forest_parts_merchandise";

    @Override
    public boolean add(PartsMerchandise partsMerchandise) {

        long id = autoIdGenerator.get(ID_KEY);
        partsMerchandise.setId(id);
        return partsMerchandiseDao.add(partsMerchandise);
    }

    @Override
    public PartsMerchandise get(Long id) {

        return partsMerchandiseDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return partsMerchandiseDao.delete(id);
    }

    @Override
    public boolean update(PartsMerchandise partsMerchandise) {

        return partsMerchandiseDao.update(partsMerchandise);
    }

    public List<PartsMerchandise> listByClassifyId(Long classifyId) {

        return partsMerchandiseDao.listByClassifyId(classifyId);
    }

    @Override
    public Page<PartsMerchandise> page(PartsMerchandiseQuery query, int start, int count) {

        return partsMerchandiseDao.page(query, start, count);
    }

    public List<PartsMerchandise> listAll() {

        return partsMerchandiseDao.listAll();
    }

    @Override
    @Transactional
    public boolean deleteByClassify(long classifyId) {

        return partsMerchandiseDao.deleteByClassify(classifyId);
    }

    @Override
    @Transactional
    public boolean deleteByMerchandiseId(long merchandiseId) {

        return partsMerchandiseDao.deleteByMerchandiseId(merchandiseId);
    }
}
