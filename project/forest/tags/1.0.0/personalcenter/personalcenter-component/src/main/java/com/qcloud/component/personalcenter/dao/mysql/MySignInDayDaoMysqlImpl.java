package com.qcloud.component.personalcenter.dao.mysql;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.personalcenter.dao.MySignInDayDao;
import com.qcloud.component.personalcenter.model.MySignInDay;
import com.qcloud.component.personalcenter.model.query.MySignInDayQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class MySignInDayDaoMysqlImpl implements MySignInDayDao {

    @Resource(name = "sqlOperator-personalcenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MySignInDay mySignInDay) {

        return sqlOperator.insert("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInDayMapper.insert", mySignInDay) == 1;
    }

    @Override
    public MySignInDay get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInDayMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInDayMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MySignInDay mySignInDay) {

        return sqlOperator.update("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInDayMapper.update", mySignInDay) > 0;
    }

    @Override
    public List<MySignInDay> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MySignInDay> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MySignInDay> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MySignInDay> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInDayMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInDayMapper.count4page", param);
        Page<MySignInDay> page = new Page<MySignInDay>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MySignInDay> page(MySignInDayQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MySignInDay> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInDayMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInDayMapper.count4query", param);
        Page<MySignInDay> page = new Page<MySignInDay>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MySignInDay> listAll() {

        List<MySignInDay> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInDayMapper.listAll");
        return list;
    }

    @Override
    public MySignInDay getByDate(long userId, Date date) {

        Calendar c0 = Calendar.getInstance();
        c0.setTime(date);
        int day = c0.get(Calendar.DAY_OF_MONTH);
        int month = c0.get(Calendar.MONTH) + 1;
        int year = c0.get(Calendar.YEAR);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("year", year);
        param.put("month", month);
        param.put("day", day);
        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInDayMapper.getByDate", param);
    }

    @Override
    public List<MySignInDay> listByYearAndMonth(long userId, int year, int month) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("year", year);
        param.put("month", month);
        param.put("userId", userId);
        List<MySignInDay> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.MySignInDayMapper.listByYearAndMonth", param);
        return list;
    }
}
