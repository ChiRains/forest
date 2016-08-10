package com.qcloud.component.pay.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.pay.dao.PayRecordDao;
import com.qcloud.component.pay.model.PayRecord;
import com.qcloud.component.pay.model.query.PayRecordQuery;

@Repository
public class PayRecordDaoMysqlImpl implements PayRecordDao {

    @Resource(name = "sqlOperator-pay")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(PayRecord payRecord) {

        return sqlOperator.insert("com.qcloud.component.pay.dao.mysql.mapper.PayRecordMapper.insert", payRecord) == 1;
    }

    @Override
    public PayRecord get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.pay.dao.mysql.mapper.PayRecordMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.pay.dao.mysql.mapper.PayRecordMapper.delete", id) > 0;
    }

    @Override
    public boolean update(PayRecord payRecord) {

        return sqlOperator.update("com.qcloud.component.pay.dao.mysql.mapper.PayRecordMapper.update", payRecord) > 0;
    }

    @Override
    public List<PayRecord> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, PayRecord> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<PayRecord> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<PayRecord> list = sqlOperator.selectList("com.qcloud.component.pay.dao.mysql.mapper.PayRecordMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.pay.dao.mysql.mapper.PayRecordMapper.count4page", param);
        Page<PayRecord> page = new Page<PayRecord>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<PayRecord> page(PayRecordQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<PayRecord> list = sqlOperator.selectList("com.qcloud.component.pay.dao.mysql.mapper.PayRecordMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.pay.dao.mysql.mapper.PayRecordMapper.count4query", param);
        Page<PayRecord> page = new Page<PayRecord>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<PayRecord> listAll() {

        List<PayRecord> list = sqlOperator.selectList("com.qcloud.component.pay.dao.mysql.mapper.PayRecordMapper.listAll");
        return list;
    }

    @Override
    public PayRecord getByObjectId(long objectId) {

        return sqlOperator.selectOne("com.qcloud.component.pay.dao.mysql.mapper.PayRecordMapper.getByObjectId", objectId);
    }
}
