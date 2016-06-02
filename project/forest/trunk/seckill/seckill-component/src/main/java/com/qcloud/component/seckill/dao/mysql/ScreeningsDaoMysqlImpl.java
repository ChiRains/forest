package com.qcloud.component.seckill.dao.mysql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.component.seckill.dao.ScreeningsDao;
import com.qcloud.component.seckill.model.Screenings;
import com.qcloud.component.seckill.model.query.ScreeningsQuery;

@Repository
public class ScreeningsDaoMysqlImpl implements ScreeningsDao {

    @Resource(name = "sqlOperator-seckill")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(Screenings screenings) {

        return sqlOperator.insert("com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsMapper.insert", screenings) == 1;
    }

    @Override
    public Screenings get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsMapper.delete", id) > 0;
    }

    @Override
    public boolean update(Screenings screenings) {

        return sqlOperator.update("com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsMapper.update", screenings) > 0;
    }

    @Override
    public List<Screenings> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Screenings> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Screenings> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<Screenings> list = sqlOperator.selectList("com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsMapper.count4page", param);
        Page<Screenings> page = new Page<Screenings>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<Screenings> page(ScreeningsQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("beginTime", query.getBeginTime());
        List<Screenings> list = sqlOperator.selectList("com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsMapper.count4query", param);
        Page<Screenings> page = new Page<Screenings>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<Screenings> listAll() {

        List<Screenings> list = sqlOperator.selectList("com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsMapper.listAll");
        return list;
    }

    @Override
    public List<Screenings> listByDate(Date day) {

        String str = DateUtil.date2String(day, DateUtil.DATE_FORMAT_STRING);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", DateUtil.str2Date(str + " 00:00:00"));
        param.put("end", DateUtil.str2Date(str + " 23:59:59"));
        List<Screenings> list = sqlOperator.selectList("com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsMapper.listByDate", param);
        return list;
    }

    @Override
    public List<Screenings> getRepeatByTime(Date beginTime, Date endTime) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("beginTime", beginTime);
        param.put("endTime", endTime);
        return sqlOperator.selectList("com.qcloud.component.seckill.dao.mysql.mapper.ScreeningsMapper.getRepeatByTime", param);
    }
}
