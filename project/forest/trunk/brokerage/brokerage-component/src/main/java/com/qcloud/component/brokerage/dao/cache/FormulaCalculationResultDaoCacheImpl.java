package com.qcloud.component.brokerage.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.dao.FormulaCalculationResultDao;
import com.qcloud.component.brokerage.model.FormulaCalculationResult;
import com.qcloud.component.brokerage.model.query.FormulaCalculationResultQuery;

@Repository
public class FormulaCalculationResultDaoCacheImpl implements FormulaCalculationResultDao {

    @Autowired
    private FormulaCalculationResultDao formulaCalculationResultDaoMysqlImpl;

    // @Autowired
    // private FormulaCalculationResultDao formulaCalculationResultDaoRedisImpl;
    @Override
    public boolean add(FormulaCalculationResult formulaCalculationResult) {

        return formulaCalculationResultDaoMysqlImpl.add(formulaCalculationResult);
    }

    @Override
    public FormulaCalculationResult get(Long id) {

        return formulaCalculationResultDaoMysqlImpl.get(id);
        // return CacheLoader.get(formulaCalculationResultDaoRedisImpl, formulaCalculationResultDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return formulaCalculationResultDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(FormulaCalculationResult formulaCalculationResult) {

        return formulaCalculationResultDaoMysqlImpl.update(formulaCalculationResult);
    }

    @Override
    public List<FormulaCalculationResult> list(List<Long> idList) {

        return CacheLoader.list(formulaCalculationResultDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, FormulaCalculationResult> map(Set<Long> idSet) {

        return CacheLoader.map(formulaCalculationResultDaoMysqlImpl, idSet);
    }

    @Override
    public Page<FormulaCalculationResult> page(int start, int count) {

        return formulaCalculationResultDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<FormulaCalculationResult> page(FormulaCalculationResultQuery query, int start, int count) {

        return formulaCalculationResultDaoMysqlImpl.page(query, start, count);
    }

    public List<FormulaCalculationResult> listAll() {

        return formulaCalculationResultDaoMysqlImpl.listAll();
    }

    @Override
    public List<FormulaCalculationResult> listToAllocation(long formulaId) {

        return formulaCalculationResultDaoMysqlImpl.listToAllocation(formulaId);
    }
}
