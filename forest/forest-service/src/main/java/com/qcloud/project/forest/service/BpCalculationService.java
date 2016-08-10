package com.qcloud.project.forest.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.BpCalculation;
import com.qcloud.project.forest.model.query.BpCalculationQuery;

public interface BpCalculationService {

    public boolean add(BpCalculation bpCalculation);

    public BpCalculation get(Long id);

    public boolean delete(Long id);

    public boolean update(BpCalculation bpCalculation);

    public Page<BpCalculation> page(BpCalculationQuery query, int start, int count);

    public List<BpCalculation> listAll();

    public BpCalculation getByRange(int dbp, int sbp);
}
