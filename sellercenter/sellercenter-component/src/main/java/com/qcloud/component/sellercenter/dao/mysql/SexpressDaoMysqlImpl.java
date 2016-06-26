package com.qcloud.component.sellercenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.sellercenter.dao.SexpressDao;
import com.qcloud.component.sellercenter.model.Sexpress;
import com.qcloud.component.sellercenter.model.query.SexpressQuery;

@Repository
public class SexpressDaoMysqlImpl implements SexpressDao {

    @Resource(name = "sqlOperator-sellercenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(Sexpress sexpress) {

        return sqlOperator.insert("com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressMapper.insert", sexpress) == 1;
    }

    @Override
    public Sexpress get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressMapper.delete", id) > 0;
    }

    @Override
    public boolean update(Sexpress sexpress) {

        return sqlOperator.update("com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressMapper.update", sexpress) > 0;
    }

    @Override
    public List<Sexpress> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Sexpress> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Sexpress> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<Sexpress> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressMapper.count4page", param);
        Page<Sexpress> page = new Page<Sexpress>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<Sexpress> page(SexpressQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("merchandId", query.getMerchandId());
        List<Sexpress> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressMapper.count4query", param);
        Page<Sexpress> page = new Page<Sexpress>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<Sexpress> listAll() {

        List<Sexpress> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressMapper.listAll");
        return list;
    }

    @Override
    public List<Sexpress> listByMerchant(Long merchandId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchandId", merchandId);
        List<Sexpress> list = sqlOperator.selectList("com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressMapper.listByMerchant", param);
        return list;
    }

    @Override
    public Sexpress getByCode(String expressCode, long merchantId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchandId", merchantId);
        param.put("code", expressCode);
        Sexpress sexpress = sqlOperator.selectOne("com.qcloud.component.sellercenter.dao.mysql.mapper.SexpressMapper.getByCode", param);
        return sexpress;
    }
}
