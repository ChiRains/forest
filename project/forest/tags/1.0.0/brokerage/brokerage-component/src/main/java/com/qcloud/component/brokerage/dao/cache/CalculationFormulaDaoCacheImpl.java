package com.qcloud.component.brokerage.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.dao.CalculationFormulaDao;
import com.qcloud.component.brokerage.model.CalculationFormula;
import com.qcloud.component.brokerage.model.query.CalculationFormulaQuery;

@Repository
public class CalculationFormulaDaoCacheImpl implements CalculationFormulaDao {

    @Autowired
    private CalculationFormulaDao calculationFormulaDaoMysqlImpl;

    // @Autowired
    // private CalculationFormulaDao calculationFormulaDaoRedisImpl;
    @Override
    public boolean add(CalculationFormula calculationFormula) {

        return calculationFormulaDaoMysqlImpl.add(calculationFormula);
    }

    @Override
    public CalculationFormula get(Long id) {

        return calculationFormulaDaoMysqlImpl.get(id);
        // return CacheLoader.get(calculationFormulaDaoRedisImpl, calculationFormulaDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return calculationFormulaDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(CalculationFormula calculationFormula) {

        return calculationFormulaDaoMysqlImpl.update(calculationFormula);
    }

    @Override
    public List<CalculationFormula> list(List<Long> idList) {

        return CacheLoader.list(calculationFormulaDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, CalculationFormula> map(Set<Long> idSet) {

        return CacheLoader.map(calculationFormulaDaoMysqlImpl, idSet);
    }

    @Override
    public Page<CalculationFormula> page(int start, int count) {

        return calculationFormulaDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<CalculationFormula> page(CalculationFormulaQuery query, int start, int count) {

        return calculationFormulaDaoMysqlImpl.page(query, start, count);
    }

    public List<CalculationFormula> listAll() {

        return calculationFormulaDaoMysqlImpl.listAll();
    }

    @Override
    public List<CalculationFormula> list() {

        return calculationFormulaDaoMysqlImpl.list();
    }
}
