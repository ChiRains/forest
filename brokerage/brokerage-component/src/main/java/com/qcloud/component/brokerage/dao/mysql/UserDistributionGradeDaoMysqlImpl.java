package com.qcloud.component.brokerage.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.brokerage.dao.UserDistributionGradeDao;
import com.qcloud.component.brokerage.model.UserDistributionGrade;
import com.qcloud.component.brokerage.model.query.UserDistributionGradeQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class UserDistributionGradeDaoMysqlImpl implements UserDistributionGradeDao {

    @Resource(name = "sqlOperator-brokerage")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(UserDistributionGrade userDistributionGrade) {

        return sqlOperator.insert("com.qcloud.component.brokerage.dao.mysql.mapper.UserDistributionGradeMapper.insert", userDistributionGrade) == 1;
    }

    @Override
    public UserDistributionGrade get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserDistributionGradeMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.brokerage.dao.mysql.mapper.UserDistributionGradeMapper.delete", id) > 0;
    }

    @Override
    public boolean update(UserDistributionGrade userDistributionGrade) {

        return sqlOperator.update("com.qcloud.component.brokerage.dao.mysql.mapper.UserDistributionGradeMapper.update", userDistributionGrade) > 0;
    }

    @Override
    public List<UserDistributionGrade> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, UserDistributionGrade> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<UserDistributionGrade> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<UserDistributionGrade> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UserDistributionGradeMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserDistributionGradeMapper.count4page", param);
        Page<UserDistributionGrade> page = new Page<UserDistributionGrade>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<UserDistributionGrade> page(UserDistributionGradeQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("gradeId", query.getGradeId());
        param.put("userId", query.getUserId());
        List<UserDistributionGrade> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UserDistributionGradeMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserDistributionGradeMapper.count4query", param);
        Page<UserDistributionGrade> page = new Page<UserDistributionGrade>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<UserDistributionGrade> listAll() {

        List<UserDistributionGrade> list = sqlOperator.selectList("com.qcloud.component.brokerage.dao.mysql.mapper.UserDistributionGradeMapper.listAll");
        return list;
    }

    @Override
    public UserDistributionGrade getByUser(long userId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        return sqlOperator.selectOne("com.qcloud.component.brokerage.dao.mysql.mapper.UserDistributionGradeMapper.getByUser", param);
    }
}
