package com.qcloud.component.my.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.InvoiceModeDao;
import com.qcloud.component.my.model.InvoiceMode;
import com.qcloud.component.my.model.query.InvoiceModeQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class InvoiceModeDaoMysqlImpl implements InvoiceModeDao {

    @Resource(name = "sqlOperator-my")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(InvoiceMode invoiceMode) {

        return sqlOperator.insert("com.qcloud.component.my.dao.mysql.mapper.InvoiceModeMapper.insert", invoiceMode) == 1;
    }

    @Override
    public InvoiceMode get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.InvoiceModeMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.InvoiceModeMapper.delete", id) > 0;
    }

    @Override
    public boolean update(InvoiceMode invoiceMode) {

        return sqlOperator.update("com.qcloud.component.my.dao.mysql.mapper.InvoiceModeMapper.update", invoiceMode) > 0;
    }

    @Override
    public List<InvoiceMode> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, InvoiceMode> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<InvoiceMode> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<InvoiceMode> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.InvoiceModeMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.InvoiceModeMapper.count4page", param);
        Page<InvoiceMode> page = new Page<InvoiceMode>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<InvoiceMode> page(InvoiceModeQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<InvoiceMode> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.InvoiceModeMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.InvoiceModeMapper.count4query", param);
        Page<InvoiceMode> page = new Page<InvoiceMode>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<InvoiceMode> listAll() {

        List<InvoiceMode> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.InvoiceModeMapper.listAll");
        return list;
    }

    @Override
    public InvoiceMode getByUser(Long userId) {

        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.InvoiceModeMapper.getByUser", userId);
    }
}
