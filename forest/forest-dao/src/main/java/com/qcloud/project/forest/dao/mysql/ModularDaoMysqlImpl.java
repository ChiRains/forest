package com.qcloud.project.forest.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.project.forest.dao.ModularDao;
import com.qcloud.project.forest.model.Modular;
import com.qcloud.project.forest.model.query.ModularQuery;

@Repository
public class ModularDaoMysqlImpl implements ModularDao {

    @Resource(name = "sqlOperator-forest")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(Modular modular) {

        return sqlOperator.insert("com.qcloud.project.forest.dao.mysql.mapper.ModularMapper.insert", modular) == 1;
    }

    @Override
    public Modular get(Long id) {

        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ModularMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.project.forest.dao.mysql.mapper.ModularMapper.delete", id) > 0;
    }

    @Override
    public boolean update(Modular modular) {

        return sqlOperator.update("com.qcloud.project.forest.dao.mysql.mapper.ModularMapper.update", modular) > 0;
    }

    @Override
    public List<Modular> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, Modular> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<Modular> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<Modular> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ModularMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ModularMapper.count4page", param);
        Page<Modular> page = new Page<Modular>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<Modular> page(ModularQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<Modular> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ModularMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ModularMapper.count4query", param);
        Page<Modular> page = new Page<Modular>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<Modular> listAll() {

        List<Modular> list = sqlOperator.selectList("com.qcloud.project.forest.dao.mysql.mapper.ModularMapper.listAll");
        return list;
    }

    @Override
    public Modular getByCode(String code) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("code", code);
        return sqlOperator.selectOne("com.qcloud.project.forest.dao.mysql.mapper.ModularMapper.getByCode", param);
    }
}
