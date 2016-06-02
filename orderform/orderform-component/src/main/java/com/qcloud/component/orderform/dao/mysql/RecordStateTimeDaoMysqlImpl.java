package com.qcloud.component.orderform.dao.mysql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.orderform.dao.RecordStateTimeDao;
import com.qcloud.component.orderform.model.RecordStateTime;
import com.qcloud.component.orderform.model.query.RecordStateTimeQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class RecordStateTimeDaoMysqlImpl implements RecordStateTimeDao {

    @Resource(name = "sqlOperator-orderform")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(RecordStateTime recordStateTime) {

        return sqlOperator.insert("com.qcloud.component.orderform.dao.mysql.mapper.RecordStateTimeMapper.insert", recordStateTime) == 1;
    }

    @Override
    public RecordStateTime get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.RecordStateTimeMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.orderform.dao.mysql.mapper.RecordStateTimeMapper.delete", id) > 0;
    }

    @Override
    public boolean update(RecordStateTime recordStateTime) {

        return sqlOperator.update("com.qcloud.component.orderform.dao.mysql.mapper.RecordStateTimeMapper.update", recordStateTime) > 0;
    }

    @Override
    public List<RecordStateTime> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, RecordStateTime> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<RecordStateTime> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<RecordStateTime> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.RecordStateTimeMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.RecordStateTimeMapper.count4page", param);
        Page<RecordStateTime> page = new Page<RecordStateTime>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<RecordStateTime> page(RecordStateTimeQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<RecordStateTime> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.RecordStateTimeMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.RecordStateTimeMapper.count4query", param);
        Page<RecordStateTime> page = new Page<RecordStateTime>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<RecordStateTime> listAll() {

        List<RecordStateTime> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.RecordStateTimeMapper.listAll");
        return list;
    }

    @Override
    public List<RecordStateTime> list4EndDateAndState(Date endDate, Date tableDate, int state, int dataIdType, int start, int size) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", size);
        param.put("state", state);
        param.put("endDate", endDate);
        param.put("dataIdType", dataIdType);
        // param.put("table_name", getTableName(tableDate));
        List<RecordStateTime> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.RecordStateTimeMapper.list4EndDateAndState", param);
        return list;
    }
}
