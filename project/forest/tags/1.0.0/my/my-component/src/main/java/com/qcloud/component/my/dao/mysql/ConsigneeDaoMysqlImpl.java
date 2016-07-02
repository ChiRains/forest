package com.qcloud.component.my.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.ConsigneeDao;
import com.qcloud.component.my.model.Consignee;
import com.qcloud.component.my.model.query.ConsigneeQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;

@Repository
public class ConsigneeDaoMysqlImpl implements ConsigneeDao {

    @Resource(name = "sqlOperator-my")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(Consignee consignee) {

        return sqlOperator.insert("com.qcloud.component.my.dao.mysql.mapper.ConsigneeMapper.insert", consignee) == 1;
    }

    @Override
    public Consignee get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.ConsigneeMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.my.dao.mysql.mapper.ConsigneeMapper.delete", id) > 0;
    }

    @Override
    public boolean update(Consignee consignee) {

        return sqlOperator.update("com.qcloud.component.my.dao.mysql.mapper.ConsigneeMapper.update", consignee) > 0;
    }

    @Override
    public List<Consignee> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Consignee> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Consignee> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<Consignee> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.ConsigneeMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.ConsigneeMapper.count4page", param);
        Page<Consignee> page = new Page<Consignee>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<Consignee> page(ConsigneeQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<Consignee> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.ConsigneeMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.my.dao.mysql.mapper.ConsigneeMapper.count4query", param);
        Page<Consignee> page = new Page<Consignee>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<Consignee> listAll() {

        List<Consignee> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.ConsigneeMapper.listAll");
        return list;
    }

    @Override
    public List<Consignee> listByUser(Long userId) {

        List<Consignee> list = sqlOperator.selectList("com.qcloud.component.my.dao.mysql.mapper.ConsigneeMapper.listByUser", userId);
        return list;
    }
}
