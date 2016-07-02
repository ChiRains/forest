package com.qcloud.component.brokerage.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.model.FormulaCalculationResult;
import com.qcloud.component.brokerage.model.query.FormulaCalculationResultQuery;

public interface FormulaCalculationResultService {

    public boolean add(FormulaCalculationResult formulaCalculationResult);

    public FormulaCalculationResult get(Long id);

    public boolean delete(Long id);

    public boolean update(FormulaCalculationResult formulaCalculationResult);

    public Page<FormulaCalculationResult> page(FormulaCalculationResultQuery query, int start, int count);

    public List<FormulaCalculationResult> listAll();

    List<FormulaCalculationResult> listToAllocation(long formulaId);
}
