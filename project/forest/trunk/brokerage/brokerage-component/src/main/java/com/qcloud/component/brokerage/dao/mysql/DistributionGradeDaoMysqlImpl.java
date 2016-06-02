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
import com.qcloud.component.brokerage.dao.DistributionGradeDao;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.model.query.DistributionGradeQuery;

@Repository
public class DistributionGradeDaoMysqlImpl implements DistributionGradeDao {

    @Resource(name = "sqlOperator-brokerage")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(DistributionGrade distributionGrade) {

        return sqlOperator.insert("com.qcloud.component.brokerage.dao.mysql.mapper.DistributionGradeMapper.insert", distributionGrade) == 1;
    }

    @Override
    public DistributionGrade get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.DistributionGradeMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.brokerage.dao.mysql.mapper.DistributionGradeMapper.delete", id) > 0;
    }

    @Override
    public boolean update(DistributionGrade distributionGrade) {

        return sqlOperator.update("com.qcloud.component.brokerage.dao.mysql.mapper.DistributionGradeMapper.update", distributionGrade) > 0;
    }

    @Override
    public List<DistributionGrade> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, DistributionGrade> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<DistributionGrade> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<DistributionGrade> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.DistributionGradeMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.DistributionGradeMapper.count4page", param);
        Page<DistributionGrade> page = new Page<DistributionGrade>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<DistributionGrade> page(DistributionGradeQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<DistributionGrade> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.DistributionGradeMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.DistributionGradeMapper.count4query", param);
        Page<DistributionGrade> page = new Page<DistributionGrade>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<DistributionGrade> listAll() {

        List<DistributionGrade> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.DistributionGradeMapper.listAll");
        return list;
    }

    @Override
    public DistributionGrade getDefault() {

        return sqlOperator.selectOne("getDefault");
    }
}
