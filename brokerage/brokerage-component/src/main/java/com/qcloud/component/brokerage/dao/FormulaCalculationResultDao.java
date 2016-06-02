package com.qcloud.component.brokerage.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.brokerage.model.FormulaCalculationResult;
import com.qcloud.component.brokerage.model.query.FormulaCalculationResultQuery;

public interface FormulaCalculationResultDao extends ISimpleDao<FormulaCalculationResult, Long> {

    public boolean add(FormulaCalculationResult formulaCalculationResult);

    public FormulaCalculationResult get(Long id);

    public boolean delete(Long id);

    public boolean update(FormulaCalculationResult formulaCalculationResult);

    public List<FormulaCalculationResult> list(List<Long> idList);

    public Map<Long, FormulaCalculationResult> map(Set<Long> idSet);

    public Page<FormulaCalculationResult> page(FormulaCalculationResultQuery query, int start, int size);

    public List<FormulaCalculationResult> listAll();
    
    List<FormulaCalculationResult> listToAllocation(long formulaId);
}
