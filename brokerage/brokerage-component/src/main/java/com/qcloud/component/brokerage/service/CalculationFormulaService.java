package com.qcloud.component.brokerage.service;

import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.model.CalculationFormula;
import com.qcloud.component.brokerage.model.query.CalculationFormulaQuery;

public interface CalculationFormulaService {

    public boolean add(CalculationFormula calculationFormula);

    public CalculationFormula get(Long id);

    public boolean delete(Long id);

    public boolean update(CalculationFormula calculationFormula);

    public Page<CalculationFormula> page(CalculationFormulaQuery query, int start, int count);

    public List<CalculationFormula> listAll();

    public List<CalculationFormula> list();
}
