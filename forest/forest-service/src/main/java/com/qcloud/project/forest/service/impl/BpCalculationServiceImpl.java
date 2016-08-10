package com.qcloud.project.forest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.BpCalculationDao;
import com.qcloud.project.forest.model.BpCalculation;
import com.qcloud.project.forest.model.query.BpCalculationQuery;
import com.qcloud.project.forest.service.BpCalculationService;

@Service
public class BpCalculationServiceImpl implements BpCalculationService {

    @Autowired
    private BpCalculationDao    bpCalculationDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "forest_bp_calculation";

    @Override
    public boolean add(BpCalculation bpCalculation) {

        long id = autoIdGenerator.get(ID_KEY);
        bpCalculation.setId(id);
        return bpCalculationDao.add(bpCalculation);
    }

    @Override
    public BpCalculation get(Long id) {

        return bpCalculationDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return bpCalculationDao.delete(id);
    }

    @Override
    public boolean update(BpCalculation bpCalculation) {

        return bpCalculationDao.update(bpCalculation);
    }

    @Override
    public Page<BpCalculation> page(BpCalculationQuery query, int start, int count) {

        return bpCalculationDao.page(query, start, count);
    }

    public List<BpCalculation> listAll() {

        return bpCalculationDao.listAll();
    }

    @Override
    public BpCalculation getByRange(int dbp, int sbp) {

        return bpCalculationDao.getByRange(dbp, sbp);
    }
}
