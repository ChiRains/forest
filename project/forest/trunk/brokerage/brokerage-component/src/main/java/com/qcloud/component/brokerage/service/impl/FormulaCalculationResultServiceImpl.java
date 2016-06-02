package com.qcloud.component.brokerage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.dao.FormulaCalculationResultDao;
import com.qcloud.component.brokerage.model.FormulaCalculationResult;
import com.qcloud.component.brokerage.service.FormulaCalculationResultService;
import com.qcloud.component.brokerage.model.query.FormulaCalculationResultQuery;

@Service
public class FormulaCalculationResultServiceImpl implements FormulaCalculationResultService {

    @Autowired
    private FormulaCalculationResultDao formulaCalculationResultDao;

    @Autowired
    private AutoIdGenerator             autoIdGenerator;

    private static final String         ID_KEY = "brokerage_formula_calculation_result";

    @Override
    public boolean add(FormulaCalculationResult formulaCalculationResult) {

        long id = autoIdGenerator.get(ID_KEY);
        formulaCalculationResult.setId(id);
        return formulaCalculationResultDao.add(formulaCalculationResult);
    }

    @Override
    public FormulaCalculationResult get(Long id) {

        return formulaCalculationResultDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return formulaCalculationResultDao.delete(id);
    }

    @Override
    public boolean update(FormulaCalculationResult formulaCalculationResult) {

        return formulaCalculationResultDao.update(formulaCalculationResult);
    }

    @Override
    public Page<FormulaCalculationResult> page(FormulaCalculationResultQuery query, int start, int count) {

        return formulaCalculationResultDao.page(query, start, count);
    }

    public List<FormulaCalculationResult> listAll() {

        return formulaCalculationResultDao.listAll();
    }

    @Override
    public List<FormulaCalculationResult> listToAllocation(long formulaId) {

        return formulaCalculationResultDao.listToAllocation(formulaId);
    }
}
