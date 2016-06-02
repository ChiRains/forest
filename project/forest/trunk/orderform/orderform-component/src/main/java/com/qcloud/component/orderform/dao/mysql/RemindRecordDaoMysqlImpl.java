package com.qcloud.component.orderform.dao.mysql;

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
import com.qcloud.component.orderform.dao.RemindRecordDao;
import com.qcloud.component.orderform.model.RemindRecord;
import com.qcloud.component.orderform.model.query.RemindRecordQuery;

@Repository
public class RemindRecordDaoMysqlImpl implements RemindRecordDao {

    @Resource(name = "sqlOperator-orderform")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(RemindRecord remindRecord) {

        return sqlOperator.insert("com.qcloud.component.orderform.dao.mysql.mapper.RemindRecordMapper.insert", remindRecord) == 1;
    }

    @Override
    public RemindRecord get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.RemindRecordMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.orderform.dao.mysql.mapper.RemindRecordMapper.delete", id) > 0;
    }

    @Override
    public boolean update(RemindRecord remindRecord) {

        return sqlOperator.update("com.qcloud.component.orderform.dao.mysql.mapper.RemindRecordMapper.update", remindRecord) > 0;
    }

    @Override
    public List<RemindRecord> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, RemindRecord> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<RemindRecord> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<RemindRecord> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.RemindRecordMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.RemindRecordMapper.count4page", param);
        Page<RemindRecord> page = new Page<RemindRecord>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<RemindRecord> page(RemindRecordQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<RemindRecord> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.RemindRecordMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.RemindRecordMapper.count4query", param);
        Page<RemindRecord> page = new Page<RemindRecord>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<RemindRecord> listAll() {

        List<RemindRecord> list = sqlOperator.selectList("com.qcloud.component.orderform.dao.mysql.mapper.RemindRecordMapper.listAll");
        return list;
    }

    @Override
    public boolean canRemind(long subOrderId, Date orderDate, int splitMinutes) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("subOrderId", subOrderId);
        param.put("time", DateUtil.addTime(new Date(), 0 - splitMinutes));
        return (Integer) sqlOperator.selectOne("com.qcloud.component.orderform.dao.mysql.mapper.RemindRecordMapper.hasRemind", param) == 0;
    }
}
