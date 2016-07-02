package com.qcloud.component.brokerage.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.brokerage.model.CalculationFormula;
import com.qcloud.component.brokerage.model.query.CalculationFormulaQuery;

public interface CalculationFormulaDao extends ISimpleDao<CalculationFormula, Long> {

    public boolean add(CalculationFormula calculationFormula);

    public CalculationFormula get(Long id);

    public boolean delete(Long id);

    public boolean update(CalculationFormula calculationFormula);

    public List<CalculationFormula> list(List<Long> idList);

    public Map<Long, CalculationFormula> map(Set<Long> idSet);

    public Page<CalculationFormula> page(CalculationFormulaQuery query, int start, int size);

    public List<CalculationFormula> listAll();

    public List<CalculationFormula> list();
}
