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
import com.qcloud.project.forest.dao.ActivityDao;
import com.qcloud.project.forest.model.Activity;
import com.qcloud.project.forest.model.query.ActivityQuery;

@Repository
public class ActivityDaoMysqlImpl implements ActivityDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(Activity activity) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.ActivityMapper.insert", activity) == 1;
    }

    @Override
    public Activity get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ActivityMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.ActivityMapper.delete", id) > 0;
    }

    @Override
    public boolean update(Activity activity) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.ActivityMapper.update", activity) > 0;
    }

    @Override
    public List<Activity> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Activity> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Activity> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<Activity> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ActivityMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ActivityMapper.count4page", param);
        Page<Activity> page = new Page<Activity>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<Activity> page(ActivityQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("departmentId", query.getDepartmentId());
        List<Activity> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ActivityMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ActivityMapper.count4query", param);
        Page<Activity> page = new Page<Activity>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<Activity> listAll() {

        List<Activity> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ActivityMapper.listAll");
        return list;
    }

    @Override
    public List<Activity> listBydepartmentId(Long departmentId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("departmentId", departmentId);
        return sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ActivityMapper.listBydepartmentId", param);
    }
}
