package com.qcloud.component.brokerage.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.model.FormulaCalculationRecord;
import com.qcloud.component.brokerage.model.query.FormulaCalculationRecordQuery;

public interface FormulaCalculationRecordService {

    public boolean add(FormulaCalculationRecord formulaCalculationRecord);

    public FormulaCalculationRecord get(Long id);

    public FormulaCalculationRecord getLast(Long formulaId);

    public boolean delete(Long id);

    public boolean update(FormulaCalculationRecord formulaCalculationRecord);

    public Page<FormulaCalculationRecord> page(FormulaCalculationRecordQuery query, int start, int count);

    public List<FormulaCalculationRecord> listAll();
}
