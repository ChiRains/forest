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
import com.qcloud.component.pay.dao.PayRequestDao;
import com.qcloud.component.pay.model.PayRequest;
import com.qcloud.component.pay.model.query.PayRequestQuery;

@Repository
public class PayRequestDaoMysqlImpl implements PayRequestDao {

    @Resource(name = "sqlOperator-pay")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(PayRequest payRequest) {

        return sqlOperator.insert("com.qcloud.component.pay.dao.mysql.mapper.PayRequestMapper.insert", payRequest) == 1;
    }

    @Override
    public PayRequest get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.pay.dao.mysql.mapper.PayRequestMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.pay.dao.mysql.mapper.PayRequestMapper.delete", id) > 0;
    }

    @Override
    public boolean update(PayRequest payRequest) {

        return sqlOperator.update("com.qcloud.component.pay.dao.mysql.mapper.PayRequestMapper.update", payRequest) > 0;
    }

    @Override
    public List<PayRequest> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, PayRequest> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<PayRequest> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<PayRequest> list = sqlOperator.selectList("com.qcloud.component.pay.dao.mysql.mapper.PayRequestMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.pay.dao.mysql.mapper.PayRequestMapper.count4page", param);
        Page<PayRequest> page = new Page<PayRequest>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<PayRequest> page(PayRequestQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<PayRequest> list = sqlOperator.selectList("com.qcloud.component.pay.dao.mysql.mapper.PayRequestMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.pay.dao.mysql.mapper.PayRequestMapper.count4query", param);
        Page<PayRequest> page = new Page<PayRequest>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<PayRequest> listAll() {

        List<PayRequest> list = sqlOperator.selectList("com.qcloud.component.pay.dao.mysql.mapper.PayRequestMapper.listAll");
        return list;
    }

    @Override
    public PayRequest getByObj(String module, Long objId, String type, String client) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("module", module);
        param.put("objId", objId);
        param.put("type", type);
        param.put("client", client);
        return sqlOperator.selectOne("com.qcloud.component.pay.dao.mysql.mapper.PayRequestMapper.getByObj", param);
    }

    @Override
    public PayRequest getByAttach(String module, String attach, String type, String client) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("module", module);
        param.put("attach", attach);
        param.put("type", type);
        param.put("client", client);
        return sqlOperator.selectOne("com.qcloud.component.pay.dao.mysql.mapper.PayRequestMapper.getByAttach", param);
    }
}
