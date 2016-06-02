package com.qcloud.component.brokerage.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.brokerage.dao.CalculationFormulaDao;
import com.qcloud.component.brokerage.model.CalculationFormula;
import com.qcloud.component.brokerage.model.query.CalculationFormulaQuery;

@Repository
public class CalculationFormulaDaoRedisImpl implements CalculationFormulaDao {

    // @Resource(name = "redis-brokerage")
    // private Redis redis;
    @Override
    public boolean add(CalculationFormula calculationFormula) {

        throw new NotImplementedException();
    }

    @Override
    public CalculationFormula get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(CalculationFormula calculationFormula) {

        throw new NotImplementedException();
    }

    @Override
    public List<CalculationFormula> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, CalculationFormula> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<CalculationFormula> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<CalculationFormula> page(CalculationFormulaQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<CalculationFormula> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<CalculationFormula> list() {

        throw new NotImplementedException();
    }
}
