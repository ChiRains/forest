package com.qcloud.component.brokerage.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.brokerage.model.FormulaCalculationRecord;
import com.qcloud.component.brokerage.model.query.FormulaCalculationRecordQuery;

public interface FormulaCalculationRecordDao extends ISimpleDao<FormulaCalculationRecord, Long> {

    public boolean add(FormulaCalculationRecord formulaCalculationRecord);

    public FormulaCalculationRecord get(Long id);

    FormulaCalculationRecord getLast(Long formulaId);

    public boolean delete(Long id);

    public boolean update(FormulaCalculationRecord formulaCalculationRecord);

    public List<FormulaCalculationRecord> list(List<Long> idList);

    public Map<Long, FormulaCalculationRecord> map(Set<Long> idSet);

    public Page<FormulaCalculationRecord> page(FormulaCalculationRecordQuery query, int start, int size);

    public List<FormulaCalculationRecord> listAll();
}
