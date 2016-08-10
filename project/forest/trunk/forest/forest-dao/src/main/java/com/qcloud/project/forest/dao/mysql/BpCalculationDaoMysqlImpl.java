package com.qcloud.project.forest.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.project.forest.dao.BpCalculationDao;
import com.qcloud.project.forest.model.BpCalculation;
import com.qcloud.project.forest.model.query.BpCalculationQuery;

@Repository
public class BpCalculationDaoMysqlImpl implements BpCalculationDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(BpCalculation bpCalculation) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.BpCalculationMapper.insert", bpCalculation) == 1;
    }

    @Override
    public BpCalculation get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.BpCalculationMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.BpCalculationMapper.delete", id) > 0;
    }

    @Override
    public boolean update(BpCalculation bpCalculation) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.BpCalculationMapper.update", bpCalculation) > 0;
    }

    @Override
    public List<BpCalculation> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, BpCalculation> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<BpCalculation> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<BpCalculation> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.BpCalculationMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.BpCalculationMapper.count4page", param);
        Page<BpCalculation> page = new Page<BpCalculation>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<BpCalculation> page(BpCalculationQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<BpCalculation> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.BpCalculationMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.BpCalculationMapper.count4query", param);
        Page<BpCalculation> page = new Page<BpCalculation>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<BpCalculation> listAll() {

        List<BpCalculation> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.BpCalculationMapper.listAll");
        return list;
    }

    @Override
    public BpCalculation getByRange(int dbp, int sbp) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("dbp", dbp);
        param.put("sbp", sbp);
        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.BpCalculationMapper.getByRange", param);
    }
}
