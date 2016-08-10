package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.BpCalculation;
import com.qcloud.project.forest.model.query.BpCalculationQuery;

public interface BpCalculationDao extends ISimpleDao<BpCalculation, Long> {

    public boolean add(BpCalculation bpCalculation);

    public BpCalculation get(Long id);

    public boolean delete(Long id);

    public boolean update(BpCalculation bpCalculation);

    public List<BpCalculation> list(List<Long> idList);

    public Map<Long, BpCalculation> map(Set<Long> idSet);

    public Page<BpCalculation> page(BpCalculationQuery query, int start, int size);

    public List<BpCalculation> listAll();

    public BpCalculation getByRange(int dbp, int sbp);
}
