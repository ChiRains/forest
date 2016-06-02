package com.qcloud.component.brokerage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.dao.CalculationFormulaDao;
import com.qcloud.component.brokerage.model.CalculationFormula;
import com.qcloud.component.brokerage.service.CalculationFormulaService;
import com.qcloud.component.brokerage.model.query.CalculationFormulaQuery;

@Service
public class CalculationFormulaServiceImpl implements CalculationFormulaService {

    @Autowired
    private CalculationFormulaDao calculationFormulaDao;

    @Autowired
    private AutoIdGenerator       autoIdGenerator;

    private static final String   ID_KEY = "brokerage_calculation_formula";

    @Override
    public boolean add(CalculationFormula calculationFormula) {

        long id = autoIdGenerator.get(ID_KEY);
        calculationFormula.setId(id);
        return calculationFormulaDao.add(calculationFormula);
    }

    @Override
    public CalculationFormula get(Long id) {

        return calculationFormulaDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return calculationFormulaDao.delete(id);
    }

    @Override
    public boolean update(CalculationFormula calculationFormula) {

        return calculationFormulaDao.update(calculationFormula);
    }

    @Override
    public Page<CalculationFormula> page(CalculationFormulaQuery query, int start, int count) {

        return calculationFormulaDao.page(query, start, count);
    }

    public List<CalculationFormula> listAll() {

        return calculationFormulaDao.listAll();
    }

    @Override
    public List<CalculationFormula> list() {

        return calculationFormulaDao.list();
    }
}
