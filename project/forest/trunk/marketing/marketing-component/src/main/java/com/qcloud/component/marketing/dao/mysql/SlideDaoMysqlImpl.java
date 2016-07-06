package com.qcloud.component.marketing.dao.mysql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.marketing.dao.SlideDao;
import com.qcloud.component.marketing.model.Slide;
import com.qcloud.component.marketing.model.query.SlideQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class SlideDaoMysqlImpl implements SlideDao {

    @Resource(name = "sqlOperator-marketing")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(Slide slide) {

        return sqlOperator.insert("com.qcloud.component.marketing.dao.mysql.mapper.SlideMapper.insert", slide) == 1;
    }

    @Override
    public Slide get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.marketing.dao.mysql.mapper.SlideMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.marketing.dao.mysql.mapper.SlideMapper.delete", id) > 0;
    }

    @Override
    public boolean update(Slide slide) {

        return sqlOperator.update("com.qcloud.component.marketing.dao.mysql.mapper.SlideMapper.update", slide) > 0;
    }

    @Override
    public List<Slide> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Slide> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Slide> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<Slide> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.SlideMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.marketing.dao.mysql.mapper.SlideMapper.count4page", param);
        Page<Slide> page = new Page<Slide>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<Slide> page(SlideQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<Slide> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.SlideMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.marketing.dao.mysql.mapper.SlideMapper.count4query", param);
        Page<Slide> page = new Page<Slide>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<Slide> listAll() {

        List<Slide> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.SlideMapper.listAll");
        return list;
    }

    @Override
    public List<Slide> listBySence(int sence) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("sence", sence);
        param.put("now", new Date());
        List<Slide> list = sqlOperator.selectList("com.qcloud.component.marketing.dao.mysql.mapper.SlideMapper.listBySence", param);
        return list;
    }
}
