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
import com.qcloud.component.brokerage.dao.AllocationSchemeDao;
import com.qcloud.component.brokerage.model.AllocationScheme;
import com.qcloud.component.brokerage.model.query.AllocationSchemeQuery;

@Repository
public class AllocationSchemeDaoMysqlImpl implements AllocationSchemeDao {

    @Resource(name = "sqlOperator-brokerage")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(AllocationScheme allocationScheme) {

        return sqlOperator.insert("com.qcloud.component.brokerage.dao.mysql.mapper.AllocationSchemeMapper.insert", allocationScheme) == 1;
    }

    @Override
    public AllocationScheme get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.AllocationSchemeMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.brokerage.dao.mysql.mapper.AllocationSchemeMapper.delete", id) > 0;
    }

    @Override
    public boolean update(AllocationScheme allocationScheme) {

        return sqlOperator.update("com.qcloud.component.brokerage.dao.mysql.mapper.AllocationSchemeMapper.update", allocationScheme) > 0;
    }

    @Override
    public List<AllocationScheme> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, AllocationScheme> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<AllocationScheme> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<AllocationScheme> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.AllocationSchemeMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.AllocationSchemeMapper.count4page", param);
        Page<AllocationScheme> page = new Page<AllocationScheme>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<AllocationScheme> page(AllocationSchemeQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("formulaId", query.getFormulaId());
        param.put("start", start);
        param.put("count", count);
        List<AllocationScheme> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.AllocationSchemeMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.AllocationSchemeMapper.count4query", param);
        Page<AllocationScheme> page = new Page<AllocationScheme>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<AllocationScheme> listAll() {

        List<AllocationScheme> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.AllocationSchemeMapper.listAll");
        return list;
    }

    @Override
    public List<AllocationScheme> listByFormula(long formulaId) {

        List<AllocationScheme> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.AllocationSchemeMapper.listByFormula", formulaId);
        return list;
    }
}
