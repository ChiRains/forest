package com.qcloud.component.brokerage.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.dao.FormulaCalculationRecordDao;
import com.qcloud.component.brokerage.model.FormulaCalculationRecord;
import com.qcloud.component.brokerage.model.query.FormulaCalculationRecordQuery;

@Repository
public class FormulaCalculationRecordDaoCacheImpl implements FormulaCalculationRecordDao {

    @Autowired
    private FormulaCalculationRecordDao formulaCalculationRecordDaoMysqlImpl;

    // @Autowired
    // private FormulaCalculationRecordDao formulaCalculationRecordDaoRedisImpl;
    @Override
    public boolean add(FormulaCalculationRecord formulaCalculationRecord) {

        return formulaCalculationRecordDaoMysqlImpl.add(formulaCalculationRecord);
    }

    @Override
    public FormulaCalculationRecord get(Long id) {

        return formulaCalculationRecordDaoMysqlImpl.get(id);
        // return CacheLoader.get(formulaCalculationRecordDaoRedisImpl, formulaCalculationRecordDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return formulaCalculationRecordDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(FormulaCalculationRecord formulaCalculationRecord) {

        return formulaCalculationRecordDaoMysqlImpl.update(formulaCalculationRecord);
    }

    @Override
    public List<FormulaCalculationRecord> list(List<Long> idList) {

        return CacheLoader.list(formulaCalculationRecordDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, FormulaCalculationRecord> map(Set<Long> idSet) {

        return CacheLoader.map(formulaCalculationRecordDaoMysqlImpl, idSet);
    }

    @Override
    public Page<FormulaCalculationRecord> page(int start, int count) {

        return formulaCalculationRecordDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<FormulaCalculationRecord> page(FormulaCalculationRecordQuery query, int start, int count) {

        return formulaCalculationRecordDaoMysqlImpl.page(query, start, count);
    }

    public List<FormulaCalculationRecord> listAll() {

        return formulaCalculationRecordDaoMysqlImpl.listAll();
    }

    @Override
    public FormulaCalculationRecord getLast(Long formulaId) {

        return formulaCalculationRecordDaoMysqlImpl.getLast(formulaId);
    }
}
