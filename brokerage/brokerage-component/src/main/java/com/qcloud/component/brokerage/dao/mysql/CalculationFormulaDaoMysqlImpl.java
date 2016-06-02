package com.qcloud.component.brokerage.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.brokerage.dao.CalculationFormulaDao;
import com.qcloud.component.brokerage.model.CalculationFormula;
import com.qcloud.component.brokerage.model.query.CalculationFormulaQuery;

@Repository
public class CalculationFormulaDaoMysqlImpl implements CalculationFormulaDao {

    @Resource(name = "sqlOperator-brokerage")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(CalculationFormula calculationFormula) {

        return sqlOperator.insert("com.qcloud.component.brokerage.dao.mysql.mapper.CalculationFormulaMapper.insert", calculationFormula) == 1;
    }

    @Override
    public CalculationFormula get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.CalculationFormulaMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.brokerage.dao.mysql.mapper.CalculationFormulaMapper.delete", id) > 0;
    }

    @Override
    public boolean update(CalculationFormula calculationFormula) {

        return sqlOperator.update("com.qcloud.component.brokerage.dao.mysql.mapper.CalculationFormulaMapper.update", calculationFormula) > 0;
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

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<CalculationFormula> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.CalculationFormulaMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.CalculationFormulaMapper.count4page", param);
        Page<CalculationFormula> page = new Page<CalculationFormula>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<CalculationFormula> page(CalculationFormulaQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<CalculationFormula> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.CalculationFormulaMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.CalculationFormulaMapper.count4query", param);
        Page<CalculationFormula> page = new Page<CalculationFormula>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<CalculationFormula> listAll() {

        List<CalculationFormula> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.CalculationFormulaMapper.listAll");
        return list;
    }

    @Override
    public List<CalculationFormula> list() {

        List<CalculationFormula> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.CalculationFormulaMapper.list");
        return list;
    }
}
